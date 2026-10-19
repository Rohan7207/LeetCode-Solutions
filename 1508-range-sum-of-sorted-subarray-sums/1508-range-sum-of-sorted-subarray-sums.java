// Problem: Range Sum of Sorted Subarray Sums
// Link: https://leetcode.com/problems/range-sum-of-sorted-subarray-sums/
// Difficulty: Medium

// Approach:
// 1. Create an array to store the sums of all contiguous subarrays.
//    There are n * (n + 1) / 2 possible subarrays.
// 2. Fix the starting index i from 0 to n - 1.
// 3. Initialize sum = 0 for each starting index.
// 4. Move the ending index j from i to n - 1, adding nums[j]
//    to sum to calculate each subarray sum incrementally.
// 5. Store every calculated sum in subArray.
// 6. Sort subArray in ascending order.
// 7. Sum the elements from index left - 1 to right - 1,
//    applying modulo MOD at every addition.
// 8. Return the final answer.

// Time Complexity: O(n^2 log n), due to generating O(n^2) sums
// and sorting them.
// Space Complexity: O(n^2), for storing all subarray sums.


class Solution {

    private int MOD = 1000000007;

    public int rangeSum(int[] nums, int n, int left, int right) {
        int[] subArray = new int[n * (n + 1) / 2];

        int idx = 0;
        for (int i = 0; i < n; i++) {
            int sum = 0;
            for (int j = i; j < n; j++) {
                sum += nums[j];
                subArray[idx++] = sum;
            }
        }

        Arrays.sort(subArray);

        int ans = 0;
        for (int i = left - 1; i < right; i++) {
            ans = (ans + subArray[i]) % MOD;
        }

        return ans;
    }
}
