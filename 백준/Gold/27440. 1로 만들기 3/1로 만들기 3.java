import java.io.*;
import java.util.*;

public class Main {

    static class Node {
        long val;   // 현재 값
        int dist;   // 여기까지 연산 횟수

        Node(long v, int d) {
            this.val = v;
            this.dist = d;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        long N = Long.parseLong(br.readLine());  // N은 최대 10^18

        // N이 이미 1인 예외 케이스 처리 (안 해도 BFS가 처리하긴 함)
        if (N == 1) {
            System.out.println(0);
            return;
        }

        // 방문 체크: 나온 값만 저장해야 해서 HashSet 사용
        HashSet<Long> visited = new HashSet<>();

        // BFS용 큐
        Queue<Node> q = new ArrayDeque<>();
        q.offer(new Node(N, 0));
        visited.add(N);

        while (!q.isEmpty()) {
            Node cur = q.poll();
            long x = cur.val;
            int d = cur.dist;

            // 1에 도착하면 그때의 d가 최소 연산 횟수
            if (x == 1) {
                System.out.println(d);
                return;
            }

            // 3으로 나누어 떨어지는 경우
            if (x % 3 == 0) {
                long nx = x / 3;
                if (!visited.contains(nx)) {
                    visited.add(nx);
                    q.offer(new Node(nx, d + 1));
                }
            }

            // 2로 나누어 떨어지는 경우
            if (x % 2 == 0) {
                long nx = x / 2;
                if (!visited.contains(nx)) {
                    visited.add(nx);
                    q.offer(new Node(nx, d + 1));
                }
            }

            // 1 빼는 연산 (항상 가능, 단 overflow/음수만 조심)
            long nx = x - 1;
            if (nx >= 1 && !visited.contains(nx)) {
                visited.add(nx);
                q.offer(new Node(nx, d + 1));
            }
        }
    }
}
