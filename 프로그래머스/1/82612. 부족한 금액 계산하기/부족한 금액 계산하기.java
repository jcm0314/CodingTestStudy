

class Solution {
    public long solution(int price, int money, int count) {
        long sum = 0;
        long minus = 0;
        
        for(int k=1; k<=count; k++) { // 루프 한번 돌 때마다 count++
            minus += (price*k);
        }
        sum = minus - money;
            
        if (sum>0)
            return sum;
        else
            return 0;
    }
}