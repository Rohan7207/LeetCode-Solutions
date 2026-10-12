// Problem: Apply Operations on Array to Maximize Sum of Squares
// Link: https://leetcode.com/problems/apply-operations-on-array-to-maximize-sum-of-squares/?envType=daily-question&envId=2026-10-12
// Difficulty: Hard

// Approach:
// 1. Create a count array of size 32 to store the number of integers having each bit set.
// 2. Traverse every number and inspect all bit positions from 0 to 31.
// 3. If bit b is set in a number, increment count[b].
// 4. Repeat k times to construct k numbers with maximum possible squared sum.
// 5. For each number, initialize curr = 0.
// 6. Traverse all bit positions. If count[b] > 0:
//    - Set bit b in curr using curr | (1 << b).
//    - Decrement count[b], because that bit occurrence has been used.
// 7. Add curr squared to the result, taking modulo MOD.
// 8. Return the result.

// Time Complexity: O(32 * n + 32 * k) = O(n + k)
// Space Complexity: O(32) = O(1)


class Solution {

    private int MOD = 1000000007;

    public int maxSum(List<Integer> nums, int k) {
        int[] count = new int[32];
        int res = 0;

        for (int num : nums) {
            for (int bit = 0; bit < 32; bit++) {
                if ((num & (1 << bit)) != 0) { 
                    count[bit]++;
                }
            }
        }

        while (k > 0) {
            int curr = 0;

            for (int bit = 0; bit < 32; bit++) {
                if (count[bit] > 0) {
                    count[bit]--;

                    curr = (curr | (1 << bit)); 
                }
            }

            res = (int) ((res + (long) curr * curr) % MOD);
            k--;
        }
        return res;
    }
}
