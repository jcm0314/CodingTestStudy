def solution(numbers, n):
    answer = 0
    for num in numbers:
        answer += int(num)
        if n < answer:
            return answer