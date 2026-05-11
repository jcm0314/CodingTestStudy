// 그리디 알고리즘
import java.util.Arrays;

class Solution
{
    public int solution(int []A, int []B)
    {
        // A 오름차순으로 정렬하기
        Arrays.sort(A);
        // B 내림차순으로 정렬하기
        Arrays.sort(B);
        
        int answer = 0;
        

        for (int i = 0; i < A.length; i++) {
            answer += A[i] * B[B.length - 1 - i];
        }
        // [실행] 버튼을 누르면 출력 값을 볼 수 있습니다.

        return answer;
    }
}