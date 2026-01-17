import java.util.*;
import java.io.*;

public class Main {
    // N: 세로 길이(행), M: 가로 길이(열)
    static int N, M;
    // maze: 실제 미로의 숫자 데이터(0 또는 1)를 저장하는 2차원 배열
    static int[][] maze;
    // visited: 특정 칸을 이미 방문했는지 체크하여 무한 루프를 방지하는 배열
    static boolean[][] visited;

    // 상하좌우 이동을 위한 방향 벡터 (y는 행 이동, x는 열 이동)
    // dy[0], dx[0] => (1, 0) : 아래로 이동
    // dy[1], dx[1] => (-1, 0) : 위로 이동
    // dy[2], dx[2] => (0, 1) : 오른쪽으로 이동
    // dy[3], dx[3] => (0, -1) : 왼쪽으로 이동
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};

    public static void main(String[] args) throws IOException {
        // BufferedReader: Scanner보다 입력 속도가 훨씬 빨라 코테에서 필수적입니다.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        // StringTokenizer: 한 줄에 공백으로 구분된 숫자들을 따로따로 분리할 때 사용합니다.
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 첫 번째 숫자는 N, 두 번째 숫자는 M에 저장
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        // 입력받은 크기대로 배열들 초기화
        maze = new int[N][M];
        visited = new boolean[N][M];

        // 미로 데이터 입력 받기 (숫자가 공백 없이 붙어서 들어옴: "101111")
        for (int i = 0; i < N; i++) {
            String line = br.readLine(); // "101111" 한 줄을 통째로 읽음
            for (int j = 0; j < M; j++) {
                // line.charAt(j): j번째 글자('1' 또는 '0')를 가져옴
                // - '0': 아스키코드 값을 이용해 문자 '1'을 숫자 1로 변환하는 기술
                maze[i][j] = line.charAt(j) - '0';
            }
        }

        // BFS 탐색을 (0, 0) 지점에서 시작하고, 결과(최단 거리)를 출력
        System.out.println(bfs(0, 0));
    }

    // BFS(너비 우선 탐색) 메소드
    static int bfs(int y, int x) {
        // Queue: 먼저 들어온 데이터를 먼저 꺼내는 자료구조 (BFS의 핵심)
        // int[] 배열을 담는 큐를 만들어 {y, x} 좌표를 저장함
        Queue<int[]> queue = new LinkedList<>();
        
        // 시작점을 큐에 넣고 방문 처리
        queue.add(new int[]{y, x});
        visited[y][x] = true;

        // 큐에 더 이상 방문할 곳이 없을 때까지 반복
        while (!queue.isEmpty()) {
            // 큐의 맨 앞(가장 먼저 들어온 것)을 꺼냄
            int[] curr = queue.poll();
            int curY = curr[0];
            int curX = curr[1];

            // 현재 위치에서 상, 하, 좌, 우 4방향을 검사
            for (int i = 0; i < 4; i++) {
                int ny = curY + dy[i]; // 다음에 갈 후보지 Row
                int nx = curX + dx[i]; // 다음에 갈 후보지 Col

                // [검사 1] 미로의 범위를 벗어나지 않는가? (배열 인덱스 에러 방지)
                if (ny >= 0 && ny < N && nx >= 0 && nx < M) {
                    
                    // [검사 2] 이동할 수 있는 길(1)인가? 
                    // [검사 3] 이전에 방문한 적이 없는가? (최단 거리를 위해 중요)
                    if (maze[ny][nx] == 1 && !visited[ny][nx]) {
                        
                        // 방문 처리: 이제 다시는 이 칸에 오지 않음
                        visited[ny][nx] = true;
                        
                        // 중요: 현재 칸까지의 거리 = 이전 칸까지의 거리 + 1
                        // maze 배열 자체에 누적 거리를 기록함 (1 -> 2 -> 3...)
                        maze[ny][nx] = maze[curY][curX] + 1;
                        
                        // 다음 탐색을 위해 큐에 새 좌표를 추가
                        queue.add(new int[]{ny, nx});
                    }
                }
            }
        }
        
        // 탐색이 모두 끝나면, 미로의 우측 하단(도착점)에 적힌 숫자가 최단 거리가 됨
        return maze[N - 1][M - 1];
    }
}