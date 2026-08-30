class Solution {
    public String minRemoveToMakeValid(String s) {
        Set<Integer> removeIndices = new HashSet<>();
        Stack<Integer> st = new Stack<>();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') {
                st.push(i);
            } else if (c == ')') {
                if (st.isEmpty()) {
                    removeIndices.add(i);
                } else {
                    st.pop();
                }
            }
        }

        //Add remaining parenthesis present in stack
        while (!st.isEmpty()) {
            removeIndices.add(st.pop());
        }

        //Add to result string
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if (!removeIndices.contains(i)) {
                res.append(s.charAt(i));
            }
        }

        return res.toString();
    }
}