import java.util.*;
import java.io.*;

public class Main {
    static int N, M;
    static int[] result;  // 결과를 담을 배열
    static boolean[] visited; // 방문 여부를 체크할 배열
    static StringBuilder sb = new StringBuilder(); // 출력을 모아서 한 번에 하기                

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        result = new int[M];
        visited = new boolean[N + 1]; // 1부터 N까지 사용하므로 N+1 크기

        // 0번째 숫자부터 뽑기 시작
        backtrack(0);
        
        // 최종 결과 출력
        System.out.print(sb);
    }

    static void backtrack(int depth) {
        // 1. 종료 조건: M개를 모두 뽑았을 때
        if (depth == M) {
            for (int val : result) {
                sb.append(val).append(" ");
            }
            sb.append("\n");
            return;
        }

        // 2. 탐색 루프: 순열이므로 항상 1부터 N까지 확인
        for (int i = 1; i <= N; i++) {
            // 아직 사용하지 않은 숫자라면
            if (!visited[i]) {
                visited[i] = true;  // 사용 처리 (방문 체크)
                result[depth] = i;  // \추가
                
                backtrack(depth + 1); // 다음 숫자 뽑으러 가기
                
                // 3. 백트래킹의 핵심: 돌아온 후 다시 사용 가능한 상태로 복구
                visited[i] = false; 
            }
        }
    }
}