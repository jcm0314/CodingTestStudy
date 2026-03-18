import java.util.*;
import java.io.*;

public class Main {
    static int n, m;
    static int[][] paper;       // 도화지 데이터
    static boolean[][] visited; // 방문했는지 체크하는 배열
    static int[] dx = {0, 0, 1, -1}; // 좌우 이동
    static int[] dy = {1, -1, 0, 0}; // 상하 이동

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken()); // 세로 크기
        m = Integer.parseInt(st.nextToken()); // 가로 크기

        paper = new int[n][m];
        visited = new boolean[n][m];

        // 1. 도화지 정보 입력 받기
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                paper[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int count = 0;    // 그림의 개수
        int maxArea = 0;  // 가장 넓은 그림의 넓이

        // 2. 모든 칸을 하나씩 확인
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                // 그림(1)인데 아직 확인 안 한 곳이라면? 새로운 그림 발견!
                if (paper[i][j] == 1 && !visited[i][j]) {
                    count++; // 그림 개수 증가
                    // BFS 탐색을 통해 이 그림의 넓이를 구함
                    maxArea = Math.max(maxArea, bfs(i, j));
                }
            }
        }

        System.out.println(count);
        System.out.println(maxArea);
    }

    // 3. 연결된 그림의 넓이를 구하는 BFS 메소드
    static int bfs(int y, int x) {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{y, x});
        visited[y][x] = true;
        
        int area = 1; // 현재 칸부터 시작하므로 넓이는 1부터 시작

        while (!q.isEmpty()) {
            int[] curr = q.poll();

            for (int i = 0; i < 4; i++) {
                int ny = curr[0] + dy[i];
                int nx = curr[1] + dx[i];

                // 도화지 범위 안에 있고
                if (ny >= 0 && ny < n && nx >= 0 && nx < m) {
                    // 그림(1)이면서 아직 방문 안 했다면
                    if (paper[ny][nx] == 1 && !visited[ny][nx]) {
                        visited[ny][nx] = true; // 방문 도장 쾅!
                        q.add(new int[]{ny, nx}); // 다음 탐색 후보로 추가
                        area++; // 넓이 1 증가
                    }
                }
            }
        }
        return area; // 최종 넓이 반환
    }
}