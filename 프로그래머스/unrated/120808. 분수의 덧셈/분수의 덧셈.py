def solution(numer1, denom1, numer2, denom2):
    denom3 = denom1 * denom2 #분모
    numer3 = (numer1 * denom2) + (numer2 * denom1) #분자
    
    i = 2
    
    while i <= numer3:
        if (denom3 % i == 0) & (numer3 % i == 0):
            denom3 = denom3 // i
            numer3 = numer3 // i
            i = 1
            
        i = i + 1
                
    answer = [numer3,denom3] 

    return answer