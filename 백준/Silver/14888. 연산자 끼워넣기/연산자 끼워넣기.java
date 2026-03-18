import java.util.Scanner;  // 입력용

public class Main {
    static int N;              // 숫자 개수
    static int[] numbers;      // 숫자 배열 (크기 N)
    static int[] operators = new int[4];  // [0:+, 1:-, 2:*, 3:/] 개수
    static int max = Integer.MIN_VALUE;   // 최댓값 초기화 (가장 작은 수)
    static int min = Integer.MAX_VALUE;   // 최솟값 초기화 (가장 큰 수)

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        numbers = new int[N];
        for(int i = 0; i < N; i++) {
            numbers[i] = sc.nextInt();  // 숫자 배열 채우기
        }
        for(int i = 0; i < 4; i++) {
            operators[i] = sc.nextInt();  // 각 연산자 개수 입력 (+ - * / 순)
        }
        sc.close();
        // 재귀 시작: 첫 숫자로 num= numbers[0], 다음 인덱스 idx=1
        dfs(numbers[0], 1);
        System.out.println(max);  // 최댓값 출력
        System.out.println(min);  // 최솟값 출력
    }

    // 여기에 dfs 메서드 넣기!
    static void dfs(int num, int idx) {
        // 모든 숫자 다 썼으면 (N-1개 연산자 사용)
        if(idx == N) {
            max = Math.max(max, num);  // 최댓값 갱신
            min = Math.min(min, num);  // 최솟값 갱신
            return;  // 끝
        }
        
        // 4종류 연산자 순회
        for(int i = 0; i < 4; i++) {
            if(operators[i] > 0) {  // 해당 연산자 남았으면
                operators[i]--;      // 사용 (1 감소)
                
                switch(i) {
                    case 0: dfs(num + numbers[idx], idx + 1); break;  // +
                    case 1: dfs(num - numbers[idx], idx + 1); break;  // -
                    case 2: dfs(num * numbers[idx], idx + 1); break;  // *
                    case 3: dfs(num / numbers[idx], idx + 1); break;  // / (정수 나눗셈)
                }
                
                operators[i]++;  // 백트래킹: 사용 취소 (다른 경우 위해 복구)
            }
        }
    }
}
