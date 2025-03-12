def solution(n):
    answer = []  # 빈 리스트로 초기화

    for i in range(n):
        row = []  # 새로운 행 초기화
        for k in range(n):
            if i == k:
                row.append(1)  # 대각선 요소에 1 추가
            else:
                row.append(0)  # 나머지 요소에 0 추가
        answer.append(row)  # 완성된 행을 answer 리스트에 추가

    return answer
