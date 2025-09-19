def solution(arr):
    if len(arr) == 1:
        return [-1]
    else:
        min_value = min(arr) # 가장 작은 값 찾기
        arr.remove(min_value) # 해당 값을 제거
        return arr
