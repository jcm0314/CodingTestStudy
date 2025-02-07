def solution(arr):
    for i in range(len(arr)):
        if 50 <= arr[i] and arr[i] % 2 == 0:
            arr[i] = arr[i] // 2
        elif 50 >= arr[i] and arr[i] % 2 == 1:
            arr[i] = arr[i] * 2
    return arr