import java.util.*;

class Solution {
    public int[] solution(int[] numbers) {
        // 1. 중복을 허용하지 않고 자동으로 정렬해주는 TreeSet 선언
        Set<Integer> set = new TreeSet<>();

        // 2. 이중 for 문으로 모든 두 수의 합 구하기
        for (int i = 0; i < numbers.length; i++) {
            for (int j = i + 1; j < numbers.length; j++) {
                // 두 수의 합을 set에 넣음 (중복은 알아서 걸러짐)
                set.add(numbers[i] + numbers[j]);
            }
        }

        // 3. Set을 다시 int[] 배열로 변환하여 리턴
        // 스트림을 쓰면 한 줄로 끝납니다!
        return set.stream().mapToInt(Integer::intValue).toArray();
    }
}