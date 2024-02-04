def solution(s):
    char_count = {}
    
    # 문자열에서 각 문자의 등장 횟수를 세기
    for char in s:
        char_count[char] = char_count.get(char, 0) + 1
    
    # 등장 횟수가 1인 문자를 사전 순으로 정렬하여 answer에 추가
    answer = ''.join(char for char, count in sorted(char_count.items()) if count == 1)

    return answer
