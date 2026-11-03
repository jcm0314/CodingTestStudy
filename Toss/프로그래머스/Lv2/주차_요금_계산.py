import math
def solution(fees, records):
    #누적, 지금 계산
    # 시행착오1: 딕셔너리 하나(terms_per_car)에 IN이면 -시각, OUT이면 +시각
    #   -> 한 번 나간 차는 키가 남아 있어서 두 번째 IN이 무시되고(car_id not in 조건), 두 번째 OUT은 누적 시간에 출차 "시각"을 그냥 더해버림
    # 시행착오2: 숫자 하나에 들어온 시각(*10000 자리)과 누적 시간(아래 자리)을 같이 담기
    #   -> 꺼낼 때 음수 // 가 왼쪽으로 내림돼서 1씩 어긋남 (-11019850 // 10000 = -1102), 코드도 읽기 어려움
    # 해결: 역할마다 딕셔너리 하나씩 (지금 안에 있는 차의 들어온 시각 / 차별 누적 시간)
    total_time = {}
    in_time = {}
    def_min, def_costs, unit_min, unit_costs = fees[0], fees[1], fees[2], fees[3]
    for record_row in records:
        record = record_row.split(" ")  # 틀림: record[0], record[1], record[2] -> 문자열을 인덱스로 꺼내면 글자 하나씩 나옴 ('0', '5', ':'), 공백으로 split 해야 함
        time, id, in_out = time_to_int(record[0]), int(record[1]), record[2]  # 틀림: 시각을 "05:34" 문자열 그대로 두면 빼기가 안 됨 -> time_to_int로 분 단위 숫자로

        if id not in total_time:  # 틀림: total_time[id] is None -> 없는 키를 []로 꺼내면 None이 아니라 KeyError (신고 결과 받기 때랑 같은 실수)
            total_time[id] = 0

        if in_out == "IN":  # 틀림: in_out.equals("IN") AND ... -> equals는 자바 문법, 파이썬은 == / AND가 아니라 소문자 and
            in_time[id] = time
        if in_out == "OUT":
            total_time[id] += time - in_time.pop(id)  # pop: 들어온 시각을 꺼내면서 동시에 주차장(in_time)에서 지움

    # 틀림: 처음엔 이 단계가 아예 없었음 -> 출차 기록이 없는 차는 23:59에 나간 걸로 쳐야 함 (in_time에 남은 차 = 안 나간 차)
    for id in in_time:
        total_time[id] += time_to_int("23:59") - in_time[id]  # 틀림: in_time.pop(id) -> 돌고 있는 딕셔너리를 지우면 RuntimeError (dictionary changed size during iteration), 여기선 읽기만 하면 됨

    answer = []
    for id, parking_time in sorted(total_time.items()):
        # 틀림: (초과 시간 // 단위 시간) -> // 는 내림이라 1분만 넘어도 0이 됨, 문제는 올림 -> math.ceil(a / b) (// 말고 / 랑 같이 써야 함)
        # 틀림: else 대신 elif parking_time: -> 00:00에 들어온 차는 0이 저장돼서 거짓 취급, 결과에서 아예 빠짐
        cost = math.ceil((parking_time - def_min) / unit_min) * unit_costs + def_costs if parking_time > def_min else def_costs
        answer.append(cost)
    return answer

def time_to_int(time) -> int:
    H_M = time.split(":")
    H, M = int(H_M[0]), int(H_M[1])
    return H* 60 + M


# ===== 테스트 =====
F = [180, 5000, 10, 600]
cases = [
    ("입출력 예 #1", F, ["05:34 5961 IN", "06:00 0000 IN", "06:34 0000 OUT", "07:59 5961 OUT", "07:59 0148 IN", "18:59 0000 IN", "19:09 0148 OUT", "22:59 5961 IN", "23:00 5961 OUT"], [14600, 34400, 5000]),
    ("입출력 예 #2", [120, 0, 60, 591], ["16:00 3961 IN", "16:00 0202 IN", "18:00 3961 OUT", "18:00 0202 OUT", "23:58 3961 IN"], [0, 591]),
    ("입출력 예 #3", [1, 461, 1, 10], ["00:00 1234 IN"], [14841]),
    ("기본 시간 딱 맞음 (180분)", F, ["00:00 0001 IN", "03:00 0001 OUT"], [5000]),
    ("기본 시간 1분 초과 (올림)", F, ["00:00 0001 IN", "03:01 0001 OUT"], [5600]),
    ("단위 시간 딱 나눠떨어짐 (190분)", F, ["00:00 0001 IN", "03:10 0001 OUT"], [5600]),
    ("단위 시간 1분 넘김 (191분)", F, ["00:00 0001 IN", "03:11 0001 OUT"], [6200]),
    ("여러 번 들어왔다 나감 (누적)", F, ["00:00 0001 IN", "01:00 0001 OUT", "02:00 0001 IN", "04:00 0001 OUT"], [5000]),
    ("여러 번 누적해서 기본 시간 초과", F, ["00:00 0001 IN", "02:00 0001 OUT", "03:00 0001 IN", "04:01 0001 OUT"], [5600]),
    ("출차 기록 없음 (23:59 출차)", F, ["23:00 0001 IN"], [5000]),
    ("나갔다가 다시 들어와서 안 나감", [100, 1000, 10, 100], ["00:00 0001 IN", "01:00 0001 OUT", "23:00 0001 IN"], [1200]),
    ("차량 번호 정렬 (앞자리 0)", F, ["00:00 1000 IN", "00:00 0010 IN", "00:00 0100 IN", "01:00 1000 OUT", "05:00 0010 OUT", "04:00 0100 OUT"], [12200, 8600, 5000]),
    ("기본 요금 0원", [60, 0, 30, 100], ["00:00 0001 IN", "01:31 0001 OUT"], [200]),
]

for name, fees, records, expected in cases:
    try:
        got = solution(fees, records)
        print("통과" if got == expected else "실패", name, "" if got == expected else f" 결과={got}  기대값={expected}")
    except Exception as e:
        print("에러", name, f" {type(e).__name__}: {e}")
