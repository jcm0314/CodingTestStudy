def solution(word):
    
    vowels = ['A', 'E', 'I', 'O', 'U']
    count = 0
    answer = 0
    
    def dfs(current):
        nonlocal count, answer
        
        if current == word:
            answer = count
            return 
        
        if len(current) == 5:
            return
        
        for v in vowels:
            count += 1
            dfs(current + v)
            
    dfs("")
    
    return answer