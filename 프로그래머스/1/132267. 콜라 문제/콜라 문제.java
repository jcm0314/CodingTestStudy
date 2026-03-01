class Solution {
    public int solution(int a, int b, int n) {
        int totalCoke = 0; // 내가 총 받은 콜라 수

        // 가진 빈 병(n)이 최소 교환 단위(a)보다 많을 때만 반복
        while (n >= a) {
            // 1. 이번에 새로 받은 콜라 계산
            int newCoke = (n / a) * b;
            
            // 2. 총 획득량에 추가
            totalCoke += newCoke;
            
            // 3. 남은 빈 병 업데이트: (교환하고 남은 병) + (새로 받은 콜라를 다 마시고 생긴 빈 병)
            n = (n % a) + newCoke;
        }

        return totalCoke;
    }
}