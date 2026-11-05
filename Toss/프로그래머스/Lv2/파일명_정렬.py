import re

def solution(files):
    file_sort = {}
    for file in files:

        m = re.search(r"\d{1,5}", file)
        if m:
            start, end = m.span()
            head = file[:start].lower()
            number = int(file[start:end])
            if head not in file_sort:
                file_sort[head] = []
            file_sort[head].append([number, file])

    answer = []

    for key, vals in sorted(file_sort.items()):
        sorted_vals = sorted(vals, key=lambda x: x[0]) 
        #파이썬의 sorted는 **안정 정렬(stable sort)**이야. 비교 기준이 같은 값끼리는 원래 순서를 그대로 둬. 
        # vals에는 입력 순서대로 append했으니까, 숫자만 비교하게 하면 조건이 자동으로 지켜져.
        for atom in sorted_vals:
            answer.append(atom[1])

    return answer
            



solution(["img12.png", "img10.png", "img02.png", "img1.png", "IMG01.GIF", "img2.JPG", "F-5 Freedom Fighter", "B-50 Superfortress", "A-10 Thunderbolt II", "F-14 Tomcat"])



