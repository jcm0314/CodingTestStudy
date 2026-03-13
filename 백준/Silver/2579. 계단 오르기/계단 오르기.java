import java.io.*;
import java.util.*;

public class Main { 
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        
        int[] stair = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            stair[i] = Integer.parseInt(br.readLine());
        }
        
        if (n == 1) {
            System.out.println(stair[1]);
            return;
        }
        if (n == 2) {
            System.out.println(stair[1] + stair[2]);
            return;
        }
        
        int[] dp = new int[n + 1];
        dp[1] = stair[1];
        dp[2] = stair[1] + stair[2];
        dp[3] = Math.max(stair[1], stair[2]) + stair[3];
        
        for (int i = 4; i<= n; i++) {
            dp[i] = Math.max(dp[i - 2], dp[i - 3] + stair[i - 1]) + stair[i];
        }
        System.out.println(dp[n]);
    }
}