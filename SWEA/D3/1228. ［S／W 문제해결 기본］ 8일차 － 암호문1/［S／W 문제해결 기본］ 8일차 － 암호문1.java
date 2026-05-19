import java.io.*;
import java.util.*;

class Solution {

    static class FastReader {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        String next() throws IOException {
            while (st == null || !st.hasMoreElements()) {
                st = new StringTokenizer(br.readLine());
            }
            return st.nextToken();
        }

        int nextInt() throws IOException {
            return Integer.parseInt(next());
        }
    }

    public static void main(String[] args) throws Exception {
        FastReader fr = new FastReader();
        StringBuilder sb = new StringBuilder();

        for (int tc = 1; tc <= 10; tc++) {
            int N = fr.nextInt();
            List<Integer> list = new ArrayList<>();

            for (int i = 0; i < N; i++) {
                list.add(fr.nextInt());
            }

            int M = fr.nextInt();

            for (int i = 0; i < M; i++) {
                String cmd = fr.next(); 
                int x = fr.nextInt();
                int y = fr.nextInt();

                for (int j = 0; j < y; j++) {
                    int num = fr.nextInt();
                    list.add(x + j, num);
                }
            }

            sb.append("#").append(tc).append(" ");
            for (int i = 0; i < 10; i++) {
                sb.append(list.get(i)).append(" ");
            }
            sb.append("\n");
        }

        System.out.print(sb);
    }
}
        // 1. 자료구조 뭘로 선택하지? -> N하고 명령 개수 작으니깐 고민없이 ArrayList<Integer>로 처리해도 될듯?
        //  list.add(index, value)를 써서 원하는 위치에 요소 넣게 하기
        // 2. 명령어 파싱
        // 3. 삽입 
