class Solution {
    public int[] sortedSquares(int[] nums) {
        int n = nums.length;
        int left = 0;
        int right = n - 1;
        int idx = n - 1;

        int[] ans = new int[n];
        while (left <= right) {
            int v1 = nums[left] * nums[left];
            int v2 = nums[right] * nums[right];

            if (v1 >= v2) {
                ans[idx--] = v1;
                left++;
            } else if (v2 >= v1) {
                ans[idx--] = v2;
                right--;
            }
        }

        return ans;
    }
}