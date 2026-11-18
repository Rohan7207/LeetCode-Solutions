class Solution {
    public String modifyString(String s) {
        char[] str = s.toCharArray();
        int n = str.length;

        for (int i = 0; i < n; i++) {
            if (str[i] == '?') {
                for (char c = 'a'; c <= 'c'; c++) {
                    if ((i == 0 || str[i - 1] != c) && (i == n - 1 || str[i + 1] != c)) {
                        str[i] = c;
                        break;
                    }
                }
            }
        }

        return new String(str);
    }
}

/*
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
*/