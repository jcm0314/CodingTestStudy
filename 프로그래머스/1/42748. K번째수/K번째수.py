def solution(array, commands):
    answer = []
    # 1. i부터 j까지 슬라이싱한다
    for command in commands:
        i, j, k = command[0], command[1], command[2]
        
        sliced_array = array[i-1:j]
        
        sorted_array = sorted(sliced_array)
        
        answer.append(sorted_array[k-1])
        
    return answer
        
    # 2. 1에서 나온 결과를 오름차순으로 정렬한다
    # 3. 2에서 나온 결과를 k 번째 숫자를 뽑는다.
    
    