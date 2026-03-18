import java.io.*;
import java.util.*;

// 백준 7569 토마토 (3차원 BFS)
public class Main {

    // M: 가로(열), N: 세로(행), H: 높이(층)
    static int M, N, H;

    // 토마토 상태 저장 3차원 배열 [층][행][열]
    // 값: 1(익은 토마토), 0(안 익은 토마토), -1(빈 칸)
    static int[][][] tomato;

    // 방문 여부 체크 배열
    static boolean[][][] visited;

    // 6방향 이동 (위, 아래, 앞, 뒤, 왼, 오른)
    // dz: 층 변화, dx: 행 변화, dy: 열 변화
    static int[] dz = {0, 0, 0, 0, 1, -1};
    static int[] dx = {1, -1, 0, 0, 0, 0};
    static int[] dy = {0, 0, 1, -1, 0, 0};

    // BFS에 사용할 큐 (익은 토마토들의 좌표를 넣는다)
    static Queue<Point> q = new LinkedList<>();

    // 며칠이 지났는지(레벨 BFS에서 일 수를 센다)
    static int days = 0;

    // 한 칸의 위치를 나타내는 클래스 (층, 행, 열)
    static class Point {
        int z, x, y;
        Point(int z, int x, int y) {
            this.z = z;
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        // 1. 첫 줄 입력: M(가로), N(세로), H(높이)
        st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        // 2. 3차원 배열 초기화
        tomato = new int[H][N][M];
        visited = new boolean[H][N][M];

        int unripeCount = 0; // 안 익은 토마토(0) 개수

        // 3. 입력으로 창고 상태 채우기
        for (int h = 0; h < H; h++) {       // 층
            for (int n = 0; n < N; n++) {   // 행
                st = new StringTokenizer(br.readLine());
                for (int m = 0; m < M; m++) { // 열
                    int value = Integer.parseInt(st.nextToken());
                    tomato[h][n][m] = value;

                    if (value == 1) {
                        // 이미 익어 있는 토마토는 BFS 시작점이 된다.
                        q.offer(new Point(h, n, m));
                        visited[h][n][m] = true;
                    } else if (value == 0) {
                        // 안 익은 토마토 개수 세기
                        unripeCount++;
                    }
                }
            }
        }

        // 4. 안 익은 토마토가 애초에 하나도 없으면 → 0 출력하고 끝
        if (unripeCount == 0) {
            System.out.println(0);
            return;
        }

        // 5. BFS 실행 (여러 개의 시작점에서 동시에 퍼져 나감)
        bfs();

        // 6. BFS가 끝난 뒤, 안 익은 토마토가 남아 있는지 다시 확인
        boolean allOk = true;
        for (int h = 0; h < H; h++) {
            for (int n = 0; n < N; n++) {
                for (int m = 0; m < M; m++) {
                    if (tomato[h][n][m] == 0) {
                        // 아직 안 익은 토마토가 남아 있다면 실패
                        allOk = false;
                        break;
                    }
                }
                if (!allOk) break;
            }
            if (!allOk) break;
        }

        // 7. 결과 출력
        if (!allOk) {
            // 모두 익지 못하는 상황
            System.out.println(-1);
        } else {
            // days는 마지막 날까지 세고 나서 +1 된 값이라 days - 1 출력
            System.out.println(days - 1);
        }
    }

    // BFS 함수: 하루(day)를 레벨 단위로 증가시키면서 토마토를 익힌다.
    static void bfs() {
        while (!q.isEmpty()) {

            // 현재 큐에 들어 있는 개수 = 오늘 익을 토마토 수
            int size = q.size();

            // 오늘 익을 토마토들을 전부 처리한다.
            for (int i = 0; i < size; i++) {
                Point cur = q.poll();
                int z = cur.z;
                int x = cur.x;
                int y = cur.y;

                // 6방향으로 인접 칸 탐색
                for (int dir = 0; dir < 6; dir++) {
                    int nz = z + dz[dir];
                    int nx = x + dx[dir];
                    int ny = y + dy[dir];

                    // 1) 창고 범위를 벗어나면 건너뛴다.
                    if (nz < 0 || nz >= H || nx < 0 || nx >= N || ny < 0 || ny >= M) {
                        continue;
                    }

                    // 2) 이미 방문했거나, 빈 칸(-1)이거나, 이미 익은 칸(1 이상)이면 건너뛴다.
                    if (visited[nz][nx][ny] || tomato[nz][nx][ny] != 0) {
                        continue;
                    }

                    // 3) 위 조건을 통과하면, 아직 안 익은 토마토(0)이므로 익게 만든다.
                    visited[nz][nx][ny] = true;
                    tomato[nz][nx][ny] = 1;        // 익은 상태로 변경
                    q.offer(new Point(nz, nx, ny)); // 다음에 퍼질 토마토로 큐에 추가
                }
            }

            // 현재 레벨에 대한 처리가 끝났으므로 하루가 지났다.
            days++;
        }
    }
}
