class Solution {
    public String freqAlphabets(String s) {
        StringBuilder sb = new StringBuilder();
        int i = s.length() - 1;

        while(i >= 0) {
            char ch = s.charAt(i);
            if(ch == '#') {
                String numStr = s.substring(i - 2, i);
                int num = Integer.parseInt(numStr);

                char letter = (char) (num + 96);
                sb.append(letter);

                i -= 3;
            } else {
                int num = ch - '0';
                char letter = (char) (num + 96);
                sb.append(letter);
                
                i--;
            }
        }

        return sb.reverse().toString();
    }
}