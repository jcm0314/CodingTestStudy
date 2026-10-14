def solution(people, limit):
    answer = 0
    # 1. 몸무게 오름차순으로 ㅈㅓㅇ렬
    people.sort()
    
    # 2. 양 끝을 가리키는 포인터
    left = 0
    right = len(people) - 1
    
    # 3. 모든 사람이 탈 때까지 반복
    while left <= right:
        # 가장 가벼운 사람과 가장 무거운 사람의 합이 무게 제한 이하
        if people[left] + people[right] <= limit:
            left += 1 # 가벼운 사람도 같이 탑승
            
        # 무거운 사람은 항상 탑승(혼자든 둘이든)
        right -= 1
        
        # 보트 사용횟수 증가
        answer += 1
    return answer
    
    return answer