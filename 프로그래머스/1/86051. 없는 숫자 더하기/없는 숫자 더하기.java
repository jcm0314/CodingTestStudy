import java.util.*;

class Solution {
    public int solution(int[] numbers) {
        int sum = 0;
        // 0부터 9까지 N 리스트 하나 만들기
        List<Integer> N = new ArrayList<>(Arrays.asList(0,1,2,3,4,5,6,7,8,9));
        // N리스트에서 numbers 리스트 빼기
        for(int i = 0; i < numbers.length; i++) {
            for(int k = 0; k < N.size(); k++) {
                if (N.get(k) == numbers[i]) {
                    N.remove(k);
                    // 중복 제거를 방지하기 위해 break 추가
                }
            }
        }
        
        // 남은 N리스트를 다 더하기
        for(int r = 0; r < N.size(); r++) {
            sum += N.get(r);
        }
        
        return sum;
    }
}