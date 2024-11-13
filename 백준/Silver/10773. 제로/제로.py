import sys

stack = []
total_sum = 0  # 합계를 저장할 변수

n = int(sys.stdin.readline())  # 명령의 수 입력받기

for _ in range(n):  # n번 반복
    command = sys.stdin.readline().strip()  # 명령 입력받기 (줄바꿈 제거)

    if command == '0':  # '0'이 입력되면
        if stack:  # 스택이 비어있지 않으면
            stack.pop()  # 최상단 요소 제거
    else:
        stack.append(int(command))  # 정수로 변환하여 스택에 추가

# 스택에 남아 있는 요소들의 합계 계산
total_sum = sum(stack)

print(total_sum)  # 합계 출력
