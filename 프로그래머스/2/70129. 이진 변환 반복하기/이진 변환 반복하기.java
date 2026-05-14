class Solution {
    public int[] solution(String s) {
        int cntTransform = 0;
        int cntZero = 0;

        while (!s.equals("1")) {
            cntTransform++;

            // 0 개수 세기
            int zeros = 0;
            for (char c : s.toCharArray()) {
                if (c == '0') zeros++;
            }
            cntZero += zeros;

            // 0 제거 후 길이
            int onesLen = s.length() - zeros;

            // 길이를 2진수 문자열로
            s = Integer.toBinaryString(onesLen);
        }

        return new int[]{cntTransform, cntZero};
    }
}