import java.util.*;


class Solution {
    public String solution(String s) {
        // 공백을 기준으로 나누고 담기
        // 인덱스0만 대문자, 나머지는 소문자
        // join해서 공백넣어서 붙이기
        StringBuilder sb = new StringBuilder();
        boolean start = true; // 단어 시작 여부
        
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            
            if (c == ' ') {
                sb.append(c);
                start = true;
            } else {
                if (start) {
                    sb.append(Character.toUpperCase(c));
                    start = false;
                } else {
                    sb.append(Character.toLowerCase(c));

                }
            }
        }
        

        return sb.toString();
    }
}