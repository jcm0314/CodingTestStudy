import java.util.*;
import java.io.*;

public class Main {
    static int N, M;
    static int[][] maze;
    static boolean[][] visited;
    // 상하좌우 이동을 위한 방향 벡터
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        maze = new int[N][M];
        visited = new boolean[N][M];

        // 붙어서 들어오는 숫자를 하나씩 잘라서 배열에 저장
        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < M; j++) {
                maze[i][j] = line.charAt(j) - '0';
            }
        }

        // BFS 시작 (시작점 0, 0)
        System.out.println(bfs(0, 0));
    }

    static int bfs(int y, int x) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{y, x});
        visited[y][x] = true;

        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int curY = curr[0];
            int curX = curr[1];

            // 상하좌우 4방향 확인
            for (int i = 0; i < 4; i++) {
                int ny = curY + dy[i];
                int nx = curX + dx[i];

                // 1. 미로 범위 내에 있는가?
                if (ny >= 0 && ny < N && nx >= 0 && nx < M) {
                    // 2. 이동할 수 있는 길(1)인가? 3. 아직 방문하지 않았는가?
                    if (maze[ny][nx] == 1 && !visited[ny][nx]) {
                        visited[ny][nx] = true;
                        // 현재 칸까지의 거리 = 이전 칸의 거리 + 1
                        maze[ny][nx] = maze[curY][curX] + 1;
                        queue.add(new int[]{ny, nx});
                    }
                }
            }
        }
        
        // 도착점(N-1, M-1)에 기록된 최단 거리 반환
        return maze[N - 1][M - 1];
    }
}