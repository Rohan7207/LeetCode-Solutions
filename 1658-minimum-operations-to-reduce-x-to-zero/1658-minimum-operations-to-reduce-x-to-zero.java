class Solution {
    public int minOperations(int[] nums, int x) {
        // We should find the maximum size subarray whose sum = total - x so that remaining elements is ans
        int n = nums.length;
        int total = 0;

        for (int num : nums) {
            total += num;
        }

        int target = total - x;
        // If target is 0, we need to remove all elements
        if (target == 0) {
            return n;
        }

        // If target is negative, impossible
        if (target < 0) {
            return -1;
        }

        int left = 0;
        int currSum = 0;
        int maxLength = -1;
        for (int right = 0; right < n; right++) {
            currSum += nums[right];

            while (left < n && currSum > target) {
                currSum -= nums[left];
                left++;
            }

            if (currSum == target) {
                maxLength = Math.max(maxLength, right - left + 1);
            }
        }

        if (maxLength == -1) {
            return -1;
        }

        return n - maxLength;
    }
}

/*
    int left = 0;
        int currSum = 0;
        int count = 0;
        for (int right = 0; right < n; right++) {
            currSum += nums[right];
            count++;

            while (left < n && currSum > target) {
                currSum -= nums[left];
                left++;
                count--;
            }

            if (currSum == target) {
                ans = Math.min(ans, n - count);
            }
        }

        return ans == Integer.MAX_VALUE ? -1 : ans;
*/