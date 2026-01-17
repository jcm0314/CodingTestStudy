import java.util.*;
import java.io.*;

public class Main {
    static int M, N, K;
    static int[][] grid;
    static boolean[][] visited;
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};
    static ArrayList<Integer> areas = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken()); // 세로 (행)
        N = Integer.parseInt(st.nextToken()); // 가로 (열)
        K = Integer.parseInt(st.nextToken()); // 사각형 개수

        grid = new int[M][N];
        visited = new boolean[M][N];

        // 1. 사각형 영역을 1로 채우기
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());

            for (int y = y1; y < y2; y++) {
                for (int x = x1; x < x2; x++) {
                    grid[y][x] = 1;
                }
            }
        }

        // 2. 전체 그리드를 돌며 빈 영역(0) 탐색
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                if (grid[i][j] == 0 && !visited[i][j]) {
                    areas.add(bfs(i, j));
                }
            }
        }

        // 3. 결과 정렬 및 출력
        Collections.sort(areas);
        System.out.println(areas.size());
        for (int area : areas) {
            System.out.print(area + " ");
        }
    }

    // 4. BFS를 이용한 영역 넓이 계산
    static int bfs(int y, int x) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{y, x});
        visited[y][x] = true;
        int count = 1;

        while (!queue.isEmpty()) {
            int[] curr = queue.poll();

            for (int i = 0; i < 4; i++) {
                int ny = curr[0] + dy[i];
                int nx = curr[1] + dx[i];

                if (ny >= 0 && ny < M && nx >= 0 && nx < N) {
                    if (grid[ny][nx] == 0 && !visited[ny][nx]) {
                        visited[ny][nx] = true;
                        queue.add(new int[]{ny, nx});
                        count++;
                    }
                }
            }
        }
        return count;
    }
}