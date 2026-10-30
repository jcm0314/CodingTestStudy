# 국어 비문학 읽는 느낌이여서 문제 이해하는데 한 세월 걸렸다... 유튜브 숏츠 그만 봐야겠다. 멍청해졌다고 느껴지네
# 아니다, 걍 문제가 좀 단계적으로 뭐가 많네
# 크게 보면 일단 1. 도형 덩어리 추출하고...
# 2좌표 정규화 해주고...
# 3. 회전 구현? 해야할듯하네
# 4! 마지막으로 빈 공간과 매칭해주기? 하면 될듯한데 일단 진행해보자
# 음 일단 dfs, bfs 뭐로 풀어야할까? 이거 좀 고민되네 제한사항을 보고 생각해보자
# dfs로 풀면 50*50(최대 보드 행 길이 50이니) 이여서 2500인데, 파이썬 재귀 제한 1000번 걸릴 수 있으니, bfs가 안전해보이네. 그리고 최단거리 아니니깐, 큐 기반 bfs로 작성하면 안정적으로 고고
# 결국 시간내에 못 풀어서 답 봄

from collections import deque

# 1. 떵어리 추출
def solution(game_board, table):
    N = len(game_board)
    
    # 상, 하, 좌, 우로 이동하기 위한 방향 벡터
    dr = [-1, 1, 0, 0]
    dc = [0, 0, -1, 1]
    
    # 연결된 덩어리들을 찾아주는 함수
    def get_blocks(board, target):
        visited = [[False] * N for _ in range(N)]
        blocks = [] # 덩어리 여기에 넣어둘 예정
        
        for r in range(N):
            for c in range(N):
                if not visited[r][c] and board[r][c] == target:
                    queue = deque([(r, c)])
                    visited[r][c] = True
                    block = [(r, c)]
                    
                    while queue:
                        curr_r, curr_c = queue.popleft()
                        
                        # 상하좌우 확인
                        for i in range(4):
                            nr = curr_r + dr[i]
                            nc = curr_c + dc[i]
                            
                            # 범위 안 인가?
                            if 0 <= nr < N and 0 <= nc < N:
                                # 아직 방문 안했고 같은 번호 인가?
                                if not visited[nr][nc] and board[nr][nc] == target:
                                    visited[nr][nc] = True
                                    queue.append((nr, nc))
                                    block.append((nr, nc))
                    # 덩어리 탐색 끝났으니 넣기
                    blocks.append(normalize(block))
        return blocks # 여기하는데만 1시간...
    
    # 2. 좌표 정규화
    def normalize(block):
        min_r = min(r for r, c in block)
        min_c = min(c for r, c in block)
        
        # 모든 좌표에서 min_r과 min_c를 빼서 (0, 0) 기준으로 맞추고, sorted()로 정렬하기
        return sorted([(r - min_r, c - min_c) for r, c in block])
    
    def rotate(block):
        # 1. r, c -> c, -r 로 회전
        # 2. 회전 후 삐져나간 좌표를 normalize로 다시 (0, 0) 기준 정렬!
        return normalize([(c, -r) for r, c in block])
    
    # 1) 빈 공간(0)과 퍼즐 조각(1) 추출 
    spaces = get_blocks(game_board, 0)
    puzzles = get_blocks(table, 1)

    used = [False] * len(puzzles)
    answer = 0

    # 4. 매칭 시작
    for space in spaces:
        for p_idx, puzzle in enumerate(puzzles):
            # 이미 사용한 조각이거나, 칸 수가 다르면 모양 비교할 필요도 없이 패스
            if used[p_idx] or len(space) != len(puzzle):
                continue
            
            # 조각을 0도, 90도, 180도, 270도 총 4번 돌려보며 확인
            curr_puzzle = puzzle
            matched = False
            for _ in range(4):
                if space == curr_puzzle:  # 모양이 완벽히 일치하면!
                    used[p_idx] = True
                    answer += len(puzzle)  # 맞춘 칸 수 누적
                    matched = True
                    break
                curr_puzzle = rotate(curr_puzzle)  # 다음 각도로 회전
            
            # 이번 빈칸을 채웠다면 다음 퍼즐 조각은 더 볼 필요 없이 다음 빈칸으로
            if matched:
                break

    return answer
    
    