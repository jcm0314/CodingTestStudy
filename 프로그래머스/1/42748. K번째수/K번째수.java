import java.util.*;

class Solution {
    public int[] solution(int[] array, int[][] commands) {
        int[] answer = new int[commands.length];
        
        for (int c = 0; c < commands.length; c++) {
            int i = commands[c][0];
            int j = commands[c][1];
            int k = commands[c][2];
                
            int[] temp = Arrays.copyOfRange(array, i-1, j);
            
            Arrays.sort(temp);
            
            answer[c] = temp[k - 1];
        }
        
        // commands != empty가 아닐때까지 진행하기(반복문)
        // array의 i부터 자르기 시작
        // array의 j까지 자르기 끝(배열에 담기)
        // sort 써서 담은 배열을 오름차순으로 정렬
        // 배열에서 k번째 숫자 return하기        
        return answer;
    }
}