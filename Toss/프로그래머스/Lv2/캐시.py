import collections

def solution(cacheSize, cities):
    # 유의사항 : cacheSize 가 0일 수 있다. ==> 이런경우 all cache miss로 처리
    # 캐시 사이즈는 도시명 하나가 1로 본다.
    # [놓쳤던 점 1] 대소문자 구분 X → lower()로 통일해야 "NewYork"과 "newyork"을 같은 도시로 본다.
    # [놓쳤던 점 2] LRU는 hit일 때도 "사용"한 것 → 해당 항목을 맨 뒤(최신)로 옮겨야 한다.
    #              갱신을 안 하면 FIFO가 되어 오답 (예: solution(2, ["A","B","A","C","A"]) → 17이어야 함)
    finding_time = 0
    q = collections.deque(maxlen=cacheSize)
    for city_raw in cities:
        city = city_raw.lower()
        if city in q:
            finding_time  += 1
            #LRU 
            q.remove(city)
            q.append(city)
        else:
            finding_time += 5
            q.append(city)

    return finding_time

