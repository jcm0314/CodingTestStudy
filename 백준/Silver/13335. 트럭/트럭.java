import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken()); // 트럭 수
        int w = Integer.parseInt(st.nextToken()); // 다리 길이
        int L = Integer.parseInt(st.nextToken()); // 다리 최대 하중

        int[] truck = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            truck[i] = Integer.parseInt(st.nextToken());
        }

        // 시간, 현재 다리 위 총 무게, 다음에 올릴 트럭 인덱스
        int time = 0;
        int currentWeight = 0;
        int nextIdx = 0;

        // 다리 위 상태를 나타내는 큐 (길이 w)
        Queue<Integer> bridge = new ArrayDeque<>();

        // 처음에는 다리가 비어 있으므로 0으로 w칸 채워둔다
        for (int i = 0; i < w; i++) {
            bridge.offer(0);
        }

        // 대기 트럭이 남아 있거나, 다리 위에 트럭이 남아 있는 동안 반복
        while (nextIdx < n || currentWeight > 0) {
            time++;

            // 1) 맨 앞 칸에서 나가는 트럭(또는 0) 처리
            int out = bridge.poll();
            currentWeight -= out;

            // 2) 새 트럭을 올릴 수 있는지 확인
            if (nextIdx < n) {
                int nextTruck = truck[nextIdx];

                // 올릴 수 있으면 올리고, 아니면 0(빈 칸)만 추가
                if (currentWeight + nextTruck <= L) {
                    bridge.offer(nextTruck);
                    currentWeight += nextTruck;
                    nextIdx++;
                } else {
                    bridge.offer(0);
                }
            } else {
                // 더 올릴 트럭이 없으면 그냥 빈 칸만 흘려보냄
                bridge.offer(0);
            }
        }

        System.out.println(time);
    }
}
