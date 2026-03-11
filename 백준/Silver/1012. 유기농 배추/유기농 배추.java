import java.util.*;
import java.io.*;

class Main {

    // (1) BFS 함수
    static void bfs(int sy, int sx, int[][] field, boolean[][] visited, int[] dy, int[] dx, int N, int M) {
        Queue<int[]> q = new ArrayDeque<>();
        visited[sy][sx] = true;
        q.offer(new int[]{sy, sx});

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int y = cur[0];
            int x = cur[1];

            for (int dir = 0; dir < 4; dir++) {
                int ny = y + dy[dir];
                int nx = x + dx[dir];

                // 범위 체크
                if (ny < 0 || ny >= N || nx < 0 || nx >= M) continue;

                // 배추 있고 아직 방문 X면 큐에 추가
                if (!visited[ny][nx] && field[ny][nx] == 1) {
                    visited[ny][nx] = true;
                    q.offer(new int[]{ny, nx});
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int M = Integer.parseInt(st.nextToken()); // 가로
            int N = Integer.parseInt(st.nextToken()); // 세로
            int K = Integer.parseInt(st.nextToken()); // 배추 개수

            int[][] field = new int[N][M];

            for (int i = 0; i < K; i++) {
                st = new StringTokenizer(br.readLine());
                int X = Integer.parseInt(st.nextToken());
                int Y = Integer.parseInt(st.nextToken());
                field[Y][X] = 1; // field[y][x]
            }

            boolean[][] visited = new boolean[N][M];
            int[] dy = {-1, 1, 0, 0};
            int[] dx = {0, 0, -1, 1};

            int answer = 0;

            // (2) 모든 칸을 돌면서 새 컴포넌트 발견 시 BFS
            for (int y = 0; y < N; y++) {
                for (int x = 0; x < M; x++) {
                    if (!visited[y][x] && field[y][x] == 1) {
                        bfs(y, x, field, visited, dy, dx, N, M);
                        answer++;
                    }
                }
            }

            sb.append(answer).append('\n');
        }

        System.out.print(sb.toString());
    }
}
