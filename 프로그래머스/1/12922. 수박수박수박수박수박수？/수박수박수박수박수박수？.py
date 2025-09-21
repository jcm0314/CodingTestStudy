def solution(n):
    answer = ''
    repeat = 0
    for i in range(n):
        if i % 2 == 0:
            answer += '수'
        # 인덱스가 홀수(1, 3, 5...)일 때 '박' 추가
        else:
            answer += '박'
    return answer