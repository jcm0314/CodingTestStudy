class Solution {
    public int solution(int n, int[] lost, int[] reserve) {
        int[] cloth = new int[n+2]; // n+1이 아닌 이유는 뒤에 인덱스도 봐야하기 때문에 안전하게 n+2로함
        int answer = 0;
        
        for(int i=1; i<=n; i++) cloth[i] = 1;
        
        for(int l : lost) cloth[l]--;
        
        for(int r : reserve) cloth[r]++;
        
        for(int i=1; i<=n; i++) {
            if(cloth[i] == 0) {
                if(cloth[i-1]==2) {
                    cloth[i-1]--;
                    cloth[i]++;
                }
                else if(cloth[i+1] == 2) {
                    cloth[i+1]--;
                    cloth[i]++;
                }
                }
            }
        for (int i = 1; i <= n; i++) {
            if(cloth[i] >= 1) answer++;
        }

        
        return answer;
        
    }
}