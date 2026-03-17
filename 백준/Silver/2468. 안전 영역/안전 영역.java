import java.io.*;
import java.util.*;

public class Main {
    static int N;                           // 지도 크기 N×N
    static int[][] map;                     // 높이 정보 저장
    static boolean[][] visited;             // 현재 강수량 기준 방문 배열
    static int[] dy = {-1,1,0,0};          // 상하좌우 이동 방향 (y축)
    static int[] dx = {0,0,-1,1};          // 상하좌우 이동 방향 (x축)
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());   // 지도 크기 입력
        
        map = new int[N][N];
        int maxH = 0;                          // 지도 내 최대 높이
        
        // 지도 입력 + 최대 높이 계산
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                maxH = Math.max(maxH, map[i][j]);  // 모든 강수량 범위 미리 계산
            }
        }
        
        int answer = 1;                        // 최소 1개
        
        // 핵심: 강수량 h=0부터 maxH까지 완전탐색
        for (int h = 0; h <= maxH; h++) {
            visited = new boolean[N][N];       // 매 강수량마다 visited 새로 생성
            int cnt = 0;                       // 현재 h에서의 안전영역 개수
            
            // 모든 칸 검사하며 안전영역 개수 세기
            for (int y = 0; y < N; y++) {
                for (int x = 0; x < N; x++) {
                    // 아직 방문X + 물에 안 잠김(높이 > h) → 새로운 안전영역 발견!
                    if (!visited[y][x] && map[y][x] > h) {
                        cnt++;                     // 안전영역 +1
                        bfs(y, x, h);              // 연결된 영역 전부 방문 처리
                    }
                }
            }
            answer = Math.max(answer, cnt);    // 최대값 갱신
        }
        System.out.println(answer);            // 정답 출력
    }
    
    // BFS: 한 안전영역(연결된 모든 칸) 방문 처리
    static void bfs(int sy, int sx, int h) {
        Queue<int[]> q = new LinkedList<>();   // BFS용 큐
        visited[sy][sx] = true;                // 시작점 방문 처리
        q.offer(new int[]{sy, sx});            // 시작점 큐에 추가
        
        while (!q.isEmpty()) {
            int[] cur = q.poll();              // 현재 위치 꺼냄
            int y = cur[0];
            int x = cur[1];
            
            // 4방향 탐색
            for (int dir = 0; dir < 4; dir++) {
                int ny = y + dy[dir];
                int nx = x + dx[dir];
                
                // 범위 밖
                if (ny < 0 || ny >= N || nx < 0 || nx >= N) continue;
                // 이미 방문
                if (visited[ny][nx]) continue;
                // 물에 잠김 (h 이상)
                if (map[ny][nx] <= h) continue;
                
                // 안전한 인접 칸은 방문 처리 + 큐 추가
                visited[ny][nx] = true;
                q.offer(new int[]{ny, nx});
            }
        }
    }
}
