import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken()); // 트럭의 개수
        int w = Integer.parseInt(st.nextToken()); // 다리의 길이
        int l = Integer.parseInt(st.nextToken()); // 다리의 최대 하중

        Queue<Integer> trucks = new LinkedList<>(); // 대기 중인 트럭
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            trucks.add(Integer.parseInt(st.nextToken()));
        }

        Queue<Integer> bridge = new LinkedList<>(); // 다리 위의 상태
        for (int i = 0; i < w; i++) {
            bridge.add(0); // 처음엔 다리가 비어있으므로 0으로 채움
        }

        int time = 0;
        int currentWeight = 0; // 현재 다리 위 트럭들의 무게 합

        // 대기 트럭이 있거나 다리 위에 트럭이 남아있는 동안 반복
        while (!bridge.isEmpty()) {
            time++;
            // 1. 다리의 맨 끝에서 트럭이 나감
            currentWeight -= bridge.poll();

            // 2. 새로운 트럭이 다리에 들어올 수 있는지 확인
            if (!trucks.isEmpty()) {
                if (currentWeight + trucks.peek() <= l) {
                    // 들어올 수 있다면 실제 트럭 진입
                    int t = trucks.poll();
                    bridge.add(t);
                    currentWeight += t;
                } else {
                    // 무게 초과로 못 들어오면 0을 넣어 전진만 시킴
                    bridge.add(0);
                }
            }
            // 3. 더 이상 들어올 트럭이 없으면 bridge는 자연스럽게 poll만 되다가 비워짐
        }

        System.out.println(time);
    }
}