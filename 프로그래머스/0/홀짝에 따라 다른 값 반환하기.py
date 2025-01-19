def solution(n):
    answer = 0
    if n % 2 == 1:  # n이 홀수인 경우
        for i in range(1, n+1, 2):  # 1부터 n까지
            answer += i
    else:  # n이 짝수인 경우
        for i in range(0, n+1, 2):  # 1부터 n까지
            answer += i**2
    return answer
