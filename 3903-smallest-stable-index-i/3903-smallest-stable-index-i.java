class Solution {
    public int firstStableIndex(int[] nums, int k) {
        int n = nums.length;
        int[] prefixMax = new int[n];
        int[] prefixMin = new int[n];
        prefixMax[0] = nums[0];
        prefixMin[n - 1] = nums[n - 1];

        for(int i = 1; i < n; i++) {
            prefixMax[i] = Math.max(prefixMax[i - 1], nums[i]);
        }

        for(int i = n - 2; i >= 0; i--) {
            prefixMin[i] = Math.min(prefixMin[i + 1], nums[i]);
        }

        for(int i = 0; i < n; i++) {
            int diff = prefixMax[i] - prefixMin[i];

            if(diff <= k) {
                return i;
            }
        }

        return -1;
    }
}