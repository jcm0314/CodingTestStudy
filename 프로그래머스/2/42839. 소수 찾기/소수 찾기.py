def is_prime(n):
    count = 0
    for i in range(1, n+1):
        if n % i == 0:
            count += 1
    return count == 2


def solution(numbers):
    n = len(numbers) # numbers 갯수
    visited = [False] * n # 방문 체크
    candidate_numbers = set() # 숫자들 중복 제거 하기 위해 ex) 011 이랑 11은 중복됨
    
    # 1. 숫자 조합 만들기(DFS)
    def dfs(current_str):
        
        # 지금까지 만든 글자가 빈 문자열이 아니면 정수로 바꿔서 집합에 추가
        if current_str:
            candidate_numbers.add(int(current_str))
            
        # 전체 종이 조각들을 처음부터 끝까지 훑어봄
        for i in range(n):
            if not visited[i]:
                visited[i] = True
                dfs(current_str + numbers[i])
                visited[i] = False
    dfs("")
    
    
    # 2. 소수 판별하기
    answer = 0
    for num in candidate_numbers:
        if is_prime(num):
            answer += 1
    
    return answer