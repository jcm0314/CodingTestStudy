import java.io.*;
import java.util.*;

public class Main {

    // L: 층 수, R: 행 수, C: 열 수
    static int L, R, C;

    // 빌딩 정보를 담는 3차원 문자 배열
    // map[z][x][y] : z층, x행, y열의 문자('#', '.', 'S', 'E')
    static char[][][] map;

    // 방문 여부 체크 배열
    static boolean[][][] visited;

    // 6방향 이동 (위, 아래, 북, 남, 서, 동)
    // dz: 층 이동, dx: 행 이동, dy: 열 이동
    static int[] dz = {1, -1, 0, 0, 0, 0};
    static int[] dx = {0, 0, -1, 1, 0, 0};
    static int[] dy = {0, 0, 0, 0, -1, 1};

    // BFS에서 사용할 큐
    static Queue<Point> q = new LinkedList<>();

    // 한 위치 + 지금까지 걸린 시간(분)
    static class Point {
        int z, x, y, t;  // 층, 행, 열, 시간
        Point(int z, int x, int y, int t) {
            this.z = z;
            this.x = x;
            this.y = y;
            this.t = t;
        }
    }

    public static void main(String[] args) throws Exception {
        // TODO: 입력 여러 테스트케이스 처리
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        // 1. while(true)로 L, R, C 반복 입력
        while(true) {
            st = new StringTokenizer(br.readLine());
            L = Integer.parseInt(st.nextToken());
            R = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());
            
        // 2. 0 0 0이면 break
            if(L == 0 && R == 0 && C == 0) break;
        // 3. L, R, C에 맞게 map, visited 크기 할당
            map = new char[L][R][C];
            visited = new boolean[L][R][C];
            q.clear(); // 큐 초기화
            
            int startZ = -1, startX = -1, startY = -1; // 스타트 지점 초기화
            
        // 4. 빌딩 정보 입력받으면서 'S' 위치를 기억
            for (int z = 0; z < L; z++) {
                for (int x = 0; x < R; x++) {
                    String line = br.readLine();
                    for (int y = 0; y < C; y++) {
                        char ch = line.charAt(y);
                        map[z][x][y] = ch; // # / S E 정하기
                        
                        if (ch == 'S') { // 스타트 지점 정하기
                            startZ = z;
                            startX = x;
                            startY = y;
                        }
                    }
                }
                br.readLine();
            }
        // 5. bfs(startZ, startX, startY) 호출
            String answer = bfs(startZ, startX, startY);
        // 6. bfs에서 결과(String)를 받아서 출력
            sb.append(answer).append('\n');
        }
        System.out.print(sb.toString());
    }

    // BFS 함수: 시작 위치에서 출구 'E'까지 최단 시간 찾기
    static String bfs(int sz, int sx, int sy) {
        // TODO:
        // 1. 큐 초기화, visited 초기화
        for (int z = 0; z < L; z++) {
            for (int x = 0; x < R; x++) {
                Arrays.fill(visited[z][x], false);
            }
        }
        q.clear();
        
        // 2. 시작점 큐에 넣고 visited=true
        q.offer(new Point(sz, sx, sy, 0));
        visited[sz][sx][sy] = true;
        
        // 3. while(!q.isEmpty())로 BFS
        while (!q.isEmpty()) {
            Point cur = q.poll();

            // 현재 칸이 'E'면 탈출 성공
            if (map[cur.z][cur.x][cur.y] == 'E') {
                return "Escaped in " + cur.t + " minute(s).";
            }

            // 6방향 탐색
            for (int dir = 0; dir < 6; dir++) {
                int nz = cur.z + dz[dir];
                int nx = cur.x + dx[dir];
                int ny = cur.y + dy[dir];

                // 범위 체크
                if (nz < 0 || nz >= L || nx < 0 || nx >= R || ny < 0 || ny >= C) {
                    continue;
                }
                // 벽이거나 이미 방문한 곳이면 스킵
                if (visited[nz][nx][ny] || map[nz][nx][ny] == '#') {
                    continue;
                }

                visited[nz][nx][ny] = true;
                q.offer(new Point(nz, nx, ny, cur.t + 1));
            }
        }

        // 큐가 빌 때까지 'E'를 못 찾음 → 탈출 불가
        return "Trapped!";
    }
}
