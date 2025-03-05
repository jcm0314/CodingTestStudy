def solution(arr):
    answer = []
    stk = []
    for i in range(len(arr)):
        if stk == []:
            stk.append(arr[i])
        elif stk != []:
            if arr[i]==stk[-1]:
                stk.pop()
            elif arr[i]!=stk[-1]:
                stk.append(arr[i])

    return stk or [-1]