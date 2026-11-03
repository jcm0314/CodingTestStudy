# ===== 수정한 풀이: 날짜를 "일" 단위 정수로 변환 (모든 달은 28일) =====
def solution(today, terms, privacies):
    today_int = parse_date(today)
    answer = []
    rule_expire_dict = {}
    for term in terms:
        rule_sort, month = term.split(" ")
        month_int = int(month)
        rule_expire_dict[rule_sort] = month_int * 28

    for i, privacy in enumerate(privacies):
        agree_date, rule_sort = privacy.split(" ")
        agree_date_int = parse_date(agree_date)
        agree_date_int += rule_expire_dict[rule_sort]

        if today_int >= agree_date_int: answer.append(i+1)

    return answer

def parse_date(date_string):
    year, month, day = map(int, date_string.split("."))
    return year * 12 * 28 + month * 28 + day


# ===== 원래 풀이 (테스트 16~19 실패) =====
# 실패 원인: 유효기간을 "개월 * 100"으로 YYYYMMDD에 더하면
# 월 + 유효기간이 100을 넘을 때 연도 자리로 넘쳐버림 (유효기간 최대 100개월)
# 예) 2020.05.10 + 100개월 -> 20210510 (정답: 20280910)
#
# def solution(today, terms, privacies):
#     today_int = parse_date(today)
#     answer = []
#     rule_expire_dict = {}
#     for term in terms:
#         rule_sort, month = term.split(" ")
#         month_int = int(month)
#         rule_expire_dict[rule_sort] = month_int * 100
#
#     for i, privacy in enumerate(privacies):
#         agree_date, rule_sort = privacy.split(" ")
#         agree_date_int = parse_date(agree_date)
#         agree_date_int += rule_expire_dict[rule_sort]
#         agree_date_int = check_overmonth(agree_date_int)
#
#         if today_int >= agree_date_int: answer.append(i+1)
#
#
#     return answer
#
# # 약관은 rule이라고 칭함
# # 날짜 파싱 함수 필요
# # 오늘날짜 파싱 -> 천만자리 정수로 분류 저장
# # 개인정보 띄어쓰기 파싱(날짜 , 약관 종류) -> 날짜 . 파싱(함수이용) -> 약관 set저장 -> 약관별로 최댓값만 저장 -> 파기기한 더하기
#
# def parse_date(date_string):
#     date_list = date_string.split(".")
#     date_int = 0; weight = 10000;
#     for date in date_list:
#         date_int += int(date) * weight
#         weight = weight // 100
#     return date_int
#
# def check_overmonth(date_int):
#     year = date_int // 10000; month_day = date_int % 10000
#     while month_day >= 1300:
#         month_day -= 1200; year += 1;
#     return year * 10000 + month_day
