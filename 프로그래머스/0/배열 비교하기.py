def solution(arr1, arr2):
    answer1 = sum(arr1)
    answer2 = sum(arr2)
    if len(arr1) < len(arr2):
        return -1
    elif len(arr1) > len(arr2):
        return 1
    elif len(arr1) == len(arr2):
        if answer1 > answer2:
            return 1
        elif answer1 < answer2:
            return -1
        elif answer1 == answer2:
            return 0