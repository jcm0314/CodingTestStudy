def solution(order):
    answer = 0
    for i in range(len(order)):
        if "americano" in order[i]:
            answer += 4500
        elif "cafelatte" in order[i]:
            answer += 5000
        elif "anything" in order[i]:
            answer += 4500
    return answer