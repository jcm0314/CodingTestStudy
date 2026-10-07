from collections import deque

def solution(begin, target, words):
    # target이 words에 없으면 0 반환하기
    if target not in words:
        return 0
    
    # 아닐경우 bfs 함수 실행하기
    return bfs(begin, target, words)

def bfs(begin, target, words):
    
    # 큐 만들기(그런데 여기서 왜 deque로 만들어야 하는지?)
    queue = deque() # deque 쓸려면 collections 에서 deque import 해와야 함
    queue.append([begin, 0]) # 첫번째 큐에 begin 하고 step 0 넣어주기
    
    while queue: # 값 찾을 때까지 반복
        current, step = queue.popleft() # 큐에서 값 꺼내서 과정 수행하기
        # 찾았을 경우 종료
        if current == target:
            return step # 현재 step 값 바로 리턴하기
        
        for word in words: # words에서 하나씩 꺼내기
            # count가 1인 값 큐에 넣기
            count = 0 # count 값 초기화
            for i in range(len(current)): # current 글자 갯수만큼 반복
                if current[i] != word[i]: # 한글자가 다르면
                    count += 1 # +1 하기
            if count == 1: # count 값이 1이면 큐에 넣기
                queue.append([word, step+1])
    