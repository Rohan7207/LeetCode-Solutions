// Problem: Find the Number of Subsequence With Equal GCD
// Link: https://leetcode.com/problems/find-the-number-of-subsequences-with-equal-gcd/?envType=daily-question&envId=2026-07-14
// Difficulty: Hard

// Approach:
// We need to build two disjoint non-empty subsequences such that
// both have the same GCD.
// Observe that while processing the array, we never need to store
// the actual subsequences.
// The only information required for future decisions is their
// current GCDs.
// DP State:
//     solve(index, gcd1, gcd2)
// where:
//     index -> current position in nums
//     gcd1  -> current GCD of seq1 (0 means seq1 is empty)
//     gcd2  -> current GCD of seq2 (0 means seq2 is empty)
// At every index, there are exactly three choices:
//     1. Skip the current element.
//     2. Put it into seq1.
//     3. Put it into seq2.
// The GCD is updated using:
//     gcd(newGcd, nums[index])
// Since many different paths can reach the same
// (index, gcd1, gcd2) state, memoization avoids
// recomputing those states.
// Base Case:
//     When all elements have been processed:
//         If gcd1 == gcd2
//         and gcd1 != 0
//         both subsequences are non-empty and have
//         equal GCD, so return 1.
//         Otherwise return 0.

// Time Complexity:
//     O(n × M²)
//
//     where
//         n = nums.length
//         M = maximum possible GCD (200)
//
// Space Complexity:
//     O(n × M²)
//     (Memoization table + recursion stack)


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
