// java.io.*: BufferedReader, InputStreamReader로 빠른 입력
// java.util.*: LinkedList(큐), StringTokenizer
import java.io.*;
import java.util.*;

// Main 클래스
public class Main {
    // dx, dy: 4방향 상하좌우
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};
    
    // BFS용 큐: int[3] {y, x, type}  type=0(불), type=1(지훈이)
    static Queue<int[]> q = new LinkedList<>();
    
    public static void main(String[] args) throws Exception {
        // 빠른 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        // R(세로), C(가로) 입력
        // 예: 4 4
        st = new StringTokenizer(br.readLine());
        int R = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());
        
        // 미로 맵[R][C]
        char[][] map = new char[R][C];
        // 지훈이 시작 위치
        int jy = 0, jx = 0;
        
        // R줄 입력 받으면서 불, 지훈이 위치 처리
        for (int i = 0; i < R; i++) {
            String line = br.readLine();
            for (int j = 0; j < C; j++) {
                // 한 글자씩 맵에 저장
                // '#': 벽, '.': 빈 칸, 'J': 지훈, 'F': 불
                map[i][j] = line.charAt(j);
                
                // 불이면: 큐에 먼저 넣기 (type=0)
                if (map[i][j] == 'F') {
                    q.offer(new int[]{i, j, 0});
                }
                // 지훈이면: 시작 위치 저장 후 맵은 빈 칸으로 바꾸기
                else if (map[i][j] == 'J') {
                    jy = i;
                    jx = j;
                    map[i][j] = '.';
                }
            }
        }
        
        // 모든 불을 큐에 넣은 뒤, 마지막에 지훈이 넣기 (type=1)
        q.offer(new int[]{jy, jx, 1});
        
        // int time[R][C]: -1(미방문), 0 이상이면 도착 시간
        // 불/지훈이 공통으로 사용하는 도착 시간 배열
        int[][] time = new int[R][C];
        // 전체를 -1로 초기화
        for (int i = 0; i < R; i++) Arrays.fill(time[i], -1);
        
        // 지훈이 시작 위치 도착 시간 0으로 설정
        time[jy][jx] = 0;
        
        // BFS 시작
        // ans: 지훈이 탈출까지 걸린 최소 시간
        int ans = 0;
        boolean escape = false;
        
        // 큐가 빌 때까지 반복
        while (!q.isEmpty()) {
            // 현재 위치, 타입 꺼내기
            // cur[0] = y, cur[1] = x, cur[2] = type(0=불, 1=지훈)
            int[] cur = q.poll();
            int cy = cur[0];
            int cx = cur[1];
            int ctype = cur[2];
            
            // 현재 칸에 도착한 시간
            int ctime = time[cy][cx];
            
            // 만약 지훈이(ctype == 1)라면 탈출 여부 먼저 체크
            // 지훈이가 한 칸 더 나가면 범위 밖으로 나가는지 확인 → 그때가 탈출
             if (ctype == 1) {
                 // 현재 위치가 이미 가장자리면, 한 번 더 나가면 밖으로 탈출
                 if (cy == 0 || cy == R - 1 || cx == 0 || cx == C - 1) {
                     ans = ctime + 1;
                     escape = true;
                     break;
                 }
             }
            
            // 4방향 탐색
            for (int d = 0; d < 4; d++) {
                int ny = cy + dy[d];
                int nx = cx + dx[d];
                
                 // (1) 지훈이인 경우: 범위 밖으로 나가면 탈출 성공
                  if (ctype == 1 && (ny < 0 || ny >= R || nx < 0 || nx >= C)) {
                      ans = ctime + 1;
                      escape = true;
                      break;
                  }
                
                 // (2) 범위 체크 (불은 범위 밖으로 못 나감)
                  if (ny < 0 || ny >= R || nx < 0 || nx >= C) continue;
                
                 // 벽이면 못 감
                  if (map[ny][nx] == '#') continue;
                
                 // 이미 방문했거나 더 빨리/같은 시간에 도착했으면 스킵
                  if (time[ny][nx] != -1 && time[ny][nx] <= ctime + 1) continue;
                
                 // 지훈이인데 다음 칸이 불이면 못 감
                  if (ctype == 1 && map[ny][nx] == 'F') continue;
                
                 // 불일 때: 다음 칸을 불로 바꾸기
                  if (ctype == 0) map[ny][nx] = 'F';
                
                 // 도착 시간 갱신하고 큐에 추가
                  time[ny][nx] = ctime + 1;
                  q.offer(new int[]{ny, nx, ctype});
             }
            
             if (escape) break;
        }
        
        // BFS 종료 후 결과 출력
        // escape가 true면 ans 출력, 아니면 IMPOSSIBLE
         if (escape) {
             System.out.println(ans);
         } else {
             System.out.println("IMPOSSIBLE");
         }
    }
}