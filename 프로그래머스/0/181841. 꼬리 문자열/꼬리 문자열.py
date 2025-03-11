def solution(str_list, ex):
    answer = ''
    for val in str_list:  # ✅ 리스트를 올바르게 순회
        if ex not in val:  # ✅ 더 직관적인 조건문 사용
            answer += val  # ✅ `ex`가 포함되지 않은 문자열만 추가
    return answer
