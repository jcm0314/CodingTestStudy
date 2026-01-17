import java.util.*;
import java.io.*;

public class Main {
    // N: 그리드의 크기 (N x N)
    static int N;
    // grid: 색상 정보(R, G, B)를 저장할 2차원 문자 배열
    static char[][] grid;
    // visited: 방문한 칸을 다시 방문하지 않도록 기록하는 체크 리스트
    static boolean[][] visited;
    // dx, dy: 상하좌우 네 방향으로 움직이기 위한 "방향 변수"
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};

    public static void main(String[] args) throws IOException {
        // BufferedReader: 입력을 빠르게 받기 위한 도구
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        // 첫 번째 줄의 N(크기)을 읽어서 숫자로 변환
        N = Integer.parseInt(br.readLine());

        // 배열의 크기를 N만큼 할당
        grid = new char[N][N];
        
        // 1. 데이터 입력 받기
        for (int i = 0; i < N; i++) {
            // 한 줄(예: RRGGB)을 통째로 읽어서 문자 배열(['R','R','G','G','B'])로 변환하여 저장
            grid[i] = br.readLine().toCharArray();
        }

        // --- [CASE 1] 일반 사람의 경우 ---
        // 방문 기록을 저장할 배열을 새로 만듭니다 (기본값은 전부 false)
        visited = new boolean[N][N];
        int normalCount = 0; // 일반 사람이 보는 영역의 개수 카운트

        // 그리드 전체를 한 칸씩 순회합니다 (이중 for문)
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                // 만약 아직 방문하지 않은 칸을 발견하면? -> 새로운 영역의 시작점!
                if (!visited[i][j]) {
                    bfs(i, j); // 해당 지점부터 연결된 같은 색깔을 모두 찾음
                    normalCount++; // 한 영역의 탐색이 끝났으므로 영역 개수 +1
                }
            }
        }

        // --- [CASE 2] 적록색약인 사람을 위한 처리 ---
        // 적록색약은 R(빨강)과 G(초록)를 똑같이 보므로, 도화지 자체를 수정합니다.
        // 모든 초록색(G)을 빨간색(R)으로 덮어씌워버리는 작업입니다.
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (grid[i][j] == 'G') {
                    grid[i][j] = 'R';
                }
            }
        }

        // --- [CASE 3] 적록색약인 사람의 경우 탐색 ---
        // 중요!! 일반인 탐색 때 썼던 방문 기록을 초기화해줘야 새로 탐색이 가능합니다.
        visited = new boolean[N][N]; 
        int colorBlindCount = 0; // 적록색약이 보는 영역의 개수 카운트

        // 수정된 그리드(R과 G가 합쳐진 상태)에서 다시 전체 탐색
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!visited[i][j]) {
                    bfs(i, j); // 같은 로직으로 탐색 (이미 G가 R로 바뀌어 있음)
                    colorBlindCount++;
                }
            }
        }

        // 결과 출력: "일반인 결과 <공백> 색약인 결과"
        System.out.println(normalCount + " " + colorBlindCount);
    }

    // BFS(너비 우선 탐색) 메소드: 연결된 같은 색상 덩어리를 모두 찾는 역할
    static void bfs(int y, int x) {
        // 큐(Queue): 방문할 칸들의 좌표를 순서대로 담아두는 바구니
        Queue<int[]> queue = new LinkedList<>();
        
        // 시작점을 큐에 넣고 방문 도장을 찍습니다.
        queue.add(new int[]{y, x});
        visited[y][x] = true;
        
        // color: 현재 탐색을 시작한 칸의 색깔 (R, G, B 중 하나)
        // 이 색깔과 같은 애들만 찾아다닐 예정입니다.
        char color = grid[y][x]; 

        // 큐가 빌 때까지(더 이상 연결된 같은 색깔이 없을 때까지) 반복
        while (!queue.isEmpty()) {
            // 현재 확인 중인 칸을 큐에서 꺼냅니다.
            int[] curr = queue.poll();
            int curY = curr[0];
            int curX = curr[1];

            // 상하좌우 4방향을 검사합니다.
            for (int i = 0; i < 4; i++) {
                int ny = curY + dy[i]; // 후보 행 위치
                int nx = curX + dx[i]; // 후보 열 위치

                // [검사 1] 후보지가 도화지(N x N) 범위를 벗어나지 않는지 확인
                if (ny >= 0 && ny < N && nx >= 0 && nx < N) {
                    // [검사 2] 아직 방문하지 않았는가?
                    // [검사 3] 후보지의 색깔이 탐색 시작점의 색깔(color)과 같은가?
                    if (!visited[ny][nx] && grid[ny][nx] == color) {
                        // 모든 조건 통과 시 방문 체크를 하고 큐에 넣습니다.
                        visited[ny][nx] = true;
                        queue.add(new int[]{ny, nx});
                    }
                }
            }
        }
    }
}