def solution(my_string, index_list):
    answer = ''
    for idx, val in enumerate(index_list):
        answer += my_string[val]
    return answer