class Solution {
    public int solution(int n) {
        int answer = 0;

        int start = 1;   // 구간의 시작 값
        int end = 1;     // 구간의 끝 값
        int sum = 1;     // 현재 구간 [start ~ end]의 합

        // start가 n을 넘으면 더 이상 의미 있는 구간이 없음
        while (start <= n) {
            if (sum == n) {
                // 현재 구간 [start ~ end]의 합이 n이므로 경우의 수 +1
                answer++;

                // 다음 구간을 보기 위해 왼쪽 하나(start)를 빼고 start를 오른쪽으로 이동
                sum -= start;
                start++;
            } else if (sum < n) {
                // 합이 n보다 작으면 오른쪽으로 한 칸 확장해서 end를 포함시킴
                end++;
                // end가 n보다 커지면 더 이상 새로운 구간을 만들 수 없음
                if (end > n) break;
                sum += end;
            } else { // sum > n
                // 합이 n보다 크면 왼쪽 값을 빼서 구간을 줄임
                sum -= start;
                start++;
            }
        }

        return answer;
    }
}