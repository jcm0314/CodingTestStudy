class Solution {
    public int solution(int[] absolutes, boolean[] signs) {
        int answer = 123456789;
        int sum = 0;
        for (int i = 0; i<signs.length; i++)
        // true면 +absolutes[i]
            if (signs[i] == true) {
                sum += absolutes[i];
            }
        // false면 -absolutes[i]
            else if (signs[i] == false) {
                sum -= absolutes[i];
            }
        return sum;
    }
}