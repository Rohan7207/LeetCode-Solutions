class Solution {
    public int minimizeSum(int[] nums) {
        int n = nums.length;

        //Because we can make any two elements equal to the third element and hence the array will have all elements equal and so the diffrence will be 0 for all pairs
        if (n == 3) {
            return 0;
        }

        Arrays.sort(nums);

        // Removing last and first
        int a = nums[n - 2] - nums[1];

        // Removing first two
        int b = nums[n - 1] - nums[2];

        // Removing last two
        int c = nums[n - 3] - nums[0];

        return Math.min(a, Math.min(b, c));
    }
}

/*
    public int minimizeSum(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;

        return Math.min((nums[n - 2] - nums[1]), Math.min((nums[n - 1] - nums[2]), (nums[n - 3] - nums[0])));
    }
*/
