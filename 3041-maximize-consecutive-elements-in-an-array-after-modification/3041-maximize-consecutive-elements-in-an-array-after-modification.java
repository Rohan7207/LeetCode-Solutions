// Problem: Maximize Consecutive Elements in an Array After Modification
// Link: https://leetcode.com/problems/maximize-consecutive-elements-in-an-array-after-modification/?envType=daily-question&envId=2026-11-20
// Difficulty: Hard

// Approach:
// 1. Sort the array so that elements can be processed in increasing order.
//
// 2. Create dp[n][2]:
//    dp[i][0] = Maximum subsequence length starting at index i
//                when nums[i] is NOT incremented.
//    dp[i][1] = Maximum subsequence length starting at index i
//                when nums[i] IS incremented.
//
// 3. Traverse the array from right to left.
//
// 4. For each index i, consider both possibilities:
//    inc = 0 -> current value = nums[i]
//    inc = 1 -> current value = nums[i] + 1
//
// 5. Option 1: Find a later element equal to currVal + 1.
//    This element continues the consecutive sequence without incrementing.
//
//    Update:
//    len = 1 + dp[j][0]
//
// 6. Option 2: Find a later element equal to currVal.
//    This element must be incremented by 1 to continue the sequence.
//
//    Update:
//    len = 1 + dp[j][1]
//
// 7. Use binary search to find the first occurrence of the required value.
//
// 8. Update dp[i][inc] and maintain the maximum answer.
//
// 9. Return the maximum subsequence length.

// Time Complexity: O(n log n)
// Space Complexity: O(n)


class Solution {
    public int maxSelectedElements(int[] nums) {
        int n = nums.length;
        Arrays.sort(nums);

        int[][] dp = new int[n + 1][2];

        int res = 1;
        for (int i = n - 1; i >= 0; i--) { 
            for (int inc = 0; inc < 2; inc++) {
                int currVal = nums[i] + inc;
                int len = 1;

                // Option 1: Find currVal + 1 directly
                int j = findMethod(nums, i + 1, currVal + 1);
                if (j != -1) {
                    len = Math.max(len, 1 + dp[j][0]);
                }

                // Option 2: Find currVal and then inc +1
                j = findMethod(nums, i + 1, currVal);
                if (j != -1) {
                    len = Math.max(len, 1 + dp[j][1]);
                }

                dp[i][inc] = len;
                res = Math.max(res, dp[i][inc]);
            }
        }

        return res;
    }

    private int findMethod(int[] nums, int start, int target) { // O(logn)
        int end = nums.length - 1;
        int ans = -1;

        while (start <= end) {
            int mid = start + (end - start) / 2;

            if (nums[mid] == target) {
                ans = mid; 
                end = mid - 1; // Keep looking left for the first occurrence
            } else if (nums[mid] < target) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }

        return ans;
    }
}
