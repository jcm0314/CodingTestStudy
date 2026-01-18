import java.util.*;
import java.io.*;

public class Main {
    static int k;
    static int[] S;
    static int[] result = new int[6]; // 뽑은 6개의 숫자를 담을 배열

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            k = Integer.parseInt(st.nextToken());
            
            // 입력의 마지막 줄에는 0이 하나 주어지므로 종료
            if (k == 0) break;

            S = new int[k];
            for (int i = 0; i < k; i++) {
                S[i] = Integer.parseInt(st.nextToken());
            }

            // 0번 인덱스부터 시작해서 0개를 뽑은 상태로 탐색 시작
            backtrack(0, 0);
            System.out.println(); // 각 테스트 케이스 사이 빈 줄
        }
    }

    /**
     * @param start 탐색을 시작할 배열 인덱스
     * @param depth 현재까지 뽑은 숫자의 개수
     */
    static void backtrack(int start, int depth) {
        // 1. 종료 조건: 6개를 모두 뽑았을 때
        if (depth == 6) {
            for (int val : result) {
                System.out.print(val + " ");
            }
            System.out.println();
            return;
        }

        // 2. 탐색 루프: 시작 지점부터 k전까지
        for (int i = start; i < k; i++) {
            result[depth] = S[i]; // 숫자 선택
            backtrack(i + 1, depth + 1); // 다음 숫자를 찾으러 재귀 호출 (i + 1이 핵심)
        }
    }
}