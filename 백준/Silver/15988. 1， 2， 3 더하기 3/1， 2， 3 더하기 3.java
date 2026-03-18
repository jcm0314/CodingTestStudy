import java.io.*;
import java.util.*;

public class Main {
    
    static final int MOD = 1000000009;
    static long[] dp = new long[1000001];
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        dp[1] = 1;
        dp[2] = 2;
        dp[3] = 4;
        
        for (int i = 4; i <= 1000000; i++) {
            dp[i] = (dp[i -1] + dp[i-2] + dp[i-3]) % MOD;
        } 
       
        int T = Integer.parseInt(br.readLine());
        
        for (int t = 0; t < T; t++) {
            int n = Integer.parseInt(br.readLine());
            sb.append(dp[n]).append('\n');
        }
        
        System.out.print(sb.toString());
    }
}