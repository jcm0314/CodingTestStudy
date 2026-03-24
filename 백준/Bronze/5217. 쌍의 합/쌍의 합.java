import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {  
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine().trim());  
        
        for(int t = 1; t <= T; t++) {  
            int n = Integer.parseInt(br.readLine().trim());  
            System.out.print("Pairs for " + n + ":");  
            
            boolean first = true;  
            for(int x = 1; x < n; x++) {  
                int y = n - x;
                if(x < y) {  
                    if(!first) System.out.print(",");  
                    System.out.print(" " + x + " " + y);
                    first = false;
                }
            }
            System.out.println();  
        }
    }
}