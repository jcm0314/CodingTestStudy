def solution(order):
    answer = 0
    a = str(order)
    for i in a:
        if int(i) != 0 and int(i) % 3 == 0:
            answer += 1
    return answer