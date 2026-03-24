import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int score = 0;
        for (int i = 0; i < 5; i++) {
            int T = Integer.parseInt(br.readLine());
            score += T;
        
            }    
        System.out.println(score);
        }

    }
