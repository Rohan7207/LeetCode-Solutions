class Solution {
    public boolean checkString(String s) {
        int n = s.length();
        int lastAPos = -1;
        int firstBPos = n;

        for (int i = 0; i < n; i++) {
            char ch = s.charAt(i);
            if (ch == 'a') {
                lastAPos = i;
            } else if (ch == 'b' && firstBPos == n) {
                firstBPos = i;
            }
        }


        return lastAPos < firstBPos;
    }
}

/*
    public boolean checkString(String s) {
        int n = s.length();
        int count = 0;

        if (s.charAt(0) == 'b') {
            for (int i = 1; i < n; i++) {
                if (s.charAt(i) == 'a') {
                    return false;
                }
            }
        } else {
            for (int i = 1; i < n; i++) {
                if (s.charAt(i) != s.charAt(i - 1)) {
                    count++;
                }

                if (count > 1) {
                    return false;
                }
            }
        }

        return true;
    }
*/