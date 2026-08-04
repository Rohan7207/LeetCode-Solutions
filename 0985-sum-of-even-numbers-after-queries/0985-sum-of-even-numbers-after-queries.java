// Problem: Sum of Even Numbers After Queries
// Link: https://leetcode.com/problems/sum-of-even-numbers-after-queries/
// Difficulty: Medium

// Approach:
// First, compute the sum of all even numbers in the array.
// This running sum will be updated after every query instead
// of recomputing it from scratch.
//
// For each query:
// 1. Let the query update nums[index] by adding val.
//
// 2. Before applying the update, check whether the current
//    value at nums[index] is even.
//    - If it is even, it is currently contributing to the
//      running even sum, so remove it.
//
// 3. Apply the update:
//      nums[index] += val
//
// 4. After the update, check whether the new value is even.
//    - If it is even, add it back to the running even sum.
//
// 5. Store the updated running even sum as the answer for
//    the current query.
//
// By updating only the affected element's contribution, each
// query is processed in constant time.

// Time Complexity:
// O(n + q)
//
// Space Complexity:
// O(1)
// (excluding the output array)


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
