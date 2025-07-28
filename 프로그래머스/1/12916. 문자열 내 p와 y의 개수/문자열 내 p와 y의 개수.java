class Solution {
    boolean solution(String s) {
        boolean answer = true;
        // count 만들기
        int pcount = 0;
        int ycount = 0;
        // 문자열에서 p하고 y추출, count ++하기
        
        s = s.toLowerCase();
        
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == 'p') {
                pcount++;
            }
            else if (c == 'y') {
                ycount++;
            }
        }
        // [실행] 버튼을 누르면 출력 값을 볼 수 있습니다.
        System.out.println("Hello Java");

        return pcount == ycount;
    }
}