import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine().trim());
        int[] score = new int[N];

        for (int i = 0; i < N; i++) {
            score[i] = Integer.parseInt(br.readLine().trim());
        }

        int answer = 0;

        // 뒤에서부터 처리
        for (int i = N - 2; i >= 0; i--) {
            if (score[i] >= score[i + 1]) {
                int newVal = score[i + 1] - 1;  // 바로 뒤보다 1 작게
                answer += score[i] - newVal;    // 줄인 만큼 더하기
                score[i] = newVal;              // 값 갱신
            }
        }

        System.out.println(answer);
    }
}
