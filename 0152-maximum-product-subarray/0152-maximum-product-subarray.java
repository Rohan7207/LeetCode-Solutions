class Solution {
    public int maxProduct(int[] nums) {
        int n = nums.length;
        
        int max = nums[0];
        int min = nums[0];
        int ans = max;

        for (int i = 1; i < n; i++) {
            int cur = nums[i];
            int temp = Math.max(cur, Math.max(max * cur, min * cur));
            min = Math.min(cur, Math.min(min * cur, max * cur));
            max = temp;

            ans = Math.max(ans, max);
        }

        return ans;
    }
}