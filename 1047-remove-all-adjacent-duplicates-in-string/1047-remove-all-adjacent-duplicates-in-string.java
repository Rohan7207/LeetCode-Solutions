class Solution {
    public String removeDuplicates(String s) {
        Stack<Integer> st = new Stack<>();

        for(int i = 0; i < s.length(); i++) {
            if(!st.isEmpty() && s.charAt(st.peek()) == s.charAt(i)) {
                st.pop();
            } else {
                st.push(i);
            }

        }

        StringBuilder sb = new StringBuilder();
        while(!st.isEmpty()) {
            sb.append(s.charAt(st.pop()));
        }

        return sb.reverse().toString();
    }
}