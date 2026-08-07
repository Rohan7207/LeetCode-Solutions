class Solution {
    public int largestSumAfterKNegations(int[] nums, int k) {
        Arrays.sort(nums);

        for(int i = 0; i < nums.length; i++) {
            while(k > 0 && nums[i] < 0) {
                nums[i] = -nums[i];
                k--;
            }
        }

        int sum = 0;
        int min = Integer.MAX_VALUE;

        for(int num : nums) {
            sum += num;
            if(num < min) {
                min = num;
            }
        }

        if(k % 2 != 0) {
            sum -= 2 * Math.abs(min);
        }

        return sum;
    }
}