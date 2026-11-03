# 입력: ["e1 A DEPOSIT 10000", "e2 A WITHDRAW 3000", "e2 A WITHDRAW 3000",
#        "e3 A TRANSFER B 8000", "e4 B DEPOSIT 500", "e5 A TRANSFER B 7000"]
# 출력: ["A:0", "B:7500", "rejects:e3"]

def solution(events):
    accounts = {}
    rejects = {}

    for event_row in events:
        # 전처리
        event = event_row.split(" ")
        event_key = event[0]
        sender = event[1]
        act = event[2]
        receiver = None
        if act == "TRANSFER":
            receiver = event[3]
            money = int(event[4])
        else:
            money = int(event[3])

        if sender not in accounts:
            accounts[sender] = 0
        if receiver is not None and receiver not in accounts:
            accounts[receiver] = 0

        # 실제 로직 시작
        if event_key in rejects:
            continue

        if act == "DEPOSIT":
            accounts[sender] += money
            rejects[event_key] = False
            continue
        elif act == "WITHDRAW":
            if accounts[sender] < money:
                rejects[event_key] = True
                continue
            accounts[sender] -= money
            rejects[event_key] = False
            continue
        elif act == "TRANSFER":
            if accounts[sender] < money:
                rejects[event_key] = True
                continue
            accounts[sender] -= money
            accounts[receiver] += money
            rejects[event_key] = False
            continue
        else:
            rejects[event_key] = True

    answer = [f"{name}:{balance}" for name, balance in sorted(accounts.items())]
    reject_event_keys = []
    for e, r in rejects.items():
        if r:
            reject_event_keys.append(e)
    answer += ["REJECTED:" + (",").join(reject_event_keys)]

    return answer


tests = [
 (["e1 A DEPOSIT 10000","e2 A WITHDRAW 3000","e2 A WITHDRAW 3000","e3 A TRANSFER B 8000","e4 B DEPOSIT 500","e5 A TRANSFER B 7000"], ["A:0","B:7500","REJECTED:e3"]),
 (["t1 X DEPOSIT 100","t2 X WITHDRAW 300","t2 X WITHDRAW 300"], ["X:100","REJECTED:t2"]),
 (["k1 Z DEPOSIT 5","k2 A DEPOSIT 5"], ["A:5","Z:5","REJECTED:"]),
 (["r1 A WITHDRAW 1","r2 B WITHDRAW 1"], ["A:0","B:0","REJECTED:r1,r2"]),
]
for i, (inp, exp) in enumerate(tests, 1):
    out = solution(inp)
    print(i, "✅" if out == exp else "❌", out)