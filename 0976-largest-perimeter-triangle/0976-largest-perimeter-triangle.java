class Solution {
    public int largestPerimeter(int[] nums) {
        //  The necessary and sufficient condition for these lengths a, b and c to form a triangle of non-zero area is a+b>c.
        Arrays.sort(nums);

        for(int i = nums.length - 3; i >= 0; i--) {
            if(nums[i] + nums[i + 1] > nums[i + 2]) {
                return nums[i] + nums[i + 1] + nums[i + 2];
            }
        }

        return 0;
    }
}