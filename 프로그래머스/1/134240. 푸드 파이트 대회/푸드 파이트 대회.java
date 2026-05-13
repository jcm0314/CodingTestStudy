class Solution {
    public String solution(int[] food) {
        StringBuilder left = new StringBuilder();
        
        for (int i = 1; i < food.length; i++) {
            int cnt = food[i] / 2; // 한 사람 몫
            for (int k = 0; k < cnt; k++) {
                left.append(i);
            }
        }
        
        StringBuilder answer = new StringBuilder();
        answer.append(left);
        answer.append(0);
        answer.append(left.reverse());
        
        return answer.toString();
    }
}