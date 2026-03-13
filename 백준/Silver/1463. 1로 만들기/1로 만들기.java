import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[] dp = new int[n + 1];
        dp[1] = 0; // 1은 이미 1이니까 연산 0번

        for (int i = 2; i <= n; i++) {
            // 1을 빼는 경우는 항상 가능
            dp[i] = dp[i - 1] + 1;  // 후보 1

            if (i % 2 == 0) {
                dp[i] = Math.min(dp[i], dp[i / 2] + 1);  
            }
            if (i % 3 == 0) {
                dp[i] = Math.min(dp[i], dp[i / 3] + 1); 
            }
        }

        System.out.println(dp[n]);
    }
}
