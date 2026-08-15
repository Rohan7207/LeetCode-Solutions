class Solution {
    public int longestSubsequence(int[] nums) {
        int n = nums.length;
        int totalXOR = 0;
        boolean allZero = true;

        for (int x : nums) {
            totalXOR ^= x;
            if (x > 0) {
                allZero = false;
            }
        }

        if (totalXOR > 0) {
            return n;
        }

        return allZero ? 0 : n - 1;
    }
}