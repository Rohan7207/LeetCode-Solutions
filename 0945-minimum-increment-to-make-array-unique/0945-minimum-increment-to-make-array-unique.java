class Solution {
    public int minIncrementForUnique(int[] nums) {
        Arrays.sort(nums);
        int moves = 0;

        // 1 2 3 4 5 7
        for(int i = 1; i < nums.length; i++) {
            if(nums[i] <= nums[i - 1]) {
                int need = nums[i - 1] + 1;
                moves += need - nums[i];
                nums[i] = need;
            }
        }

        return moves;
    }
}