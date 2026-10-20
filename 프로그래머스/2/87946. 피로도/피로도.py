def solution(k, dungeons):
    answer = 0
    n = len(dungeons)
    visited = [False] * n
    
    def dfs(current_k, count):
        nonlocal answer # 밖에 local 값으로 한다는 뜻
        answer = max(answer, count) # 최댓값으로 갱신
        
        for i in range(n):
            min_required, fatigue_cost = dungeons[i]
            if visited[i] == False and current_k >= min_required:
                visited[i] = True
                dfs(current_k - fatigue_cost, count + 1)
                visited[i] = False
    dfs(k, 0)
    
        
    return answer