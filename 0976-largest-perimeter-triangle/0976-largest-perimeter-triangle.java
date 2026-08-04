class Solution {
    public int largestPerimeter(int[] nums) {
        //  The necessary and sufficient condition for these lengths a, b and c to form a triangle of non-zero area is a+b>c.
        Arrays.sort(nums);

        for(int i = nums.length - 1; i >= 2; i--) {
            // Triangle inequality condition: sum of two smaller sides > largest side
            if(nums[i - 2] + nums[i - 1] > nums[i]) {
                return nums[i - 2] + nums[i - 1] + nums[i];
            }
        }

        return 0;
    }
}