# [배운 점 정리]
# 1. lambda = 이름 없는 한 줄 함수. lambda 매개변수: 반환값  (return 안 씀)
#    sorted(..., key=함수) → 원소 하나씩 key 함수에 넣고, 나온 값 기준으로 정렬
#    기준 여러 개 → 튜플 반환: key=lambda x: (-x[1], x[0])  (앞 칸부터 비교, 같으면 다음 칸)
#    내림차순은 -값 또는 reverse=True
# 2. sum(t[1] for t in items) → 튜플 리스트에서 특정 칸만 더하기 (제너레이터 표현식)
#    주의: x[1][1]은 "두 번째 원소 하나"라서 sum 하면 엉뚱한 값이 나옴
# 3. set은 순서 보장 X. 문자열 해시가 실행마다 랜덤이라 print 순서가 매번 달라질 수 있음
#    (dict는 3.7부터 넣은 순서 유지 → setdefault / defaultdict(list) 쓰면 순서 고정)
# 4. 파이썬 정렬은 안정 정렬(stable): 기준값이 같으면 원래 순서 유지
#    → 번호 순으로 append 했으니 -x[1]만으로 정렬해도 동점은 번호 작은 순 (직접 구현 불필요했음)
# 5. 리스트[1]은 칸이 없으면 바로 IndexError (None 비교 전에 터짐)
#    리스트[:2]는 범위 넘어도 있는 만큼만 반환 → "최대 2개" 같은 조건에 딱


def solution(gengenre_dict, plays):
    answer = []
    genre_set = set(gengenre_dict)  # [3] set → 순서 랜덤
    genre_dict = {genre:[] for genre in genre_set}

    for i in range(len(plays)):
        genre_dict[gengenre_dict[i]].append((i, plays[i]))  # 번호 순으로 쌓임 → [4] 안정 정렬 전제

    # [1][2] 장르 총 재생 수 내림차순
    genre_dict = dict(sorted(genre_dict.items(), key=lambda x: -sum(i[1] for i in x[1])))


    for genre, tuple_lists in genre_dict.items():
        # [4] 동점이면 안정 정렬 덕분에 번호 작은 순 유지
        stl = sorted(tuple_lists, key= lambda x: -x[1])
        genre_dict[genre] = stl

    for stl in genre_dict.values():
        for t in stl[:2]:  # [5] 곡이 1개여도 에러 없음
            answer.append(t[0])
    
    return answer

# 1. 종합: 장르 4개, 동점 곡, 곡 1개짜리 장르
print(solution(
    ["rock", "jazz", "rock", "pop", "jazz", "rock", "pop", "hiphop", "jazz", "rock"],
    [300, 500, 300, 1000, 500, 800, 200, 50, 400, 100]
))  # 기댓값 [5, 0, 1, 4, 3, 6, 7]

# 2. 함정: 장르 순서는 "최고 곡"이 아니라 "총합" 기준
print(solution(
    ["ballad", "edm", "edm", "edm", "ballad", "trot"],
    [10000, 3000, 3000, 3000, 1, 9500]
))  # 기댓값 [0, 4, 5, 1, 2]

# 3. 장르 안 재생 수 전부 동점 → 번호 작은 순
print(solution(
    ["k", "k", "k", "j"],
    [7, 7, 7, 30]
))  # 기댓값 [3, 0, 1]

# 4. 곡이 딱 하나
print(solution(["solo"], [10]))  # 기댓값 [0]
