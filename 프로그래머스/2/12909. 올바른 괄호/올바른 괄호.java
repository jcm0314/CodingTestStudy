import java.util.Stack;

class Solution {
boolean solution(String s) {

    Stack<Character> stack = new Stack<>();

    for (char ch : s.toCharArray()) {
        if(ch == '(') {
            stack.push(ch);
        } else {
            if (stack.isEmpty()) {
                return false;
            }
            stack.pop();
        }
    } 
    boolean answer = stack.isEmpty();
    return answer;
}
}