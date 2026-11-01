class Solution {
    public String makeGood(String s) {
        StringBuilder sb = new StringBuilder();
        
        sb.append(s.charAt(0));
        for(int i = 1; i < s.length(); i++) {
            char ch = s.charAt(i);

            if(!sb.isEmpty()) {
                char lastChar = sb.charAt(sb.length() - 1);

                if(Character.toLowerCase(ch) == Character.toLowerCase(lastChar) && ch != lastChar) {
                    sb.deleteCharAt(sb.length() - 1);
                } else {
                    sb.append(ch);
                }
            } else {
                sb.append(ch);
            }
        }

        return sb.toString();
    }
}

/*
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
*/