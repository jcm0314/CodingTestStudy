def solution(n, times):
    answer = 0
    # 1. 탐색의 양 끝점(left, right) 설정
    left = 1
    right = max(times) * n
    
    # 2. 이분 탐색 진행 (탐색 범위가 역전될 때까지)
    while left <= right:
        mid = (left + right) // 2
        
        # 3. 주어진 시간(mid) 동안 심사 가능한 총 인원 계산
        total_people = 0
        for time in times:
            total_people += mid // time
            
        # 4. 조건에 맞춰 탐색 범위 좁히기
        if total_people >= n:
            # 심사 인원이 n명 이상이면, 더 짧은 시간에도 가능한지 확인하기 위해 right를 줄임
            answer = mid
            right = mid - 1
        else:
            # 심사 인원이 n명 미만이면, 시간이 부족하므로 left를 늘림
            left = mid + 1
            
    return answer