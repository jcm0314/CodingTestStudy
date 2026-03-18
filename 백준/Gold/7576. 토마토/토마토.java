import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    static int M;
    static int N;
    static int [][] map;
    static int remain = 0;
    static Queue<int[]> q = new LinkedList<>();    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        map = new int[N][M];

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j]== 0) 
                    remain++;
                else if (map[i][j]==1)
                    q.offer(new int[]{i,j});
                }
            }

            bfs();
        }
    
        public static void bfs() {
            int days = 0;
            int[] dr = {-1, 0, 1, 0}, dc = {0, 1, 0, -1};

            while(!q.isEmpty() && remain > 0) {
                int size = q.size();
                for (int s = 0; s < size; s++) {
                    int[] cur = q.poll();
                    int r = cur[0];
                    int c = cur[1];
                    for (int d=0; d < 4; d++) {
                        int nr = r+dr[d], nc = c+dc[d];
                        if(nr>=0 && nr < N && nc >=0 && nc<M && map[nr][nc]==0) {
                            map[nr][nc] = 1;
                            q.offer(new int[]{nr,nc});
                            remain--;
                        }
                    }
                }
                if(!q.isEmpty()) days++;

                
                }
            if(remain == 0) {
                System.out.println(days);
            } else {
                System.out.println(-1);
            }
        }
        }
