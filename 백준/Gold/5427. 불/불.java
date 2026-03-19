
import java.io.*;
import java.util.*;

// Main 클래스
public class Main {
    // dx, dy: 4방향 상하좌우
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};
    
    // BFS용 큐: int[3] {y, x, type} type=0(불), type=1(상근이)
    static Queue<int[]> q = new LinkedList<>();
    
    public static void main(String[] args) throws IOException {
        // 빠른 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        // 테스트 케이스 수
        int tc = Integer.parseInt(br.readLine());
        
        // tc만큼 반복
        for(int t=0; t<tc; t++) {
            // 큐 초기화
            q.clear();
            // w(가로), h(세로) 입력
            st = new StringTokenizer(br.readLine());
            int w = Integer.parseInt(st.nextToken());
            int h = Integer.parseInt(st.nextToken());
            
            // char 맵[h][w]
            char [][] map = new char[h][w];
            int sy = 0, sx =0; // 상근이 위치
            
            // h줄 입력하며 불 위치 큐에 넣기(type=0), 상근이 위치 저장
            for(int i = 0; i < h; i++) {
                String line = br.readLine();
                for(int j=0; j <w; j++) {
                    map[i][j] = line.charAt(j);
                    if(map[i][j] == '*') { // 불 발견
                        q.offer(new int[]{i, j, 0});
                    } else if(map[i][j] == '@') { // 상근이 발견: 위치 저장, 맵 '.'으로 변경
                        sy = i; sx =j;
                        map[i][j] = '.';
                    }
                }
            }
            
            // 상근이 큐에 추가(type=1): 불보다 늦게 시작
             q.offer(new int[]{sy, sx, 1});
            // int 방문[h][w]: -1(미방문), 0 이상이면 도착 시간 <- 이게 이 문제의 킥~!
             int[][] time = new int[h][w];
             for(int i =0; i < h; i++) Arrays.fill(time[i], -1);
            // 상근이 시작 시간 0으로 설정
             time[sy][sx] = 0;
            
            // BFS 시작
            int ans = 0; // 최소시간
            boolean escape = false;
            
            while(!q.isEmpty()) {
                int[] cur = q.poll();
                int cy = cur[0], cx = cur[1], ctype = cur[2];
                int ctime = time[cy][cx]; // 현재 위치(cy,cx)에 도착한 시간
            // 상근이 탈출
                if(ctype == 1) {
                    if(cy == 0 || cy == h-1 || cx == 0 || cx == w-1) {
                        ans = ctime + 1;
                        escape = true;
                        break;
                    }
                }
            
                // 4방향 탐색
                for(int d=0; d<4; d++) {
                    int ny = cy + dy[d];
                    int nx = cx + dx[d];
                    
                    // 범위 밖
                    if(ny < 0 || ny >= h || nx < 0 || nx >= w) continue;
                    
                    // 벽
                    if(map[ny][nx] == '#') continue;
                    
                    // 이미 방문 또는 더 빠른 시간 있음
                    if(time[ny][nx] != -1 && time[ny][nx] <= ctime + 1) continue;
                    
                    // 불이면 상근이 이동 불가 체크
                    if(ctype == 1 && map[ny][nx] == '*') continue;
                    
                    // 불 퍼뜨리기: map에 '*' 표시
                    if(ctype == 0) map[ny][nx] = '*';
                    
                    // 시간 기록 및 큐 추가
                    time[ny][nx] = ctime + 1;
                    q.offer(new int[]{ny, nx, ctype});
                }
            }
            
            // 결과 출력
            if(escape) {
                System.out.println(ans);
            } else {
                System.out.println("IMPOSSIBLE");
            }
        }
    }
}

