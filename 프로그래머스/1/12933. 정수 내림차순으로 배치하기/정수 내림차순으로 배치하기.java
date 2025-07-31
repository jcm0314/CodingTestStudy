import java.util.Arrays;
import java.util.Collections;

class Solution {
    public long solution(long n) {
        // 문자열로 만들기
        String str = Long.toString(n);
        // 문자 배열로 만들기
        String[] arr = str.split("");
        // array 정렬하기
        Arrays.sort(arr, Collections.reverseOrder());
        // 합쳐서 문자열로 만들기
        StringBuilder sb = new StringBuilder();
        for (String s :  arr) {
            sb.append(s);
        }
        // 문자열을 long으로 바꾸기
        return Long.parseLong(sb.toString());
    }
}