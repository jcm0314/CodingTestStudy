'''
주어진 배열의 길이는 최대 20개 2^20은 백만 정도로 전체 경우의 수는 백만 정도
컴퓨터는 1초에 1억번 계산 가능하므로 완전 탐색으로 풀어도 됩니다
그러면 여기서 DFS vs BFS 인데, 모든 경로를 끝가지 탐색해서 타겟넘버와 일치하는 경우를 세워야 하잖아? 그래서 DFS로 메모리 효율도 좋고 구현 간결하니깐 푸는걸로

면접관 왈 : 왜 BFS로 안 풀었어? -> 최단 경로를 찾는 문제가 아니니깐, BFS로 굳이 풀어야 되나 싶음. DFS하고 BFS 공간 복잡도 차이랑 메모리 소비 생각하면 BFS보다 DFS로 푸는 것이 이득입니다. 그래서 DFS가 훨씬 적합하다고 생각했습니다. 이 문제는 모든 경우의 수를 세어야 하기 때문!!!!(논파)

'''
def solution(numbers, target):
    def dfs(index, current_sum):
        # index은 현재 처리할 숫자의 위치(0부터 스타또)
        # current_sum은 말 그대로 누적 합
        
        # 모든 숫자 탐색했을 때
        if index == len(numbers):
        # 모든 숫자 한번 씩 다 돌았을 때 누적합이 target이랑 일치하면 1가지 겟또한걸로 1반환 해야함 아니면 0 반환 해야할듯?
            return 1 if (current_sum == target) else 0
            
        
        # 재귀 반복하자잉
        # 더하기는 dfs(index+1, current_sum+numbers[index])
        # 빼기는 dfs(index+1, current_sum-numbers[index])
        return dfs(index+1, current_sum+numbers[index]) + dfs(index+1, current_sum-numbers[index])
    return dfs(0,0)
