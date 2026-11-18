class Solution {
    public String modifyString(String s) {
        int n = s.length();

        if (n == 1 && s.charAt(0) == '?') {
            return "a";
        }

        char[] str = s.toCharArray();

        for (int i = 0; i < n; i++) {
            char currCh = str[i];

            if (i == 0 && currCh == '?') {
                char ch = 'a';

                if (ch == str[i + 1]) {
                    ch++;
                }

                str[i] = ch;
            } else if (i == n - 1 && currCh == '?') {
                char ch = 'a';

                if (ch == str[i - 1]) {
                    ch++;
                }

                str[i] = ch;
            } else if (currCh == '?') {
                char ch = 'a';

                while (ch == str[i - 1] || ch == str[i + 1]) {
                    ch++;
                }

                str[i] = ch;
            } else {
                str[i] = currCh;
            }
        }

        return new String(str);
    }
}