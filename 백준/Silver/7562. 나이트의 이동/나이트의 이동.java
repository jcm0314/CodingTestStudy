import java.util.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    static int I;
    static int[][] dist;
    static boolean[][] visited;
    static int[] dx = {1, 2, 2, 1, -1, -2, -2, -1};
    static int[] dy = {2, 1, -1, -2, -2, -1, 1, 2};

    static int bfs(int sx, int sy, int ex, int ey) {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{sx, sy});
        visited[sx][sy] = true;

        if (sx == ex && sy == ey) return 0;

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int x = cur[0];
            int y = cur[1];

            for (int i = 0; i < 8; i++) {
                int nx = x + dx[i];   // ← x에서 이동
                int ny = y + dy[i];   // ← y에서 이동

                if (nx < 0 || ny < 0 || nx >= I || ny >= I) continue;
                if (visited[nx][ny]) continue;

                visited[nx][ny] = true;
                dist[nx][ny] = dist[x][y] + 1;

                if (nx == ex && ny == ey) {
                    return dist[nx][ny];
                }
                q.add(new int[]{nx, ny});
            }
        }
        return -1;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            I = Integer.parseInt(br.readLine());
            dist = new int[I][I];
            visited = new boolean[I][I];

            StringTokenizer st = new StringTokenizer(br.readLine());
            int sx = Integer.parseInt(st.nextToken());
            int sy = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(br.readLine());
            int ex = Integer.parseInt(st.nextToken());
            int ey = Integer.parseInt(st.nextToken());

            int ans = bfs(sx, sy, ex, ey);
            sb.append(ans).append('\n');
        }
        System.out.print(sb.toString());
    }
}

