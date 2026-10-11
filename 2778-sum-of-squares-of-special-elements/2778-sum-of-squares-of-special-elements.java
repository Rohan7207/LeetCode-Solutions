class Solution {
    public int sumOfSquares(int[] nums) {
        int n = nums.length;
        int sum = nums[n - 1] * nums[n - 1];

        for(int i = 1; i <= n / 2; i++) {
            if(n % i == 0) {
                sum += (nums[i - 1] * nums[i - 1]);
            }
        }

        return sum;
    }
}