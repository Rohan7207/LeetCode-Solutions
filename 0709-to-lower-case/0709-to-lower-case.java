class Solution {
    public String toLowerCase(String s) {
        StringBuilder str = new StringBuilder();

        for (char ch : s.toCharArray()) {
            if (ch >= 'A' && ch <= 'Z') {
                str.append((char) (ch + 32));
            } else {
                str.append(ch);
            }
        }

        return str.toString();
    }
}

// or
// return s.toLowerCase();