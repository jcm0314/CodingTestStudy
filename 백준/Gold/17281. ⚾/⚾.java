import java.util.*;
import java.io.*;

public class Main {
    static int N;
    static int[][] players; // 각 이닝별 선수들의 결과
    static int[] order = new int[10]; // 타순 (1번~9번 타자)
    static boolean[] selected = new boolean[10]; // 순열 생성용 방문 배열
    static int maxScore = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        players = new int[N + 1][10];

        for (int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= 9; j++) {
                players[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 1번 선수는 4번 타자로 고정
        order[4] = 1;
        selected[1] = true;

        // 타순 정하기 시작 (1번 타자 자리부터 채움)
        makeOrder(1);
        System.out.println(maxScore);
    }

    static void makeOrder(int depth) {
        if (depth == 10) {
            playGame();
            return;
        }

        // 4번 타자는 이미 정해졌으므로 패스
        if (depth == 4) {
            makeOrder(depth + 1);
            return;
        }

        for (int i = 2; i <= 9; i++) {
            if (!selected[i]) {
                selected[i] = true;
                order[depth] = i;
                makeOrder(depth + 1);
                selected[i] = false;
            }
        }
    }

    static void playGame() {
        int score = 0;
        int currentBatter = 1; // 1번 타자부터 시작

        for (int i = 1; i <= N; i++) {
            int outCount = 0;
            boolean[] bases = new boolean[4]; // 1, 2, 3루 주자 여부

            while (outCount < 3) {
                int batterIdx = order[currentBatter];
                int result = players[i][batterIdx];

                if (result == 0) {
                    outCount++;
                } else {
                    score += moveRunners(bases, result);
                }

                currentBatter = (currentBatter % 9) + 1; // 다음 타자로 (9번 다음은 1번)
            }
        }
        maxScore = Math.max(maxScore, score);
    }

    static int moveRunners(boolean[] bases, int hit) {
        int point = 0;
        for (int i = 3; i >= 1; i--) { // 3루 주자부터 이동
            if (bases[i]) {
                if (i + hit >= 4) point++; // 홈인
                else bases[i + hit] = true;
                bases[i] = false;
            }
        }
        
        // 타자 본인 이동
        if (hit == 4) point++;
        else bases[hit] = true;
        
        return point;
    }
}