def solution(today, terms, privacies):
    today_int = parse_date(today)
    answer = []
    rule_expire_dict = {}
    for term in terms:
        rule_sort, month = term.split(" ")
        month_int = int(month)
        rule_expire_dict[rule_sort] = month_int * 28  # 틀림: month_int * 100 -> YYYYMMDD의 월 자리에 더하면 월+유효기간이 100 넘을 때 연도 자리로 넘침 (유효기간 최대 100개월)

    for i, privacy in enumerate(privacies):
        agree_date, rule_sort = privacy.split(" ")
        agree_date_int = parse_date(agree_date)
        agree_date_int += rule_expire_dict[rule_sort]
        # 틀림: agree_date_int = check_overmonth(agree_date_int) -> 13월 이상을 연도로 넘기는 함수였는데, 월이 100을 넘으면 이미 연도가 망가져서 못 고침 (일 단위로 바꾸면서 필요 없어짐)

        if today_int >= agree_date_int: answer.append(i+1)

    return answer

# 약관은 rule이라고 칭함
# 날짜 파싱 함수 필요
# 오늘날짜 파싱 -> 천만자리 정수로 분류 저장
# 개인정보 띄어쓰기 파싱(날짜 , 약관 종류) -> 날짜 . 파싱(함수이용) -> 약관 set저장 -> 약관별로 최댓값만 저장 -> 파기기한 더하기

def parse_date(date_string):
    year, month, day = map(int, date_string.split("."))
    return year * 12 * 28 + month * 28 + day  # 틀림: year*10000 + month*100 + day (YYYYMMDD) -> 모든 달이 28일이니 "일" 단위로 바꾸면 넘침 걱정 없음

# 틀림: 필요 없어진 함수 (13월 이상이면 1200 빼고 연도 +1)
# def check_overmonth(date_int):
#     year = date_int // 10000; month_day = date_int % 10000
#     while month_day >= 1300:
#         month_day -= 1200; year += 1;
#     return year * 10000 + month_day
