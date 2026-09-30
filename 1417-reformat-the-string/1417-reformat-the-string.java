class Solution {
    public String reformat(String s) {
        int n = s.length();
        StringBuilder sb = new StringBuilder();
        StringBuilder letters = new StringBuilder();
        StringBuilder digits = new StringBuilder();

        for(int i = 0; i < n; i++) {
            char ch = s.charAt(i);

            if(Character.isDigit(ch)) {
                digits.append(ch);
            } else {
                letters.append(ch);
            }
        }

        int len1 = letters.length();
        int len2 = digits.length();

        if(Math.abs(len1 - len2) > 1) {
            return "";
        }
        
        int i = 0, j = 0;

        // If digits are more, start with digit.;
        boolean flag = len1 >= len2;

        while(i < len1 || j < len2) {
            if(flag) {
                sb.append(letters.charAt(i++));
            } else {
                sb.append(digits.charAt(j++));
            }

            flag = !flag;
        }

        return sb.toString();
    }
}