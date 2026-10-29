# 일단 bfs로 풀어야 하는데,,,,,,하 어렵네 역시 레벨 3인가. 사실 어렵다기보다는 귀찮은 느낌
# 일단.... rec받은 값들에서 가장 모서리 부분만 지나갈 수 있는 거 뽑고
# 움직일 때마다 count ++ 해주면 되겠고
# bfs 로직은 종료조건 완성될때까지 반복문 돌리고 이게 방향이 두개니깐 먼저 찾으면 종료하는 느낌
# 종료 조건은 item이랑 character 위치 같을 떄
# 음 아 여기서 이거 가장 자리인 거 어떻게 분별하지? 이게 좀 귀찮네 아마 이것때문에 레벨 3인듯 하네
# 일단 방향 4방향이랑 맵 생성 코드 넣고 count = 0으로 할당해주고
# 음 테두리 분별을 어떻게 할거냐가 관건인데, 이거는 테두리만 1로 채우고 내부는 2로 해야 할듯? 그래서 1이고 vistied 안된 경우 count++ 하면서 전진하면 될 듯함
# 그런데 이 문제 지금 보니 함정이 있네, 이거 맵 크기 곱하기 2 해야하네.이거 겹치는 경우는 테두리 1이여서 지름길 사용가능하게되면서 로직 파괴되는구나 아 이거 어쩐지 테스트 케이스가 틀린 이유가 있네. 이것 때문에 레벨 3이구나, 레벨 4는 얼마나 함정이 많은거야
# 그러면 곱하기 2하면 중간에 공간 생겨서 여기 0 해주면 bfs가 못가네 오키 확인완료
from collections import deque # 디큐 쓰자

def solution(rectangle, characterX, characterY, itemX, itemY):
    # 0. 2배 이벤트로 맵 102 102 생성(원래는 5151 이였음) 내다버린 30분
    board = [[0] * 102 for _ in range(102)]
    
    # 1. 직사각형 테두리 및 내부 표시
    for x1, y1, x2, y2 in rectangle:
        # 좌표 2배 확대
        x1, y1, x2, y2 = x1 * 2, y1 * 2, x2 * 2, y2 * 2
        for i in range(x1, x2 + 1):
            for j in range(y1, y2 + 1):
                # 테두리면 1 (단, 다른 사각형의 내부가 아닐 때만)
                if x1 < i < x2 and y1 < j < y2:
                    board[i][j] = 2 # 사각형 내부는 2
                elif board[i][j] != 2:
                    board[i][j] = 1 # 테두리는 1
                    
    # 2. BFS 탐색 준비
    q = deque([(characterX * 2, characterY * 2, 0)])
    visited = [[False] * 102 for _ in range(102)] # 여기서 [False] * 102 * 102하면 안됨, 102개가 독립적 배열이 아니라, 모두 같은 하나의 행 메모리 주소 참조하게 되서 [0][0] = true 로 바뀌면 [n][0]도 True가 됨, 와 함정 너무 많은데;
    visited[characterX * 2][characterY * 2] = True
    
    dx = [-1, 1, 0, 0]
    dy = [0, 0, -1, 1]
    
    # 3. BFS 실행
    while q:
        x, y, dist = q.popleft()
        
        # 아이템 도착 시 2배 확대했던 거리 원복(//2)
        if x == itemX * 2 and y == itemY * 2:
            return dist // 2
        
        for i in range(4):
            nx, ny = x + dx[i], y + dy[i]
            
            if (0 <= nx < 102) and (0 <= ny < 102):
                    if board[nx][ny] == 1 and not visited[nx][ny]:
                        visited[nx][ny] = True
                        q.append((nx, ny, dist + 1))
                        
    return 0
    
    
    # 흠, 왜 안되는거지? 다시 봐도 이유를 모르겠네? 아 들여쓰기 잘못해서 안되는거였네 20분 내다버렸다
    