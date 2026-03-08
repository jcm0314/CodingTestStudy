import java.util.*;

class Solution {
    public String[] solution(String[] strings, int n) {
        Arrays.sort(strings, (s1, s2) -> {
            // 1. n번째 문자 비교
            if (s1.charAt(n) == s2.charAt(n)) {
                // 2. 같으면 문자열 전체를 사전순 비교
                return s1.compareTo(s2);
            }
            // 3. 다르면 n번째 문자 기준으로 정렬
            return s1.charAt(n) - s2.charAt(n);
        });
        return strings;
    }
}
