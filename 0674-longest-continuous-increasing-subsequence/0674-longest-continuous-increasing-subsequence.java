class Solution {
    public int findLengthOfLCIS(int[] nums) {
        int res = 1;
        int max = 1;

        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] < nums[i + 1]) {
                res++;
                if (max < res) {
                    max = res;
                }
            } else {
                res = 1;
            }
        }

        return max;
    }
}