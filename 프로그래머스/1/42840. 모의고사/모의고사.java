import java.util.*;

class Solution {
    public int[] solution(int[] answers) {
        // 1. 수포자들의 반복 패턴 정의
        int[] p1 = {1, 2, 3, 4, 5};
        int[] p2 = {2, 1, 2, 3, 2, 4, 2, 5};
        int[] p3 = {3, 3, 1, 1, 2, 2, 4, 4, 5, 5};

        int[] score = new int[3]; // 수포자들의 점수를 저장

        // 2. 정답 비교 (나머지 연산 % 가 핵심!)
        for (int i = 0; i < answers.length; i++) {
            if (answers[i] == p1[i % p1.length]) score[0]++;
            if (answers[i] == p2[i % p2.length]) score[1]++;
            if (answers[i] == p3[i % p3.length]) score[2]++;
        }

        // 3. 가장 높은 점수 찾기
        int maxScore = Math.max(score[0], Math.max(score[1], score[2]));

        // 4. 최대 점수와 같은 사람들을 리스트에 담기 (오름차순 보장)
        List<Integer> list = new ArrayList<>();
        if (maxScore == score[0]) list.add(1);
        if (maxScore == score[1]) list.add(2);
        if (maxScore == score[2]) list.add(3);

        // 5. 리스트를 배열로 변환하여 리턴
        int[] answer = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            answer[i] = list.get(i);
        }
        return answer;
    }
}