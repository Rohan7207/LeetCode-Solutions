class Solution {
    public int scoreOfParentheses(String s) {
        Stack<Integer> st = new Stack<>();
        int score = 0;

        for(int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if(ch == '(') {
                // Save current outer score and reset for the inner context
                st.push(score);
                score = 0;
            } else {
                // If it's a direct pair "()", add 1 point
                if(s.charAt(i - 1) == '(') {
                    score = st.pop() + 1;
                } else {
                    // If it's a closed outer structure "(A)", double inner score and add to outer score
                    score = st.pop() + 2 * score;
                }
            }
        }

        return score;
    }
}