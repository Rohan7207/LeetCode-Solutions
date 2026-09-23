class Solution {
    public int minOperations(int[] nums, int x) {
        // We should find the maximum size subarray whose sum = total - x so that remaining elements is ans
        int n = nums.length;
        int total = 0;
        int ans = Integer.MAX_VALUE;

        for(int num : nums) {
            total += num;
        }

        if(total < x) {
            return -1;
        }

        int left = 0;
        int currSum = 0;
        int count = 0;
        int target = total - x;
        for(int right = 0; right < n; right++) {
            currSum += nums[right];
            count++;

            while(left < n && currSum > target) {
                currSum -= nums[left];
                left++;
                count--;
            }

            if(currSum == target) {
                ans = Math.min(ans, n - count);
            }
        }

        return ans == Integer.MAX_VALUE ? -1 : ans;
    }
}