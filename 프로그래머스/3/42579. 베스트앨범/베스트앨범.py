# 오답이여서 정답 보고 수정

def solution(genres, plays):
    # 0. 결과를 담을 리스트와 장르별 정보를 모아둘 딕셔너리 2개 
    answer = []
    genre_total = {}
    genre_songs = {}
    
    # - genre_total: {장르: 총 재생수}
    # - genre_songs: {장르: [(고유번호, 재생수), ...]}
    
    # 1. 고유번호(idx), 장르(genre), 재생수(play)를 묶어서 순회
    # - genre_total에 해당 장르의 재생수 누적 합산 (처음 등장 시 0으로 시작)
    # - genre_songs에 장르별 빈 리스트 생성 후 (idx, play) 튜플 추가
    for idx, (genre, play) in enumerate(zip(genres, plays)):
        genre_total[genre] = genre_total.get(genre, 0) + play
        if genre not in genre_songs:
            genre_songs[genre] = []
        genre_songs[genre].append((idx, play))
    
    # 2. 정렬 기준 함수(Named function) 2개 정의
    # - by_total(genre): 장르의 총 재생수를 반환하는 함수
    def by_total(genre):
        return genre_total[genre]
    # - by_song_priority(song): 
    #     song[1](재생수) 내림차순을 위한 음수 부호(-song[1]),
    #     song[0](고유번호) 오름차순을 위한 양수(song[0])를 튜플로 반환
    def by_song_priority(song):
        return (-song[1], song[0])
    
    # 3. 장르별 총 재생수 기준 내림차순 정렬 (총 재생수가 큰 장르 순서로 추출)
    sorted_genres = sorted(genre_total.keys(), key = by_total, reverse=True)
    
    # 4. 정렬된 각 장르를 순회하며 베스트 앨범에 수록
    # - 해당 장르의 노래 목록을 재생수 내림차순, 고유번호 오름차순으로 정렬
    # - 상위 최대 2곡(슬라이싱 [:2])에서 고유번호(idx)만 꺼내 answer에 추가
    for genre in sorted_genres:
        songs = sorted(genre_songs[genre], key=by_song_priority)
        
        for idx, _ in songs[:2]:
            answer.append(idx)
    
    # 5. 최종 수록된 곡 고유번호 리스트 반환
    return answer 