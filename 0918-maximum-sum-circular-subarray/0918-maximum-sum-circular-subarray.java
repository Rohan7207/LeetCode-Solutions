class Solution {
    public int maxSubarraySumCircular(int[] nums) {
        int totalSum = nums[0];
        int currMax = nums[0];
        int maxSum = nums[0];

        for (int i = 1; i < nums.length; i++) {
            totalSum += nums[i];
            currMax = Math.max(nums[i], currMax + nums[i]);
            maxSum = Math.max(maxSum, currMax);
        }

        int currMin = nums[0];
        int minSum = nums[0];

        for (int i = 1; i < nums.length; i++) {
            currMin = Math.min(nums[i], currMin + nums[i]);
            minSum = Math.min(minSum, currMin);
        }

        if (maxSum < 0)
            return maxSum;

        return Math.max(maxSum, totalSum - minSum);
    }
}