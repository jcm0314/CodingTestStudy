import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[][] board;
    static int[][] dist;
    static int[] dy = {-1,1,0,0};
    static int[] dx = {0,0,-1,1};


    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        board = new int[N][M];
        dist = new int[N][M];

        for (int i = 0; i < N; i++) {
            String line = br.readLine();

            for (int j = 0; j < M; j++) {
                board[i][j] = line.charAt(j) - '0';
            }
        }

        bfs();
        System.out.println(dist[N-1][M-1]);
    }

    static void bfs() {
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{0, 0});
        dist[0][0] = 1; 

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int y = cur[0];
            int x = cur[1];

            for (int dir = 0; dir < 4; dir++) {
                int ny = y + dy[dir];
                int nx = x + dx[dir];

                if (ny < 0 || ny >= N || nx < 0 || nx >= M)
                    continue;

                if (board[ny][nx] == 0) continue;

                if (dist[ny][nx] != 0) continue;

                dist[ny][nx] = dist[y][x] + 1;
                q.offer(new int[]{ny, nx});
            }
        }
    }
}