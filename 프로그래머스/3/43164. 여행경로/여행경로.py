def solution(tickets):
    tickets.sort() # 일단 티켓 알파벳 순으로
    visited = [False] * len(tickets) # 일단 방문 배열 만들기
    
    
    def dfs(curr, route): # 이 문제는 dfs로 풀어야함, 매개변수는 현재 공항이랑 몇 개 거쳐서 왔는지가 필요함
        if len(route) == len(tickets) + 1: # 종료조건(티켓보다 1개 많으면 티켓 전부 소진한거
            return route # 경로 반환
        for idx, [start, end] in enumerate(tickets): # 티켓 인덱스로 편하게 하기 위해서 enumerate 사용
            if curr == start and not visited[idx]: # 티켓에서 현재 공항이랑 start 공항 같고, 아직 방문 곳 찾기
                visited[idx] = True # 방문 체크 해주기
                result = dfs(end, route +[end]) # result 반환으로 마지막 공항이 첫 공항이 되고 경로에 마지막 공항 추가
            
                if result: # 실패한다면 none을 반환했기에ㅇ 성공하면 result값 들고 바로 탈출, 그러면 visited[idx] = False 실행안됨
                    return result # result값 들고 ㅌㅌ
                visited[idx] = False # 실패하면 방문 취소하고 다시 티켓 찾기
        return None # 못 찾으면 None 반환하기
    return dfs("ICN", ["ICN"])
        