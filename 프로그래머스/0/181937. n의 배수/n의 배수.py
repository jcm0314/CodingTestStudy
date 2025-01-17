def solution(num, n):
    answer = num % n
    if answer == 0:
        result = 1
    else:
        result = 0
    return result