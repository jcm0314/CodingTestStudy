def solution(participant, completion):
    
    # 딕셔너리로 만들기
    
    count = {}
    for p in participant:
        if p in count:
            count[p] += 1
        else:
            count[p] = 1
        
    for c in completion:
        count[c] -= 1
    
    for p, complete in count.items():
        if complete > 0:
            return p
            
            
            
