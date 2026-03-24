import java.io.*;
import java.util.*;

public class Main {
    public static void compare(int a, int b) {

        if (a > b) System.out.println("Yes");
        else System.out.println("No");
    }
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line;
        
        
        while ((line = br.readLine()) != null) {
            StringTokenizer st = new StringTokenizer(line);
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            
            if (a == 0 && b == 0) break;
                
            compare(a, b);
        }
    }
}