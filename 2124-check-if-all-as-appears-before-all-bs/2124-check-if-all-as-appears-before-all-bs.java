class Solution {
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
}