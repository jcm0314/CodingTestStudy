import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        
        long[] fib = new long[n+1];
        
        fib[0]=0;
        if(n>=1) {
            fib[1] = 1;
        }
        
        
        for (int i = 2; i <= n; i++) {
            fib[i] = fib[i-1] + fib[i-2];
        }
        
        
        System.out.println(fib[n]);
    }
}