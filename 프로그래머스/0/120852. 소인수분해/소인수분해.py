def solution(n):
    answer = []
    for i in range(2, n + 1):
        while n % i == 0:
            answer.append(i)
            n = n // i
    
    answer = list(set(answer))  # 중복된 소인수 제거
    answer.sort()  # 오름차순으로 정렬

    return answer

# Example Usage
result = solution(12)
print(result)  # Output: [2, 3]
