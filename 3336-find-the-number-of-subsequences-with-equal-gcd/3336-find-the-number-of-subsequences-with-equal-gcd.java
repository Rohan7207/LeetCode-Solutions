class Solution {
    private final int MOD = 1_000_000_007;
    private Integer[][][] dp;

    public int subsequencePairCount(int[] nums) {
        int n = nums.length;

        // dp[index][gcd1][gcd2]
        dp = new Integer[n][201][201];

        return solve(nums, 0, 0, 0);
    }

    private int solve(int[] nums, int index, int gcd1, int gcd2) {
        // Base Case
        if (index == nums.length) {
            if (gcd1 == gcd2 && gcd1 != 0) {
                return 1;
            }

            return 0;
        }

        // Memoization
        if (dp[index][gcd1][gcd2] != null) {
            return dp[index][gcd1][gcd2];
        }

        // Choice 1 : Skip current element
        int skip = solve(nums, index + 1, gcd1, gcd2);

        // Choice 2 : Put current element in seq1
        int takeSeq1 = solve(nums, index + 1, gcd(gcd1, nums[index]), gcd2);

        // Choice 3 : Put current element in seq2
        int takeSeq2 = solve(nums, index + 1, gcd1, gcd(gcd2, nums[index]));

        // Total ways
        int ans = (int) (((long) skip + takeSeq1 + takeSeq2) % MOD);

        return dp[index][gcd1][gcd2] = ans;
    }

    private int gcd(int a, int b) {
        while (b != 0) {
            int temp = a % b;
            a = b;
            b = temp;
        }

        return a;
    }
}