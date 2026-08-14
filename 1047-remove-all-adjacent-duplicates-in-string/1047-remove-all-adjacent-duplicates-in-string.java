class Solution {
    public String removeDuplicates(String s) {
        StringBuilder str = new StringBuilder();

        for(char c:s.toCharArray())
        {
        int len = str.length();
        if(len>0 && str.charAt(len-1)==c)
        {
            str.deleteCharAt(len-1);
        }
        else 
            str.append(c);
        }
        return str.toString();
    }
}

/*
    With stack:
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
*/