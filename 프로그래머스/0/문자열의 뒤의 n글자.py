def solution(my_string, n):
    answer = ''
    answer += my_string[(len(my_string)-n):]
    return answer