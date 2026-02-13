import java.util.*;

class Solution {
    public int solution(int[][] sizes) {
        int maxWidth = 0;
        int maxHeight = 0;
        
        for (int[] size : sizes) {
            int garo = Math.max(size[0], size[1]);
            int sero = Math.min(size[0], size[1]);

            maxWidth = Math.max(maxWidth, garo);
            maxHeight = Math.max(maxHeight, sero);
        }
        return maxWidth * maxHeight;
            
    }
}