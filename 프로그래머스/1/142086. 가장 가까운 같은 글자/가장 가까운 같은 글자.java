import java.util.*;

class Solution {
    public int[] solution(String s) {
        int[] answer = new int[s.length()];
        Map<Character, Integer> lastPosMap = new HashMap<>();
        
        for(int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            
            if (!lastPosMap.containsKey(c)) {
                answer[i] = -1;
            }
            
            else {
                answer[i] = i - lastPosMap.get(c);
            }
            
            lastPosMap.put(c, i);
        }
        return answer;
    }
}