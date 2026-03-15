import java.io.*;
import java.util.*;

public class Main {
    
    static final int MAX = 100000;
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        
        int[] dist = new int[MAX + 1];
        Arrays.fill(dist, -1);
        
        Queue<Integer> q = new ArrayDeque<>();
        
        dist[N] = 0;
        q.offer(N);
        while (!q.isEmpty()) {
            int x = q.poll();
            
            if (x == K) {
                System.out.println(dist[x]);
                return;
            }
            
            
            int[] nexts = { x - 1, x + 1, x * 2};
            
            for (int nx : nexts) {
                if (nx < 0 || nx > MAX) continue;
                
                if (dist[nx] == -1) {
                    dist[nx] = dist[x] + 1;
                    q.offer(nx);
                }
            }
        }
        
    }













}