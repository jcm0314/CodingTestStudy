class Solution {
    public int solution(String s) {
        // 1. 인덱스 번호와 영단어를 매칭시킨 배열 선언
        String[] words = {
            "zero", "one", "two", "three", "four", 
            "five", "six", "seven", "eight", "nine"
        };

        // 2. 배열을 돌면서 s에 포함된 영단어를 숫자로 치환
        for (int i = 0; i < words.length; i++) {
            // "one"이 있으면 "1"로, "two"가 있으면 "2"로...
            s = s.replace(words[i], Integer.toString(i));
        }

        // 3. 최종 결과 문자열을 정수(int)로 변환하여 리턴
        return Integer.parseInt(s);
    }
}