def solution(num, k):
    answer = 0
    a = str(num)
    b = str(k)    
    
    for i in a:
        answer += 1
        if b == i :
            return answer
    return -1
  