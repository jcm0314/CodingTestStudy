import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        // 빠른 입력을 위한 BufferedReader 사용
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        // 첫 줄: 집의 개수 N (2 ≤ N ≤ 1000)
        int N = Integer.parseInt(br.readLine());
        
        // cost[i][j]: i번째 집을 j색(R=0,G=1,B=2)으로 칠할 때 드는 비용
        int[][] cost = new int[N][3];
        
        // N줄씩 R,G,B 비용 입력받기
        for(int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            cost[i][0] = Integer.parseInt(st.nextToken()); // 빨강 비용
            cost[i][1] = Integer.parseInt(st.nextToken()); // 초록 비용  
            cost[i][2] = Integer.parseInt(st.nextToken()); // 파랑 비용
        }
        
        // dp[i][j]: i번째 집까지 칠했을 때, i번째 집을 j색으로 칠한 최소 비용
        int[][] dp = new int[N][3];
        
        // 0번 집 초기값: 그냥 해당 색 비용 그대로
        dp[0][0] = cost[0][0];
        dp[0][1] = cost[0][1];
        dp[0][2] = cost[0][2];
        
        // 1번 집부터 N-1번 집까지 bottom-up 방식으로 채우기
        for(int i = 1; i < N; i++) {
            // i번째 집을 빨강(0)으로 칠할 때: 이전집 초록(1) or 파랑(2) 중 최소값 + 현재 빨강비용
            dp[i][0] = Math.min(dp[i-1][1], dp[i-1][2]) + cost[i][0];
            
            // i번째 집을 초록(1)으로 칠할 때: 이전집 빨강(0) or 파랑(2) 중 최소값 + 현재 초록비용  
            dp[i][1] = Math.min(dp[i-1][0], dp[i-1][2]) + cost[i][1];
            
            // i번째 집을 파랑(2)으로 칠할 때: 이전집 빨강(0) or 초록(1) 중 최소값 + 현재 파랑비용
            dp[i][2] = Math.min(dp[i-1][0], dp[i-1][1]) + cost[i][2];
        }
        
        // 마지막 집(인덱스 N-1)의 세 색깔 중 최소값이 정답
        int answer = Math.min(dp[N-1][0], Math.min(dp[N-1][1], dp[N-1][2]));
        System.out.println(answer);
    }
}
