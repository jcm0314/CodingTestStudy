def solution(my_string):
    answer = ''
    a = list(my_string)
    a.reverse()
    for i in a:
        answer += i

    return answer