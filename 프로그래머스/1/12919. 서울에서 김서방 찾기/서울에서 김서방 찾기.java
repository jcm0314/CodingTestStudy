class Solution {
    public String solution(String[] seoul) {
        // seoul에서 하나씩 빼기
        String answer = "";
        for (int i = 0; i<seoul.length; i++) {
            if (seoul[i].equals("Kim")) {
                answer = String.valueOf(i);
                break;
            }
        }
            
        // Kim이 있는 인덱스를 리턴하기
   
        return "김서방은 "+answer+"에 있다";
    }
}