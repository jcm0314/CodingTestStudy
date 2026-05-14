class Solution {
    public int[] solution(int numer1, int denom1, int numer2, int denom2) {
        int[] answer = {};
        int numer = numer1 * denom2 + numer2 * denom1;
        int denom = denom1 * denom2;
        
        int g = gcd(numer, denom);
        
        return new int[] { numer / g, denom /g };
    }
    
    private int gcd(int a, int b) {
    // 유클리드 호제법
    while (b != 0) {
        int tmp = a % b;
        a = b;
        b = tmp;
    }
    return a;
    }
}