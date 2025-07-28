class Solution {
    public boolean solution(int x) {
        boolean answer = true;
        int sum = 0;
        // x를 문자열로 바꾸기
        String s = String.valueOf(x);  // 또는 Integer.toString(x)
        // x에서 하나씩 추출
        for(int i = 0; i < s.length(); i++) {
            // 추출한 값 다 더하기 sum
            sum += s.charAt(i) - '0';  // 문자를 숫자로 변환
        }
        // x를 sum으로 나눴을 때, 나머지 없으면 true, 아니면 false 반환
        return (x % sum == 0);
    }
}