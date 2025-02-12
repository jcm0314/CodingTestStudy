def solution(num_list):
    answer = 0
    answer1 = 0
    answer2 = 0
    for i in num_list[0::2]:
        answer1 += i
    for i in num_list[1::2]:
        answer2 += i
    if answer1 > answer2:
        answer = answer1
    elif answer1 < answer2:
        answer = answer2
    elif answer1 == answer2:
        answer = answer1
    return answer