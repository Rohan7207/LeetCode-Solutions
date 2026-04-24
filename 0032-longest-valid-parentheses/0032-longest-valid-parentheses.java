class Solution {
    public int longestValidParentheses(String s) {
        int maxlength = 0;
        Stack<Integer> st = new Stack<>();
        st.push(-1); //Initial value

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                st.push(i);
            } else {
                st.pop();

                if (st.isEmpty()) {
                    //Push the current index as base for next substring
                    st.push(i);
                } else {
                    maxlength = Math.max(maxlength, i - st.peek());
                }
            }
        }
        
        return maxlength;
    }
}