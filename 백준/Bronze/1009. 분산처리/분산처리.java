import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[][] cycle = {
            {},
            {1},
            {2, 4, 8, 6},
            {3, 9, 7, 1},
            {4, 6},
            {5},
            {6},
            {7, 9, 3, 1},
            {8, 4, 2, 6},
            {9, 1}
    };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for (int i = 0; i < T; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            int last = a % 10;

            if (last == 0) {
                sb.append(10).append('\n');
                continue;
            }

            int[] pattern = cycle[last];
            int idx = (b - 1) % pattern.length;
            sb.append(pattern[idx]).append('\n');
        }

        System.out.print(sb);
    }
}