def solution(id_list, report, k):
    reportees = {}
    reporters = {}
    for report_row in report:
        reporter, reportee = report_row.split(" ")  # 틀림: report.split(" ") -> report는 리스트 전체라 split 불가 (AttributeError), 한 줄씩 담긴 report_row를 써야 함
        if reporter == reportee: continue
        if reportee not in reportees: reportees[reportee] = set()  # 틀림: reportees[reportee] is None -> 없는 키를 []로 꺼내면 None이 아니라 KeyError
        reportees[reportee].add(reporter)  # 틀림: 처음엔 리스트 + "이미 있으면 continue" -> set은 중복을 알아서 무시하고 in 검사도 빠름

    # 틀림: 처음엔 신고를 하나씩 읽으면서 k명이 되는 순간마다 신고자 전원에게 +1 -> k명 넘은 뒤 신고가 더 오면 메일이 여러 번 세짐
    #       문제 조건대로 "다 모은 다음 한꺼번에" 판단하도록 반복문을 둘로 나눔
    for reportee in reportees:  # 틀림: reportees.keys -> 괄호 없으면 함수 자체라 반복 불가 (TypeError). 딕셔너리는 그냥 돌려도 키가 나옴
        if len(reportees[reportee]) < k: continue
        for reporter in reportees[reportee]:
            if reporter not in reporters:
                reporters[reporter] = 0
            reporters[reporter] += 1
    answer = []
    for id in id_list:
        if id in reporters:  # 틀림: reporters[id] -> 신고를 한 번도 안 한 사람은 키가 없어서 KeyError, in으로 확인하고 0 넣기
            answer.append(reporters[id])
        else:
            answer.append(0)

    return answer

# ===== 테스트 (id_list / report / k -> 기대값) =====
# 입출력 예 #1:             ["muzi", "frodo", "apeach", "neo"] / ["muzi frodo", "apeach frodo", "frodo neo", "muzi neo", "apeach muzi"] / 2 -> [2, 1, 1, 0]
# 입출력 예 #2:             ["con", "ryan"] / ["ryan con", "ryan con", "ryan con", "ryan con"] / 3 -> [0, 0]
# k=1:                      ["a", "b", "c"] / ["a b", "b c"] / 1 -> [1, 1, 0]
# 중복 신고 (k 도달):       ["a", "b", "c"] / ["a c", "a c", "b c"] / 2 -> [1, 1, 0]
# 중복 신고 (k 미도달):     ["a", "b", "c"] / ["a c", "a c", "b c"] / 3 -> [0, 0, 0]
# k보다 더 많이 신고됨:     ["a", "b", "c", "d"] / ["a d", "b d", "c d"] / 2 -> [1, 1, 1, 0]
# 정지된 사람 여러 명 신고: ["a", "b", "c"] / ["a b", "c b", "a c", "b c"] / 2 -> [2, 1, 1]
# 아무도 정지 안 됨:        ["x", "y"] / ["x y"] / 2 -> [0, 0]
# 신고에 안 나온 유저:      ["a", "b", "c", "d", "e"] / ["b c", "d c"] / 2 -> [0, 1, 0, 1, 0]
# 큰 입력:                  ["u0" ~ "u999"] / ["u1 u0" ~ "u999 u0"] * 200 (199,800개) / 500 -> [0] + [1] * 999
