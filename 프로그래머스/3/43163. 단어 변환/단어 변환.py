from collections import deque

def solution(begin, target, words):
    if target not in words:
        return 0
    
    return bfs(begin, target, words)

def bfs(begin, target, words):
    
    # 초기화
    queue = deque()
    queue.append([begin, 0])
    
    # 이 코드가 필요한가?
    # queue = set(deque)
    
    while queue:
        current, step = queue.popleft()
        
        if current == target:
            return step
        
        for word in words:
            count = 0
            for i in range(len(current)):
                if current[i] != word[i]:
                    count += 1
                
            if count == 1:
                queue.append([word, step + 1])

    
    
    
    