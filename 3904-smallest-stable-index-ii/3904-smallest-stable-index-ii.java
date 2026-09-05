class Solution {
    public int firstStableIndex(int[] nums, int k) {
        int n = nums.length;
        int[] suffixMin = new int[n];

        int min = Integer.MAX_VALUE;
        for(int i = n - 1; i >= 0; i--) {
            if(nums[i] < min) {
                min = nums[i];
            }

            suffixMin[i] = min;
        }

        int max = 0;
        for(int i = 0; i < n; i++) {
            if(nums[i] > max) {
                max = nums[i];
            }

            if(max - suffixMin[i] <= k) {
                return i;
            }
        }

        return -1;
    }
}

/*
    public int firstStableIndex(int[] nums, int k) {
        int n = nums.length;
        int[] suffixMin = new int[n];
        suffixMin[n - 1] = nums[n - 1];

        for(int i = n - 2; i >= 0; i--) {
            suffixMin[i] = Math.min(suffixMin[i + 1], nums[i]);
        }

        int max = 0;
        for(int i = 0; i < n; i++) {
            max = Math.max(max, nums[i]);

            if(max - suffixMin[i] <= k) {
                return i;
            }
        }

        return -1;
    }
*/