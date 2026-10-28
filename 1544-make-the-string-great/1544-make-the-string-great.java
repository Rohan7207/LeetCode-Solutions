class Solution {
    public String makeGood(String s) {
        Stack<Integer> st = new Stack<>();

        for (int i = 0; i < s.length(); i++) {
            char currCh = s.charAt(i);

            if (!st.isEmpty()) {
                char ch = s.charAt(st.peek());

                // If they match (same letter, different case), destroy the pair
                if ((Character.toLowerCase(ch) == Character.toLowerCase(currCh)) && ch != currCh) {
                    st.pop();
                } else {
                    st.push(i);
                }
            } else {
                st.push(i);
            }
        }

        StringBuilder sb = new StringBuilder();
        while (!st.isEmpty()) {
            sb.append(s.charAt(st.peek()));
            st.pop();
        }

        return sb.reverse().toString();
    }
}