class Solution {
    public int numDecodings(String s) {
        if (s.charAt(0) == '0') return 0;

        int first = 1;
        int second = 1;

        for (int i = 1; i < s.length(); i++) {
            int current = 0;

            if (s.charAt(i) != '0') {
                current = first;
            }

            int val = Integer.parseInt(s.substring(i - 1, i + 1));
            if (val >= 10 && val <= 26) {
                current = current + second;
            }

            second = first;
            first = current;
        }

        return first;
    }
}