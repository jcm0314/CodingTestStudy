def bs(li, n):
    s, e = 0, len(li)-1
    while s <= e:
        m = (s+e)//2
        if li[m] == n:
            return 1
        elif li[m] < n:
            s = m+1
        else:
            e = m-1
    return 0
        
for _ in range(int(input())):
    N = int(input())
    li1 = sorted(list(map(int, input().split())))
    M = int(input())
    li2 = list(map(int, input().split()))
    for n in li2:
        print(bs(li1, n))