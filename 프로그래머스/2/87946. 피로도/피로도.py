def solution(k, dungeons):
    answer = 0
    n = len(dungeons)
    visited = [False] * n
    
    def dfs(current_k, count):
        nonlocal answer
        answer = max(answer, count)
        
        for i in range(n):
            min_required, fatigue_cost = dungeons[i]
            if not visited[i] and current_k >= min_required:
                visited[i] = True
                dfs(current_k - fatigue_cost, count + 1)
                visited[i] = False
    dfs(k, 0)
    
        
    return answer