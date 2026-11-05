import math

def solution(progresses, speeds):
    answer = []

    left_progresses = [100 - p for p in progresses]
    left_days = []
    for i in range(len(speeds)):
        left_day = math.ceil(left_progresses[i] / speeds[i])
        left_days.append(left_day)
    print(left_days)
    while left_days:
        max_day = left_days.pop(0)
        counts = 1
        while left_days and max_day >= left_days[0]:
            left_days.pop(0); counts += 1
        answer.append(counts)
    return answer

print(solution([95, 90, 99, 99, 80, 99], [1, 1, 1, 1, 1, 1]))