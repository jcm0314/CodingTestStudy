class Solution {
    public int solution(int left, int right) {
        int answer = 0;

        // 1. left부터 right까지의 모든 수(num)를 순회
        for (int num = left; num <= right; num++) {
            int count = 0; // num의 약수의 개수를 세는 카운터

            // 2. num의 약수의 개수(count) 구하기
            for (int i = 1; i <= num; i++) {
                if (num % i == 0) {
                    count++;
                }
            }

            // 3. 약수의 개수가 짝수/홀수인지에 따라 answer에 더하거나 빼기
            if (count % 2 == 0) {
                // 약수의 개수가 짝수이면 해당 숫자를 더함
                answer += num;
            } else {
                // 약수의 개수가 홀수이면 해당 숫자를 뺌
                answer -= num;
            }
        }

        return answer;
    }
}