import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        // 빠른 입력을 위한 설정
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // F: 건물 전체 층 수, S: 현재 층, G: 목표 층, U: 위로 U층, D: 아래로 D층
        int F = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());
        int G = Integer.parseInt(st.nextToken());
        int U = Integer.parseInt(st.nextToken());
        int D = Integer.parseInt(st.nextToken());
        

// 배열 선언: 크기 F+1 인 방문 배열 visited, 거리 배열 dist 생성 (1번 층부터 F번 층까지 사용)
boolean[] visited = new boolean[F + 1];
int [] dist = new int[F + 1];

// 큐 생성: Queue<Integer> q = new LinkedList<>();
Queue<Integer> q = new LinkedList<>();


// 시작층 S를 큐에 넣고, 방문 처리하고, dist[S] = 0 으로 설정
q.offer(S);
visited[S] = true;
dist[S] = 0;


// BFS 시작: while 큐가 빌 때까지 반복
    while(!q.isEmpty()) {
    // 현재 층 x = q.poll() 으로 하나 꺼냄
        int x = q.poll();
        
    // 만약 x 가 목표 층 G라면, 더 이상 볼 필요 없으므로 반복 종료 (최소 버튼 수를 찾은 상태)
        if (x == G) break;
        
    // 1) 위로 이동 가능한 층 계산: up = x + U
        int up = x + U;
    //    - up 이 1 ~ F 범위 안이고, 아직 방문하지 않았다면
        if(up <= F && !visited[up]) {
    //        - visited[up] = true 로 표시
            visited[up] = true;
    //        - dist[up] = dist[x] + 1 로 버튼 수 갱신
            dist[up] = dist[x] + 1;
    //        - q.offer(up) 으로 큐에 추가
            q.offer(up);
        }
    // 2) 아래로 이동 가능한 층 계산: down = x - D
        int down = x - D;
    //    - down 이 1 ~ F 범위 안이고, 아직 방문하지 않았다면
        if(down >= 1 && !visited[down]) {
    //        - visited[down] = true 로 표시
            visited[down] = true;
    //        - dist[down] = dist[x] + 1 로 버튼 수 갱신
            dist[down] = dist[x] + 1;
    //        - q.offer(down) 으로 큐에 추가
            q.offer(down);
        }
    }

// BFS가 끝난 뒤:
//   - 만약 visited[G] 가 true 라면: dist[G] 를 출력 (최소 버튼 수)
        if (visited[G]) System.out.println(dist[G]);
//   - 그렇지 않다면: "use the stairs" 를 출력 (엘리베이터로는 도달 불가)
        else{
            System.out.println("use the stairs");
        }
    }
}
