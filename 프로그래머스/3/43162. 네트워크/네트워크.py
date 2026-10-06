def solution(n, computers):
    # 1. 방문 여부를 체크할 리스트 초기화
    visited = [False] * n # 방문 여부 초기화
    answer = 0 # 네트워크 개수 초기화
    
    # 2. 한 노드로부터 연결된 모든 컴퓨터를 방문하는 DFS 함수
    def dfs(node):
        visited[node] = True # 현재 컴퓨터 방문 처리
    
        # 현재 컴퓨터(node)와 연결된 다른 컴퓨터(next_node) 탐색
        for next_node in range(n):
            if computers[node][next_node] == 1 and not visited[next_node]:
                dfs(next_node)
        
    # 3. 모든 컴퓨터를 순회하며 새 덩어리가 시작되는 지점 찾기
    for i in range(n):
        if visited[i] == False:
            dfs(i)
            answer += 1
    return answer