class Solution {
    public int sumFourDivisors(int[] nums) {
        int ans = 0;

        for (int i = 0; i < nums.length; i++) {
            int x = nums[i];
            int count = 0;
            int sum = 1 + nums[i];
            int sqrt = (int) Math.sqrt(x);

            for (int j = 2; j <= sqrt; j++) {
                if(j * j == x) {
                    count++;
                    sum += j;
                }

                if (x % j == 0) {
                    count += 2;
                    sum += j + x / j;
                }

                if (count > 2) {
                    break;
                }
            }

            if (count == 2) {
                ans += sum;
            }
        }

        return ans;
    }
}