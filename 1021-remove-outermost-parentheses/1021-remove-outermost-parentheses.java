class Solution {
    public String removeOuterParentheses(String s) {
        StringBuilder sb = new StringBuilder();
        int start = -1, end = -1;

        int count = 0;
        for(int i = 0; i < s.length(); i++) {
            if(start == -1) {
                start = i;
            }

            count += (s.charAt(i) == '(' ? 1 : -1);

            if(count == 0 && end == -1) {
                end = i;
            }

            if(start != -1 && end != -1) {
                for(int j = start + 1; j < end; j++) {
                    sb.append(s.charAt(j));
                }

                start = -1;
                end = -1;
            }
        }

        return sb.toString();
    }
}