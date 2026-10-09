class Solution {
    public int[] shuffle(int[] nums, int n) {
        int len = nums.length;
        int[] ans = new int[len];
        int i = 0, j = n;
        int idx = 0;

        while (i < n && j < len) {
            ans[idx++] = nums[i++];
            ans[idx++] = nums[j++];
        }

        return ans;
    }
}