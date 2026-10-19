class Solution {
    public int longestSubarray(int[] nums) {
        int ans = 0;
        int countZero = 0;
        int left = 0;

        for (int right = 0; right < nums.length; right++) {
            if (nums[right] == 0) {
                countZero++;
            }

            while (countZero > 1) {
                if (nums[left] == 0) {
                    countZero--;
                }

                left++;
            }

            ans = Math.max(ans, right - left + 1);
        }

        return ans - 1;
    }
}