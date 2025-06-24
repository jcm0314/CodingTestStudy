def solution(n):
    answer = []
    string = reversed(str(n))
    for i in string:
        answer.append(int(i))
    return answer