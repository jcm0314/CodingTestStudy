import java.io.*;
import java.util.*;

public class Main {
    
    static int n, m;
    static int[][] paper;
    static boolean[][] visited;
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};
    
    public static void main(String[] args) throws Exception {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        
        
        paper = new int[n][m];
        visited = new boolean[n][m];
        
        for (int i=0; i<n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                paper[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        
        int count = 0;
        int maxArea = 0;
        
        for (int i = 0; i < n; i++) {
            for (int j=0; j < m; j++){
                if (paper[i][j] == 1 && !visited[i][j]) {
                    count++;
                    int area = dfs(i, j);
                    maxArea = Math.max(maxArea, area);
                }
            }
        }
        
        System.out.println(count);
        System.out.println(maxArea); 
    }
    
    static int dfs(int y, int x) {
        visited[y][x] = true;
        int area = 1;
        
        for(int i = 0; i <4; i++) {
            int ny = y + dy[i];
            int nx = x + dx[i];
            
            if (ny < 0 || ny >= n || nx < 0 || nx >= m) {
                continue;
            }
            
            if (paper[ny][nx] == 1 && !visited[ny][nx]) {
                area += dfs(ny, nx);
            }
        }
        
        return area;
    }
}