import java.io.*;
import java.util.*;

public class Main {

    // 지도 크기 N (N x N)
    static int N;
    // 집 정보 저장할 배열 (0: 집 없음, 1: 집 있음)
    static int[][] map;
    // 방문 여부 체크 배열
    static boolean[][] visited;
    // 상, 하, 좌, 우 이동 방향
    static int[] dx = { -1, 1, 0, 0 };
    static int[] dy = { 0, 0, -1, 1 };

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 첫 줄: 지도의 크기 N
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        visited = new boolean[N][N];

        // N줄에 걸쳐 지도 정보 입력
        // 각 줄은 "01101" 처럼 공백 없이 들어옴
        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < N; j++) {
                // 문자 '0' 또는 '1'을 정수 0, 1로 변환
                map[i][j] = line.charAt(j) - '0';
            }
        }

        // 각 단지(연결된 집 묶음)의 집 개수를 저장할 리스트
        List<Integer> sizes = new ArrayList<>();

        // 전체 칸을 돌면서
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                // 집이 있고(1) 아직 방문하지 않은 경우 → 새로운 단지 시작
                if (map[i][j] == 1 && !visited[i][j]) {
                    // (i, j)를 시작점으로 BFS 돌려서 이 단지의 집 개수 구하기
                    int cnt = bfs(i, j);
                    // 구한 집 개수를 리스트에 추가
                    sizes.add(cnt);
                }
            }
        }

        // 단지 내 집의 개수를 오름차순으로 정렬
        Collections.sort(sizes);

        // 출력 형식에 맞게 StringBuilder로 구성
        StringBuilder sb = new StringBuilder();
        // 첫 줄: 단지의 총 개수
        sb.append(sizes.size()).append('\n');
        // 다음 줄들: 각 단지의 집 수 (오름차순)
        for (int size : sizes) {
            sb.append(size).append('\n');
        }

        System.out.print(sb.toString());
    }

    // (sx, sy)에서 시작해서 상하좌우로 연결된 집들을 BFS로 모두 탐색
    // → 해당 단지에 속한 집의 개수를 리턴
    static int bfs(int sx, int sy) {
        // int[0] = x(행), int[1] = y(열)
        Queue<int[]> q = new ArrayDeque<>();
        // 시작 위치 큐에 넣고 방문 처리
        q.offer(new int[]{sx, sy});
        visited[sx][sy] = true;
        // 시작 집 한 채 포함
        int count = 1;

        // 큐가 빌 때까지 반복 (너비 우선 탐색)
        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int x = cur[0];
            int y = cur[1];

            // 현재 위치에서 4방향으로 이웃 탐색
            for (int dir = 0; dir < 4; dir++) {
                int nx = x + dx[dir];
                int ny = y + dy[dir];

                // 배열 범위 벗어나면 스킵
                if (nx < 0 || ny < 0 || nx >= N || ny >= N) continue;

                // 집이 있고, 아직 방문하지 않았다면
                if (map[nx][ny] == 1 && !visited[nx][ny]) {
                    // 방문 처리 후 큐에 추가
                    visited[nx][ny] = true;
                    q.offer(new int[]{nx, ny});
                    // 같은 단지에 속한 집 개수 +1
                    count++;
                }
            }
        }

        // 이 BFS로 찾은 단지의 총 집 개수 반환
        return count;
    }
}
