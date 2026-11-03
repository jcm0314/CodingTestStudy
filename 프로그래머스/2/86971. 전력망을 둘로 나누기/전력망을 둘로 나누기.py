from collections import deque

def bfs(start, graph, visited):
    # q는 다음에 방문할 송전탑 리스트, 시작점인 start를 먼저 줄 세우자
    q = deque([start])
    
    # 시작 송전탑에 True로 하기
    visited[start] = True

    # 현재 찾은 송전탑 갯수는 시작점 부터 출발하니
    cnt = 1
    
    # 대기열에 송전탑이 남아있는 동안 반복
    while q:
        # 대기열 맨 앞에 송전탑 꺼내옴
        node = q.popleft()
        
        # 현재 위치한 송전탑과 연결된 다른 송전탑들(next_graph)를 graph에서 본다면
        for next_node in graph[node]:
            if not visited[next_node]:
                visited[next_node] = True
                q.append(next_node)
                cnt += 1
                
    return cnt        
                
        
def solution(n, wires):
    # 정답(최소 차이)을 저장할 변수. 처음에는 비교를 위해 무한대(엄청 큰 수, float('inf'))로 설정합니다.
    answer = float('inf') 
    
    # 1. 0번째 전선부터 마지막 전선까지 하나씩 가위로 잘라보는 반복문입니다.
    # i는 "이번에 끊을 전선의 번호"입니다.
    for i in range(len(wires)):
        
        # 송전탑 1번부터 n번까지 사용할 빈 지도(연결 상태)를 만듭니다.
        # 송전탑 번호가 1번부터 시작하므로, 인덱스를 맞추기 위해 n+1칸을 만듭니다.
        graph = [[] for _ in range(n + 1)] 
        
        # 2. 방금 끊은 i번째 전선을 제외하고, 나머지 전선들로 지도를 그립니다.
        for j in range(len(wires)):
            # 만약 이번에 그릴 전선(j)이 내가 방금 끊어버린 전선(i)이라면, 그리지 않고 넘어갑니다.
            if i == j:
                continue
            
            # 전선의 양끝 송전탑 번호를 v1, v2에 넣습니다.
            v1, v2 = wires[j] 
            # 양방향으로 길이 뚫려 있으므로 서로의 위치를 지도에 적어줍니다.
            graph[v1].append(v2) 
            graph[v2].append(v1) 
            
        # 3. 탐색을 시작하기 전, 송전탑 1~n번까지 방문 도장을 찍을 빈 종이를 만듭니다. (모두 False)
        visited = [False] * (n + 1) 
        
        # 1번 송전탑부터 출발해서 연결된 송전탑이 총 몇 개인지 bfs 대원에게 세어오라고 시킵니다.
        # (꼭 1번이 아니어도 무방하지만, 무조건 존재하는 번호이므로 1번을 쓴 것입니다.)
        cnt = bfs(1, graph, visited) 
        
        # 4. 두 그룹의 송전탑 개수 차이를 계산합니다.
        # 한쪽이 cnt개라면, 다른 쪽은 (전체 n개 - cnt개)입니다.
        # abs()는 음수가 나와도 양수로 바꿔주는 절댓값 기호입니다. (예: abs(-3) = 3)
        diff = abs(cnt - (n - cnt)) 
        
        # 지금까지 구한 차이(answer)와 방금 구한 차이(diff) 중 더 작은 것을 정답으로 갱신합니다.
        answer = min(answer, diff) 
        
    return answer
    
    