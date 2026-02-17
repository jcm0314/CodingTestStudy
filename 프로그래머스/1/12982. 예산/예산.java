import java.util.*;

class Solution {
    public int solution(int[] d, int budget) {
        int answer = 0;
        int cal = 0;
        // 1. d를 오름차순으로 정렬
        Arrays.sort(d);
        // 2. budget을 조건으로 budget보다 작으면 answer ++하고 진행, 
        for(int i = 0; i < d.length; i++) {
            if(cal <= budget) {
                cal += d[i];
                answer++;
                
            // 3. budget보다 크면 멈추기
            if(cal > budget) {
                answer--;
                break;
            }
            }
        }
        // 3. answer값 리턴하기

        return answer;
    }
}