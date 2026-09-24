class Solution {
    public int smallestIndex(int[] nums) {
        for(int i = 0; i < nums.length; i++) {
            int sum = helper(nums[i]);
            if(i == sum) {
                return i;
            }
        }

        return -1;
    }

    private int helper(int num) {
        int sum = 0;

        while(num > 0) {
            sum += num % 10;
            num /= 10;
        }

        return sum;
    }
}