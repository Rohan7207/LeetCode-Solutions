class Solution {
    public int findLengthOfLCIS(int[] nums) {
        int res = 1;
        int max = 1;

        for(int i = 1; i < nums.length; i++) {
            if(nums[i] > nums[i - 1]) {
                res++;
            } else {
                res = 1;
            }

            max = Math.max(max, res);
        }

        return max;
    }
}