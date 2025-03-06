def solution(arr, k):
    answer = []
    seen = set()  # 중복 방지를 위한 집합
    
    for num in arr:
        if num not in seen:  # 중복되지 않은 값만 추가
            answer.append(num)
            seen.add(num)
        if len(answer) == k:  # k개만 유지
            break
    
    while len(answer) < k:  # k개보다 부족하면 -1 채우기
        answer.append(-1)

    return answer
