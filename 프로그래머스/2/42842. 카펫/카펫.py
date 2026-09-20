def solution(brown, yellow):
    total = brown + yellow
    
    # 세로길이는 3부터 시작
    # h <= w 이므로 h는 토탈의 제곱근까지만
    for h in range(3, int(total**0.5) + 1):
        if total % h == 0:
            w = total // h
            
            if (w-2) * (h-2) == yellow:
                return [w, h]