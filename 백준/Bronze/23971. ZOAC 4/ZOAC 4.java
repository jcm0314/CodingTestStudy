import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // H: 행(세로 칸 수), W: 열(가로 칸 수)
        int H = Integer.parseInt(st.nextToken());
        int W = Integer.parseInt(st.nextToken());
        // N: 세로로 비워야 하는 칸 수, M: 가로로 비워야 하는 칸 수
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        // 세로 방향에 앉을 수 있는 사람 수
        // (H - 1)을 (N + 1)로 나눈 몫 + 1
        int height = (H - 1) / (N + 1) + 1;

        // 가로 방향에 앉을 수 있는 사람 수
        int width = (W - 1) / (M + 1) + 1;

        // 전체 최대 인원 수 = 세로 × 가로
        System.out.println(height * width);
    }
}
