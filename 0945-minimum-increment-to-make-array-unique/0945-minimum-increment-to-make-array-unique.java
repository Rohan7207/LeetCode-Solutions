class Solution {
    public int minIncrementForUnique(int[] nums) {
        Arrays.sort(nums);
        int moves = 0;  // Counts the total increments required.
        int numTracker = 0;  // Tracks the next unique number that should be set.

        for(int num : nums) {
            numTracker = Math.max(numTracker, num);
            moves += numTracker - num;
            numTracker += 1;
        }

        return moves;
    }
}

/*
    int moves = 0;

        for(int i = 1; i < nums.length; i++) {
            if(nums[i] <= nums[i - 1]) {
                int need = nums[i - 1] + 1;
                moves += need - nums[i];
                nums[i] = need;
            }
        }

        return moves;
*/