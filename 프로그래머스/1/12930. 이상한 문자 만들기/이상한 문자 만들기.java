// 단어별로 인덱스를 판단해야 함: 전체 문자열의 인덱스가 아니라, 공백을 기준으로 각 단어의 인덱스가 0부터 다시 시작됩니다. (예: try hello -> t는 0번째, h도 다시 0번째)

// 공백 처리: 단어 사이에 공백이 여러 개일 수도 있고, 마지막에 공백이 올 수도 있습니다. 이를 그대로 유지해야 합니다.

// 대소문자 변환: 자바에서는 Character.toUpperCase()나 toLowerCase()를 쓰는 게 가장 깔끔합니다. 아스키코드 연산(+32, -32)을 직접 하면 가독성이 떨어지고 실수가 잦아요.

class Solution {
    public String solution(String s) {
        StringBuilder sb = new StringBuilder();
        int idx = 0;
        
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            
            if(c== ' ') {
                sb.append(' ');
                idx = 0;
            } else {
                if (idx % 2 == 0) {
                    sb.append(Character.toUpperCase(c));
                } else {
                    sb.append(Character.toLowerCase(c));
                }
                idx++;
            }
        }

        return sb.toString();
    }
}