import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        String s = br.readLine().trim();
        
        if(s.isEmpty()){
            System.out.println('0');
            return;
        }
        
        
        int count = 1;
        for(int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == ' ') {
                count++;
            }
        }
        System.out.println(count);
    }
}