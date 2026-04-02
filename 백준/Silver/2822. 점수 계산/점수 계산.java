import java.util.*;
import java.io.*;

public class Main {
    static class Node {
        int score, idx;
        Node(int score, int idx) {
            this.score = score;
            this.idx = idx;
        }
    }
    
    
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Node[] arr = new Node[8];
        
        for (int i = 0; i < 8; i++) {
            int score = Integer.parseInt(br.readLine());
            arr[i] = new Node(score, i + 1);
        }
        
        Arrays.sort(arr, (a, b) -> b.score - a.score);
        
        int sum = 0;
        int[] idxs = new int[5];
        
        for (int i = 0; i < 5; i++) {
            sum += arr[i].score;
            idxs[i] = arr[i].idx;
        }
        
        Arrays.sort(idxs);
        
        System.out.println(sum);
        for (int idx : idxs) {
            System.out.print(idx + " ");
        }
        
        
        
        
        // 5개 받아서 key value로 넣기
        // 6번째부터 min.value보다 높을 경우 min의 key value 삭제 후, 그 값 넣기
        // br.readLine()이 끝나면 종료
        // 큐에 들어있는 value의 합과 각 key값 출력
    }   
}