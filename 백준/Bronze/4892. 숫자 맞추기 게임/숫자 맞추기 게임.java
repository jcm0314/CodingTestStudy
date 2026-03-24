import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line;
        int i = 1; 
        while ((line = br.readLine()) != null) {
            int a = Integer.parseInt(line.trim());
            if (a == 0) break;
            else if (a % 2 == 0) {
                System.out.println((i)+". even " + (a / 2));
                i++;
            } else {
                System.out.println((i)+". odd " + ((a - 1) / 2));
                i++;
            }
        }
    }
}