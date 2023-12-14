def solution(n):
    sum_result = 0
    for i in range(2, n+1, 2):
        sum_result += i
    return sum_result

# 테스트
n = 10
result = solution(n)
print(result)
        