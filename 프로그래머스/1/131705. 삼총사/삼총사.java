class Solution {
    public int solution(int[] number) {
        int answer = 0;
        // 0. 계산 하는 거 선언
        // 1. int[] number 배열하기 
        // 2. for문 3번하기 -> 3번이 계산한 것이 0이되면 answer에 +하기
        for(int i = 0; i < number.length; i++) {
            for(int k = i+1; k < number.length; k++) {
                for(int j = k+1; j < number.length; j++) {
                    if(number[i] + number[k] + number[j] == 0) {
                        answer++;
                    }
                }
            }
        }
        // 3. answer 리턴하기
        return answer;
    }
}