import re

def solution(new_id):
    s = new_id
    s = s.lower()
    s = re.sub(r"[^a-z0-9\-_.]", "", s)
    s = re.sub(r"\.+", ".", s)
    s = re.sub(r"^\.|\.$", "", s)
    while len(s) < 3 or len(s) > 15:  # 틀림1: len(s) >= 3 and len(s) <= 15 -> 거꾸로라서 정상 길이면 무한루프 / 틀림2: len(s) < 3 and len(s) > 15 -> 항상 거짓이라 루프 실행 안 됨
        if len(s) == 0:
            s += "a"
        if len(s) >= 16:
            s = s[:15]
            s = re.sub(r"^\.|\.$", "", s)  # 틀림: 이 줄 빠뜨림 -> 자른 뒤 끝에 남은 점을 안 지움
        if len(s) <= 2:
            s += s[-1]  # 틀림: new_id += new_id[-1] -> s가 아니라 new_id를 바꿈

    return s
