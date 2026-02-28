class Solution {
    public int solution(int n) {
        // 1. 3진법으로 변환하면서 동시에 뒤집기 위해 StringBuilder 사용
        StringBuilder sb = new StringBuilder();
        
        while (n > 0) {
            // 나머지를 추가 (나중에 들어온 나머지가 뒷자리가 되므로 자연스럽게 반전됨)
            sb.append(n % 3);
            n /= 3;
        }
        
        // 2. 3진법(문자열)을 다시 10진법(int)으로 변환
        // Integer.parseInt(값, 현재진법)을 쓰면 자동으로 계산해줘!
        return Integer.parseInt(sb.toString(), 3);
    }
}