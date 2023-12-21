def solution(n):
    pizza = 1
    while (pizza*6)%n != 0:
        pizza = pizza + 1
    return pizza