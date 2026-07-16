class Solution {
    public int arrayPairSum(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        int sum = 0;

        for(int i = 1; i < n; i += 2) {
            if(i < n) {
                sum += Math.min(nums[i], nums[i - 1]);
            }
        }

        return sum;
    }
}