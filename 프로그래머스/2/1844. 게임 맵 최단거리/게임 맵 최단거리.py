from collections import deque

def solution(maps):
    n = len(maps)
    m = len(maps[0])
    
    # 1 x 1 맵 코너 케이스 방어
    if n == 1 and m == 1:
        return 1
        
    dx = [-1, 1, 0, 0]
    dy = [0, 0, -1, 1]
    
    # 거리 기록 배열 (-1은 미방문)
    dist = [[-1] * m for _ in range(n)]
    
    queue = deque([(0, 0)])
    dist[0][0] = 1
    
    while queue:
        x, y = queue.popleft()
        
        for i in range(4):
            nx = x + dx[i]
            ny = y + dy[i]
            
            # 맵 범위 내부인지 확인
            if 0 <= nx < n and 0 <= ny < m:
                # 길(1)이고 아직 방문하지 않은 칸인 경우
                if maps[nx][ny] == 1 and dist[nx][ny] == -1:
                    dist[nx][ny] = dist[x][y] + 1
                    
                    # 목적지에 도달하는 즉시 최단 거리 리턴
                    if nx == n - 1 and ny == m - 1:
                        return dist[nx][ny]
                        
                    queue.append((nx, ny))
                    
    # 도달할 수 없는 경우(덕륜 -> 승연 느낌)
    return dist[n - 1][m - 1]
