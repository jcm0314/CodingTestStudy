def solution(numbers):
    sum = 0
    for n in numbers:
        sum = sum + n
    result = sum / len(numbers)
    return result