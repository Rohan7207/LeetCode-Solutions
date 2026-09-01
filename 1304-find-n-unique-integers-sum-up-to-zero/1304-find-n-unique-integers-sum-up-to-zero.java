// Problem: Find N Unique Integers Sum up to Zero
// Link: https://leetcode.com/problems/find-n-unique-integers-sum-up-to-zero/
// Difficulty: Easy

// Approach:
// Use Symmetric Pairs.
//
// 1. We need exactly `n` distinct integers whose sum is 0.
//
// 2. Create pairs of opposite numbers:
//
//      1, -1
//      2, -2
//      3, -3
//      ...
//
// 3. Every pair contributes 0 to the total sum.
//
// 4. Add `n / 2` such pairs.
//
// 5. If `n` is odd, one element is still required.
//    Add `0`, which does not change the sum.
//
// 6. Return the constructed array.

// Time Complexity: O(n)
// Space Complexity: O(n)


class Solution {
    public int[] sumZero(int n) {
        int[] ans = new int[n];
        int index = 0;

        for (int i = 1; i <= n / 2; i++) {
            ans[index++] = i;
            ans[index++] = -i;
        }

        if (n % 2 == 1) {
            ans[index] = 0;
        }

        return ans;
    }
}
