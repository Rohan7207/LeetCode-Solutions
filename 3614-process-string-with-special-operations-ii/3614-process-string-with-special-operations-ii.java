class Solution {
    public char processStr(String s, long k) {
        long len = 0;

        for(char ch : s.toCharArray()) {
            if(ch == '#') {
                len *= 2;
            } else if(ch == '*') {
                if(len > 0) len--;
            } else if(ch == '%') {

            } else {
                len++;
            }
        }

        if(k >= len) return '.';

        for(int i = s.length() - 1; i >= 0; i--) {
            char ch = s.charAt(i);

            if(ch == '#') {
                long oldLen = len / 2;
                if(oldLen == 0) {
                    len = 0;
                    continue;
                }
                k = k % oldLen;
                len = oldLen;
            } else if(ch == '*') {
                len++;
            } else if(ch == '%') {
                k = len - 1 - k;
            } else {
                if(k == len - 1) {
                    return ch;
                }

                len--;
            }
        }

        return '.';
    }
}