import java.util.*;

class Solution {
    public int[] solution(int[] arr, int divisor) {
        List<Integer> list = new ArrayList<>();
        
        // 배열에서 한 개씩 빼오기
        for (int num : arr) {
            // divisor로 나눠지는지 확인
            if (num % divisor == 0) {
                list.add(num);
            }
        }
        
        // 나눠지는 수가 없으면 -1 반환
        if (list.isEmpty()) {
            return new int[]{-1};
        }
        
        // 오름차순 정렬
        Collections.sort(list);
        
        // List를 배열로 변환
        int[] answer = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            answer[i] = list.get(i);
        }
        
        return answer;
    }
}