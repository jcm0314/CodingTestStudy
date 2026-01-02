import java.util.*;

public class Solution {
    public int[] solution(int []arr) {
        ArrayList<Integer> tempList = new ArrayList<>();
        
        // 첫 번째 원소는 무조건 넣거나, 이전 값과 비교하는 로직
        int lastValue = -1; 
        
        for (int i : arr) {
            // 이전 숫자와 현재 숫자가 다를 때만 리스트에 추가
            if (i != lastValue) {
                tempList.add(i);
                lastValue = i;
            }
        }

        // List를 int[] 배열로 변환
        int[] answer = new int[tempList.size()];
        for (int i = 0; i < tempList.size(); i++) {
            answer[i] = tempList.get(i);
        }

        return answer;
    }
}