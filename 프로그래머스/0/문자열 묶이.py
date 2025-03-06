def solution(strArr):
    count = [0] * 31  # 문자열 길이는 1~30이므로 리스트 크기를 미리 지정
    
    for s in strArr:
        count[len(s)] += 1  # 문자열 길이를 인덱스로 사용하여 카운트 증가
    
    return max(count)  # 가장 많이 등장한 길이의 개수 반환
