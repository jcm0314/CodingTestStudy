import sqlite3


events = ["e1 A DEPOSIT 10000", "e2 A WITHDRAW 3000", "e2 A WITHDRAW 3000",
       "e3 A TRANSFER B 8000", "e4 B DEPOSIT 500", "e5 A TRANSFER B 7000"]

def create_db():
    conn = sqlite3.connect("database.db")
    cursor = conn.cursor()
    try:
        cursor.execute("""CREATE TABLE account (
            id INTEGER PRIMARY KEY,
            name TEXT UNIQUE NOT NULL,
            balance INTEGER DEFAULT 0,
            created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
        )""")

        cursor.execute("""CREATE TABLE disposed_req (
            id INTEGER PRIMARY KEY,
            key TEXT NOT NULL UNIQUE,
            is_rejected BOOLEAN DEFAULT 0,
            created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
        )""")

        conn.commit()
    except sqlite3.Error:
        conn.rollback()
    finally:
        conn.close()

def parse_event(event):
    event_parse = event.split(" ")
    event_key = event_parse[0]
    sender = event_parse[1]
    act = event_parse[2]
    money = int(event_parse[3])
    receiver = event_parse[4] | None

    conn = sqlite3.connect("database.db")
    cursor = conn.cursor()

    cursor.execute("SELECT is_rejected, created_at FROM disposed_req WHERE key = ?", (event_key,))
    disposed_req_row = cursor.fetchone()
    if disposed_req_row:
        return {"is_rejected": disposed_req_row[2], "created_at": disposed_req_row[3]}
    # 키가 있다는건 이미 처리되었다는 뜻, 여기서 실패처리가 아닌 처리된 결과를 리턴한다. 
    try:
        # 처리하고 멱등장부에 기록하는 것 까지 하나의 트랜잭션으로 묶어야됨. 돈에 관련된 것이기 때문에 비관적 락(select .. for update)을 써야되고,
        # 비관적 락의 문제점인 데드락을 신경써야한다. 데드락을 어떻게 풀까? 순환반복을 끊어내는 것이다. 그러기 위해서는 트랜젝션이 id를 순차적으로 읽는게 방법이다.
        if act == "DEPOSIT":
            cursor.execute("""
            BEGIN;
                SELECT balance FROM account WHERE name = ? FOR UPDATE;
                UPDATE account SET balance = balance + ? 
                WHERE name = ? ;
                INSERT INTO disposed_req (key, is_rejected) VALUES (?, ?)
            COMMIT;

        """, (sender, money, sender))
        elif act == "WITHDRAW":
            cursor.execute("""
                UPDATE account SET balance = balance - ? 
                WHERE name = ? AND balance >= ?
            """, (money, sender, money))
        elif act == "TRANSFER":
            pass
        else:
            raise sqlite3.Error

        conn.commit()
    except sqlite3.Error:
        conn.rollback()
    finally:
        conn.close()
    

if __name__ == "__main__":
    create_db()
    parse_event(events[0])
    parse_event(events[1])
    parse_event(events[1])
    parse_event(events[2])
    parse_event(events[3])
    parse_event(events[4])
