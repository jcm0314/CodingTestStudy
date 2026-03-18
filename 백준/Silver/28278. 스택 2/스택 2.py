# 1. N개 입력 받기
# 2. 명령 구현

import sys

stack = []

n = int(sys.stdin.readline()) # 9 입력받기

for _ in range(n): # 9번 돌기
    command = sys.stdin.readline().split() # 9번 동안 커맨드 입력하기

    if command[0] == '1': # 1일 때, 정수 X 스택에 넣기
        stack.append(int(command[1]))

    elif command[0] == '2':
        if not stack:
            print(-1)
        else:
            print(stack.pop())

    elif command[0] == '3':
        print(len(stack))

    elif command[0] == '4':
        if not stack:
            print(1)
        else:
            print(0)

    elif command[0] == '5':
        if stack:
            print(stack[-1])
        else:
            print(-1)


