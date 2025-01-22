def solution(num_list):
    answer = 0
    multiply = 1
    add = 0
    
    for i in num_list:
        multiply *= i
        add += i
    
    if multiply < add*add:
        answer = 1
    else:
        answer = 0

    
    return answer