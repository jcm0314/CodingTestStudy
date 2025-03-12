def solution(picture, k):

    answer = []

    for i in range(len(picture)):
        answer += [picture[i].replace('.', '.'*k).replace('x', 'x'*k)] * k

    return answer