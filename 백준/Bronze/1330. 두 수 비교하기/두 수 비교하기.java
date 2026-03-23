import java.io.*;
import java.util.*;

class Main {
    static void compare(int a, int b) {
        if(a < b) System.out.println("<");
        else if(a > b) System.out.println(">");
        else System.out.println("==");
    }
    static int a;
    static int b;
    
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        a = Integer.parseInt(st.nextToken());
        b = Integer.parseInt(st.nextToken());
        compare(a,b);
    }
}