class Solution {
    public int numIdenticalPairs(int[] nums) {
        int[] count = new int[101];
        int ans = 0;

        for (int num : nums) {
            ans += count[num]++;
        }

        return ans;
    }
}

/*
    public int numIdenticalPairs(int[] nums) {
        int n = nums.length;
        int count = 0;

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (nums[i] == nums[j]) {
                    count++;
                }
            }
        }

        return count;
    }
*/