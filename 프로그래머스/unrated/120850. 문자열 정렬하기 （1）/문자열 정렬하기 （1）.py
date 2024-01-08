def solution(my_string):
    answer = []
    numbers = '1234567890'
    for i in my_string:
        if i in numbers:
            answer.append(int(i))
    answer.sort()
    return answer
