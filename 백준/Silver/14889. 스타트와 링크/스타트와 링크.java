import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    static int N; // 사람수
    static int[][] S; // 맵
    static boolean[] visit; // 방문체크
    static int minDiff = Integer.MAX_VALUE; // 두팀 능력치 차이의 최솟값




    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        S = new int[N][N];
        visit = new boolean[N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for ( int j = 0; j < N; j++) {
                S[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        combi(0, 0); // 백트래킹 시작 idx= 0부터, 0명선택
        System.out.println(minDiff);
        
    }

    static void combi(int idx, int count) { // N/2 명 선택하는 백트래킹
        if(count == N / 2) { // 스타트 팀에 N/2명 모았으면 능력치 계산으로 이동
            calcDiff();
            return;
        }

        for (int i = idx; i<N; i++) { // for 루프에서 다음 사람 선택 (idx부터 시작해 중복/순서 무시))
            if (!visit[i]) { // 아직 선택 안된 사람
                visit[i] = true;
                combi(i + 1, count + 1); // 다음 사람부터 재귀
                visit[i] = false; // 백트래킹
            }
        }
    }
    static void calcDiff() {
        int start = 0, link = 0;

        // 모든 서로 다른 i,j 쌍에 대해 (i < j로 중복 계산 피함)
        for (int i = 0; i < N -1; i++) {
            for (int j = i + 1; j < N; j++) {
                if (visit[i] && visit[j]) { // 둘 다 스타트 팀
                    start += S[i][j] + S[j][i];
                } else if (!visit[i] & !visit[j]) { // 둘다 링크 팀
                    link += S[i][j] + S[j][i];
                }
            }
        }
        int diff = Math.abs(start - link);
        if (diff == 0) { // 최솟값 0 나오면 바로 종료
            System.out.println(0);
            System.exit(0);
        }
        minDiff = Math.min(minDiff, diff);
    }
    
}