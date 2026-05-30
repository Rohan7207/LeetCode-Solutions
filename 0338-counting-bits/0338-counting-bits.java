// Problem : Counting Bits
// Link : https://leetcode.com/problems/counting-bits/
// Difficulty : Easy

// Approach:
// Use Dynamic Programming.
// Let dp[i] represent the number of
// set bits (1s) in i.
// Use Brian Kernighan's observation:
//     i & (i - 1)
// removes the rightmost set bit.
// Therefore:
//     dp[i] = dp[i & (i - 1)] + 1
// Since (i & (i - 1)) is always smaller
// than i, its answer is already computed.

// Time Complexity: O(n)
// Space Complexity: O(n)


class Solution {
    public int[] countBits(int n) {
        int[] ans = new int[n + 1];
        ans[0] = 0;

        for(int i = 1; i <= n; i++){
            ans[i] = ans[i & (i - 1)] + 1;
        }

        return ans;
    }
}
