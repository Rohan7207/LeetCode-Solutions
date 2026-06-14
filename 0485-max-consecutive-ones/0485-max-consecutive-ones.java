class Solution {
    public int findMaxConsecutiveOnes(int[] nums) {
        int n = nums.length;
        int maxCount = 0;

        for (int i = 0; i < n; i++) {
            int temp = 0;
            while (i < n && nums[i] == 1) {
                temp++;
                i++;
            }

            maxCount = Math.max(maxCount, temp);
        }

        return maxCount;
    }
}