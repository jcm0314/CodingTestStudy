class Solution {
    public String solution(String s, int n) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            // 1. 공백은 처리하지 않고 그대로 추가
            if (c == ' ') {
                sb.append(c);
                continue;
            }

            // 2. 소문자인 경우
            if (Character.isLowerCase(c)) {
                // 'a'로부터 얼마나 떨어져 있는지 계산 후 n만큼 밀기
                sb.append((char) ((c - 'a' + n) % 26 + 'a'));
            } 
            // 3. 대문자인 경우
            else if (Character.isUpperCase(c)) {
                // 'A'로부터 얼마나 떨어져 있는지 계산 후 n만큼 밀기
                sb.append((char) ((c - 'A' + n) % 26 + 'A'));
            }
        }

        return sb.toString();
    }
}