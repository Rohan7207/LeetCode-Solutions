class Solution {
    public int reverseDegree(String s) {
        int sum = 0;

        for (int i = 1; i <= s.length(); i++) {
            int val = 26 - (s.charAt(i - 1) - 'a');
            int product = val * i;

            sum += product;
        }

        return sum;
    }
}