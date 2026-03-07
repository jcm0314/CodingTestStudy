import java.util.Arrays;

class Solution {
    public int[] solution(int k, int[] score) {
        int[] answer = new int[score.length];
        int[] temp = new int[k];      // 명예의 전당 점수 저장
        int size = 0;                 // 현재 temp에 들어간 개수

        for (int i = 0; i < score.length; i++) {
            int s = score[i];

            if (size < k) {           // 아직 k개 안 찼을 때
                temp[size] = s;
                size++;
            } else {
                // temp[0..k-1] 중 최솟값 찾기
                int minIdx = 0;
                for (int j = 1; j < k; j++) {
                    if (temp[j] < temp[minIdx]) {
                        minIdx = j;
                    }
                }
                if (s > temp[minIdx]) {  // 새 점수가 최소값보다 크면 교체
                    temp[minIdx] = s;
                }
            }

            // 현재까지 들어간 것들 중 최솟값 구해서 answer[i]에 기록
            int min = temp[0];
            for (int j = 1; j < size; j++) {
                if (temp[j] < min) min = temp[j];
            }
            answer[i] = min;
        }

        return answer;
    }
}
