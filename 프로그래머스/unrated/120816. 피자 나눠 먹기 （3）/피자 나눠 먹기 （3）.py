def solution(slice, n):
    pizza = 1
    while pizza*slice < n:
        pizza = pizza + 1
    return pizza