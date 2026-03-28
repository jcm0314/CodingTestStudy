import java.io.*;
import java.util.*;

public class Main {
    static final int MAX = 100000;
    static final int INF = 1000000000;
    
    static int N, K;
    static int[] dist = new int[MAX + 1];
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        
        System.out.println(bfs());
    }
    
    static int bfs() {
        if (N >= K) {
            return N - K;
        }
        
        Arrays.fill(dist, INF);
        
        Deque<Integer> dq = new ArrayDeque<>();
        dq.add(N);
        dist[N] = 0;
        
        while (!dq.isEmpty()) {
            int x = dq.removeFirst();
            
            if (x == K) {
                return dist[x];
            }
            
            int nx = x * 2;
            if(nx <= MAX && dist[nx] > dist[x]) {
                dist[nx] = dist[x];
                dq.addFirst(nx);
            }
            
            nx = x - 1;
            if (nx >= 0 && dist[nx] > dist[x] + 1) {
                dist[nx] = dist[x] + 1;
                dq.addLast(nx);
            }
            nx = x + 1;
            if (nx <= MAX && dist[nx] > dist[x] + 1) {
                dist[nx] = dist[x] + 1;
                dq.addLast(nx);
            }
              
             
        }
        return dist[K];
    }
    
}