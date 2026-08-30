class Solution {
    public int minimumDeletions(int[] nums) {
        int n = nums.length;
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        int maxIndex = 0;
        int minIndex = 0;

        for (int i = 0; i < n; i++) {
            if (nums[i] > max) {
                max = nums[i];
                maxIndex = i;
            }

            if (nums[i] < min) {
                min = nums[i];
                minIndex = i;
            }
        }

        int leftIndex = Math.min(minIndex, maxIndex);
        int rightIndex = Math.max(maxIndex, minIndex);

        int front = rightIndex + 1;
        int back = n - leftIndex;

        int both = (leftIndex + 1) + (n - rightIndex);

        return Math.min(front, Math.min(back, both));
    }
}