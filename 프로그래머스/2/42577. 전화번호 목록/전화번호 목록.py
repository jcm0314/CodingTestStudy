# 어떤 번호가 다른 번호의 접두어인 경우 -> false
# 어떤 번호가 다른 번호의 접두어가 아닐 경우 -> true
# 그러면 어떻게 구분? -> 딕셔너리로 묶어서? -> 다른 딕셔너리랑 겹치는지 하나하나 확인?
# phone_book 길이는 백만 이하 -> 완전탐색으로 풀면 안되기 때문에 다르게 풀기 -> 어차피 해시로 풀거여서 상관없어보임


def solution(phone_book):
    
    # 모든 전화번호를 set에 등록
    hash_set = set(phone_book)
    
    # 각 번호의 가능한 접두어가 해시에 존재하는지 확인
    for phone_number in phone_book:
        prefix = ""
        for number in phone_number[:-1]:
            prefix += number
            if prefix in hash_set:
                return False
    
    return True