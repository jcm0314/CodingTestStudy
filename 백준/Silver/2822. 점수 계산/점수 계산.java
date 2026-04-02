import java.io.*;
import java.util.*;

public class Main {
    // 점수와 문제 번호를 같이 저장하기 위한 클래스
    static class Node {
        int score, idx;

        Node(int score, int idx) {
            this.score = score; // 점수
            this.idx = idx;     // 문제 번호
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 최소 힙: score가 작은 값이 먼저 나오게 설정
        PriorityQueue<Node> pq = new PriorityQueue<>((a, b) -> a.score - b.score);

        // 문제 1번부터 8번까지 점수를 입력받는다
        for (int i = 1; i <= 8; i++) {
            int score = Integer.parseInt(br.readLine()); // 점수 입력
            pq.offer(new Node(score, i));               // 점수와 번호를 같이 저장

            // 큐에 5개보다 많아지면, 가장 작은 점수 1개를 제거
            // 이렇게 하면 항상 상위 5개만 남는다
            if (pq.size() > 5) {
                pq.poll();
            }
        }

        int sum = 0;        // 상위 5개 점수 합
        int[] idxs = new int[5]; // 선택된 문제 번호 저장

        // 큐에 남아 있는 5개를 꺼내면서 합을 구하고 번호를 저장
        for (int i = 0; i < 5; i++) {
            Node cur = pq.poll(); // 가장 작은 점수부터 하나씩 꺼냄
            sum += cur.score;     // 점수 합산
            idxs[i] = cur.idx;    // 문제 번호 저장
        }

        // 문제 번호는 오름차순으로 출력해야 하므로 정렬
        Arrays.sort(idxs);

        // 점수 합 출력
        System.out.println(sum);

        // 문제 번호 출력
        for (int idx : idxs) {
            System.out.print(idx + " ");
        }
    }
}