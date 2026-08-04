class Solution {
    public int[] sumEvenAfterQueries(int[] nums, int[][] queries) {
        int evenSum = 0;

        for (int num : nums) {
            if (num % 2 == 0) {
                evenSum += num;
            }
        }

        int[] ans = new int[queries.length];
        int z = 0;

        for (int[] query : queries) {
            int val = query[0];
            int idx = query[1];

            if (nums[idx] % 2 == 0) {
                evenSum -= nums[idx];
            }

            nums[idx] += val;

            if (nums[idx] % 2 == 0) {
                evenSum += nums[idx];
            }

            ans[z++] = evenSum;
        }

        return ans;
    }
}