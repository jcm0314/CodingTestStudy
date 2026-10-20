def solution(answers):
    # 1. 각 수포자의 찍기 패턴
    p1 = [1, 2, 3, 4, 5]
    p2 = [2, 1, 2, 3, 2, 4, 2, 5]
    p3 = [3, 3, 1, 1, 2, 2, 4, 4, 5, 5]
    
    # 2. 맞힌 개수를 저장할 리스트
    scores = [0, 0, 0]
    
    # 3. 모든 문제 돌면서 정답 체크
    for i, ans in enumerate(answers):
        if ans == p1[i % len(p1)]:
            scores[0] += 1
        if ans == p2[i % len(p2)]:
            scores[1] += 1
        if ans == p3[i % len(p3)]:
            scores[2] += 1
            
    # 4. 가장 높은 점수 찾기
    max_score = max(scores)
    
    # 5. 최고 점수를 받은 사람 결과에 추가
    result = []
    for idx, scores in enumerate(scores):
        if scores == max_score:
            result.append(idx+1)
            
    return result