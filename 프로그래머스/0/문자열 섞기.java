class Solution {
    public String solution(String str1, String str2) {
        // StringBuilder를 사용하여 문자열 조합
        StringBuilder sb = new StringBuilder();

        // 두 문자열 길이만큼 반복
        for (int i = 0; i < str1.length(); i++) {
            sb.append(str1.charAt(i)); // str1의 i번째 문자 추가
            sb.append(str2.charAt(i)); // str2의 i번째 문자 추가
        }

        return sb.toString(); // 최종 문자열 반환
    }
}
