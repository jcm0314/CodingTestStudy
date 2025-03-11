def solution(num_list, n):
    if n in num_list:
            return 1  # n을 찾으면 즉시 1 반환
    return 0  # 전체 탐색 후 n이 없으면 0 반환
