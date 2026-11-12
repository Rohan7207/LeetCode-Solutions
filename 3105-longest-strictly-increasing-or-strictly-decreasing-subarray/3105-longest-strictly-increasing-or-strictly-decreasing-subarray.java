class Solution {
    public int longestMonotonicSubarray(int[] nums) {
        int increase = 1, decrease = 1;
        int maxIncrease = 1, maxDecrease = 1;

        for(int i = 1; i < nums.length; i++) {
            if(nums[i - 1] < nums[i]) {
                increase++;
                decrease = 1;

                maxIncrease = Math.max(maxIncrease, increase);
            } else if(nums[i - 1] > nums[i]) {
                decrease++;
                increase = 1;

                maxDecrease = Math.max(maxDecrease, decrease);
            } else {
                increase = 1;
                decrease = 1;
            }
        }

        return Math.max(maxIncrease, maxDecrease);
    }
}