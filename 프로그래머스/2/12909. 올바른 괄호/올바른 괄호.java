import java.util.Stack;

class Solution {
        boolean solution(String s) {
        int count = 0;
        int len = s.length();
        for (int i = 0; i < len; i++) {
            count += s.charAt(i) == '(' ? 1 : -1;

            if (count < 0) {
                return false;
            }
        }
        return count == 0;
    }
}