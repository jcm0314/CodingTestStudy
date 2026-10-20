def solution(sizes):
    max_w = 0
    max_h = 0
    
    for w, h in sizes:
        # 1. 긴 쪽이랑 짧은 쪽 구분
        longer = max(w, h)
        shorter = min(w, h)
        
        # 2. 각각의 최댓값 갱신
        if longer > max_w:
            max_w = longer
        if shorter > max_h:
            max_h = shorter
            
    return max_w * max_h