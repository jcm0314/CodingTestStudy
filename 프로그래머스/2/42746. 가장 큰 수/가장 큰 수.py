def solution(numbers):
    
    answer=''
    # 그냥 sort 하면 10, 6, 2 이렇게 되는데.... 흠...
    # 정수 내림차순이 아닌, 문자열 내림차순으로 하면 해결될 듯
    numbers = list(map(str, numbers))
    
    # 맨 앞자리가 큰 순서대로 정렬
    numbers.sort(key=lambda x: x*3, reverse=True)
    
    # 다 정렬되면 그 값 이어붙이기
    for num in numbers:
        answer += num
        
    # 테스트 케이스 1개가 틀린 것을 보니, 예외 처리 해줘야할듯 아마 모든 숫자가 0일 때 인듯
    if numbers[0] == '0':
        answer = '0'
        
    return answer