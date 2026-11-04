def solution(nums):
    # 일단 nums에 있는 것을 숫자로 대로 ++ 해야할듯? 이걸 해시로 연결
    # 뽑을 수 있는 경우는 nums/2
    # count[값]이 nums/2보다 많거나 같으면 nums/2 반환
    # 아닐 경우는 최대 count[값]을 반환
    
    max_pick = len(nums)//2
    
    # nums를 추출해서 count에 해싱하기
    count = {}
    for num in nums:
        if num in count:
            count[num] += 1
        else:
            count[num] = 1
    
    variety_count = len(count)
    
    return min(variety_count, max_pick)
    
    # 반환 값은 count[값]이 nums/2보다 많거나 같으면 nums/2 반환 아닐 경우는 count[유효값]의 개수 반환