# 🛠️ Spring · Tomcat · JVM · OS · 네트워크 트러블슈팅 통합 커리큘럼

> 토스증권 공고 "Spring Framework, Tomcat, JVM, OS, 네트워크, 인프라 등 다양한 레이어에서의 트러블슈팅 경험"을 겨냥한 보충 커리큘럼
> 메인 커리큘럼(Ch1~Ch6)이 먼저, 이건 시간 남을 때. Ch4와 겹치는 부분은 일부러 더 깊게 다룸.
> ⭐ = 필수 / 🔸 = 개념만 / 🔹 = 꼬리질문 수준

## 전체 지도 (약 22시간)

| 챕터 | 주제 | 시간 |
|---|---|---|
| T1 | 요청 하나의 여정 (네트워크 → Tomcat → Spring) | 3.5h |
| T2 | Spring 내부와 트랜잭션 | 3.5h |
| T3 | 스레드와 동시성 (OS + Java) | 3.5h |
| T4 | JVM 메모리와 GC | 3h |
| T5 | I/O와 네트워크 장애 | 3h |
| T6 | 관측과 트러블슈팅 방법론 | 2h |
| T7 | 종합 장애 시나리오 (서술형) | 2h |
| 모의 | 모의고사 2회 | 2.5h |

---

## T1. 요청 하나의 여정 (3.5h)

- [ ] T1-1 ⭐ 계층 구조 직관 (IP · 포트 · URL)
- [ ] T1-2 🔸 DNS 조회와 TTL, 캐싱
- [ ] T1-3 ⭐ TCP 3-way handshake / 4-way 종료
- [ ] T1-4 ⭐ HTTPS · TLS 핸드셰이크와 인증서
- [ ] T1-5 ⭐ 로드밸런서 L4 vs L7, 헬스체크, 스티키 세션
- [ ] T1-6 ⭐ HTTP keep-alive, HTTP/1.1 vs HTTP/2
- [ ] T1-7 ⭐ Tomcat 커넥터와 스레드 모델 (maxThreads, acceptCount)
- [ ] T1-8 🔸 Filter → DispatcherServlet → Interceptor → Controller
- [ ] T1-9 ⭐ 상태 코드로 장애 위치 읽기 (4xx vs 5xx, 429, 502 · 503 · 504)
- [ ] 💻 코딩 T1: **액세스 로그 분석기** — 로그 줄(시각, 경로, 상태 코드, 응답 시간)을 받아 경로별 요청 수, 5xx 비율, p50 · p99를 계산

## T2. Spring 내부와 트랜잭션 (3.5h)

- [ ] T2-1 ⭐ IoC · DI, 생성자 주입
- [ ] T2-2 ⭐ 싱글톤 빈과 공유 상태 (필드 상태 = Lost Update)
- [ ] T2-3 ⭐ AOP와 프록시 (JDK 동적 프록시 vs CGLIB)
- [ ] T2-4 ⭐ `@Transactional` 동작 원리 (프록시 + 트랜잭션 매니저 + ThreadLocal 커넥션)
- [ ] T2-5 ⭐ `@Transactional` 함정 (self-invocation, private, 체크 예외, 예외 삼키기)
- [ ] T2-6 🔸 전파 (REQUIRED vs REQUIRES_NEW), readOnly
- [ ] T2-7 🔸 JPA 영속성 컨텍스트, 변경 감지, flush 시점
- [ ] T2-8 ⭐ N+1 문제 (fetch join, EntityGraph, batch size, 페이징 함정)
- [ ] T2-9 🔸 OSIV와 커넥션 점유
- [ ] T2-10 🔸 JPA 락 (`@Lock`, `@Version`)과 재시도 위치
- [ ] 💻 코딩 T2: **N+1 탐지기** — 요청별 SQL 로그 목록을 받아, 같은 쿼리 형태(파라미터 제거 후)가 K번 이상 반복된 요청 ID와 반복 쿼리를 출력

## T3. 스레드와 동시성 (3.5h)

- [ ] T3-1 ⭐ 프로세스 vs 스레드 (메모리 공유, fork)
- [ ] T3-2 ⭐ 컨텍스트 스위칭과 스레드 수의 한계
- [ ] T3-3 🔸 CPU 스케줄링, 컨테이너 CPU throttling
- [ ] T3-4 ⭐ 경쟁 상태, 임계 구역
- [ ] T3-5 ⭐ 뮤텍스 vs 세마포어 vs 모니터(`synchronized`)
- [ ] T3-6 ⭐ `synchronized` vs `volatile` vs `AtomicLong`
- [ ] T3-7 🔸 ThreadLocal과 스레드 풀 재사용 누수
- [ ] T3-8 ⭐ 스레드 풀 설계 (Tomcat, `@Async`, HikariCP 크기 관계)
- [ ] T3-9 ⭐ 스레드 풀 고갈 도미노 계산 (리틀의 법칙)
- [ ] T3-10 ⭐ 데드락 · 기아 · 라이브락
- [ ] T3-11 🔸 스레드 덤프 읽기 (RUNNABLE · BLOCKED · WAITING)
- [ ] 💻 코딩 T3: **스레드 덤프 데드락 탐지기** — 스레드별 "가진 락 / 기다리는 락" 목록을 받아 상태별 스레드 수를 세고, 대기 그래프에서 순환(데드락)을 이루는 스레드들을 출력

## T4. JVM 메모리와 GC (3h)

- [ ] T4-1 ⭐ JVM 메모리 구조 (힙, 스택, 메타스페이스, 힙 밖 메모리)
- [ ] T4-2 ⭐ 세대별 GC (Young / Old, Minor / Full GC)
- [ ] T4-3 ⭐ Stop-The-World와 지연
- [ ] T4-4 🔸 G1 vs ZGC 직관
- [ ] T4-5 🔸 GC 로그 읽기
- [ ] T4-6 🔸 메모리 누수와 힙 덤프
- [ ] T4-7 ⭐ 가상 메모리, 페이지 폴트, 스왑
- [ ] T4-8 ⭐ 컨테이너 OOM (cgroup, OOM Killer, exit 137, MaxRAMPercentage)
- [ ] 💻 코딩 T4: **GC 멈춤 상관 분석기** — GC 멈춤 구간 목록과 요청 목록(시작 시각, 응답 시간)을 받아, 느린 요청(임계치 초과) 중 GC 멈춤과 겹치는 비율을 계산

## T5. I/O와 네트워크 장애 (3h)

- [ ] T5-1 ⭐ 블로킹 vs 논블로킹, 동기 vs 비동기
- [ ] T5-2 ⭐ select · poll · epoll, IOCP
- [ ] T5-3 ⭐ 이벤트 루프 (Netty)와 이벤트 루프 블로킹 금지
- [ ] T5-4 🔸 파일 디스크립터, ulimit, "Too many open files"
- [ ] T5-5 ⭐ TCP 상태: TIME_WAIT vs CLOSE_WAIT
- [ ] T5-6 ⭐ ephemeral 포트 고갈
- [ ] T5-7 ⭐ connect timeout vs read timeout, 타임아웃 미설정 함정
- [ ] T5-8 ⭐ 재시도, 지수 백오프, 지터, 멱등성
- [ ] T5-9 🔸 HTTP 클라이언트 커넥션 풀
- [ ] T5-10 🔹 Nagle과 TCP_NODELAY
- [ ] 💻 코딩 T5: **소켓 상태 분석기** — `ss -tan` 형식 출력을 받아 상태별 개수와, CLOSE_WAIT가 가장 많은 원격 주소 상위 K개를 출력

## T6. 관측과 트러블슈팅 방법론 (2h)

- [ ] T6-1 ⭐ 메트릭 · 로그 · 트레이스
- [ ] T6-2 ⭐ p50 · p99와 평균의 함정
- [ ] T6-3 🔸 분산 추적 (trace, span)
- [ ] T6-4 ⭐ 장애 대응 순서 (변경 이력 → 분산 추적 → tcpdump · thread dump) — 토스증권 서버 챕터 FAQ
- [ ] T6-5 ⭐ 사실과 가설 구분, 가설을 가르는 관측
- [ ] T6-6 ⭐ 롤백 vs 원인 분석 우선순위 판단
- [ ] T6-7 🔸 도구 모음 (ss, tcpdump, curl -v, top, vmstat, jstack, jstat)
- [ ] 💻 코딩 T6: **트레이스 병목 경로 찾기** — span 목록(id, 부모 id, 시작, 끝, 서비스명)을 받아 트리를 만들고, 루트에서 가장 오래 걸린 경로(critical path)를 출력

## T7. 종합 장애 시나리오 · 서술형 15분 (2h)

- [ ] T7-1 배포 30분 뒤 p99 200ms → 4초
- [ ] T7-2 p50은 정상인데 p99만 주기적으로 튐
- [ ] T7-3 외부 API 지연이 서비스 전체 장애로 번짐
- [ ] T7-4 Pod가 로그 없이 계속 재시작 (exit 137)
- [ ] T7-5 서버에 CLOSE_WAIT 수만 개
- [ ] T7-6 "Too many open files" 후 신규 요청 실패
- [ ] T7-7 로드밸런서에서 502 급증
- [ ] T7-8 DNS 전환 후 일부 사용자만 옛 서버로 접속

## 모의고사 (2.5h)

- [ ] 모의 1 (75분): 코딩 1(T1~T6 중) + 주관식 5 + 서술형 2(T7 중)
- [ ] 모의 1 오답 정리
- [ ] 모의 2 (75분): 다른 조합으로
- [ ] 모의 2 오답 정리