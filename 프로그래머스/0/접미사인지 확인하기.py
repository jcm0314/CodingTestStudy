def solution(my_string, is_prefix):
    if my_string.endswith(is_prefix):
        return 1
    else:
        return 0