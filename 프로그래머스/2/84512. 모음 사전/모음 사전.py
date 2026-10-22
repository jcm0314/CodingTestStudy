def solution(word):
    
    # 필요한 것들 먼저
    vowels = ['A', 'E', 'I', 'O', 'U']
    count = 0
    answer = 0
    
    # dfs로 푸는게 맞다 이거는 그리고 visited 필요없음(단방향 트리)
    def dfs(current):
        # nonlocal로 밖에 있는 함수 참조해서 변경하도록
        nonlocal count, answer
        
        # 종료조건으로 word랑 current 같으면 count 반환
        if current == word:
            answer = count
            return 
        
        # 최대 길이가 5이므로, 5넘어가면 그냥 백트래킹 하면됨(5 넘어갔을 때 더 탐색하지 않음)
        if len(current) == 5:
            return
        
        # dfs 실행
        for v in vowels:
            count += 1 # 새 단어 생성할 때마다 카운트 ++
            # 현재값에 v 더해서 재귀 호출 시켜야함
            dfs(current + v)
       
    # 빈 문자열에서 시작
    dfs("")
    
    return answer