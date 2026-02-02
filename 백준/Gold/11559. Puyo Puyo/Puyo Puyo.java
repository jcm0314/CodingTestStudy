import java.util.*;
import java.io.*;

public class Main {
    static char[][] map = new char[12][6];
    static boolean[][] visited;
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};
    static int totalChains = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 0; i < 12; i++) {
            map[i] = br.readLine().toCharArray();
        }

        while (true) {
            boolean isPopped = false;
            visited = new boolean[12][6];

            // 1. 전체 맵을 돌며 터뜨릴 수 있는 뿌요 찾기
            for (int i = 0; i < 12; i++) {
                for (int j = 0; j < 6; j++) {
                    if (map[i][j] != '.' && !visited[i][j]) {
                        if (bfs(i, j)) {
                            isPopped = true; // 한 번이라도 터졌다면 표시
                        }
                    }
                }
            }

            // 2. 더 이상 터질 게 없다면 종료
            if (!isPopped) break;

            // 3. 터진 후 뿌요들을 아래로 떨어뜨리기
            dropPuyos();
            totalChains++; // 연쇄 횟수 증가
        }

        System.out.println(totalChains);
    }

    static boolean bfs(int r, int c) {
        Queue<int[]> queue = new LinkedList<>();
        ArrayList<int[]> puyoGroup = new ArrayList<>();
        
        char color = map[r][c];
        queue.add(new int[]{r, c});
        puyoGroup.add(new int[]{r, c});
        visited[r][c] = true;

        while (!queue.isEmpty()) {
            int[] curr = queue.poll();

            for (int i = 0; i < 4; i++) {
                int nr = curr[0] + dy[i];
                int nc = curr[1] + dx[i];

                if (nr >= 0 && nr < 12 && nc >= 0 && nc < 6) {
                    if (!visited[nr][nc] && map[nr][nc] == color) {
                        visited[nr][nc] = true;
                        int[] next = {nr, nc};
                        queue.add(next);
                        puyoGroup.add(next);
                    }
                }
            }
        }

        // 4개 이상 모였다면 터뜨림
        if (puyoGroup.size() >= 4) {
            for (int[] pos : puyoGroup) {
                map[pos[0]][pos[1]] = '.';
            }
            return true;
        }
        return false;
    }

    static void dropPuyos() {
        for (int j = 0; j < 6; j++) {
            Queue<Character> tempQueue = new LinkedList<>();
            // 아래서부터 위로 읽으며 뿌요만 큐에 담음
            for (int i = 11; i >= 0; i--) {
                if (map[i][j] != '.') {
                    tempQueue.add(map[i][j]);
                    map[i][j] = '.';
                }
            }
            // 다시 아래서부터 큐에 있는 뿌요들로 채움
            int idx = 11;
            while (!tempQueue.isEmpty()) {
                map[idx--][j] = tempQueue.poll();
            }
        }
    }
}