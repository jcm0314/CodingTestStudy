import java.io.*;
import java.util.*;

class Main {


    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String k = br.readLine();
        int result = Integer.parseInt(k, 16);
        System.out.println(result);
    }
}