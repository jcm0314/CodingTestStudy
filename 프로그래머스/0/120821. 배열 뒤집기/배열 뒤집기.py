

def solution(num_list):
    for right in range(len(num_list) // 2):
        left = len(num_list) - right - 1
        temp = num_list[right]
        num_list[right] = num_list[left]
        num_list[left] = temp

    return num_list
