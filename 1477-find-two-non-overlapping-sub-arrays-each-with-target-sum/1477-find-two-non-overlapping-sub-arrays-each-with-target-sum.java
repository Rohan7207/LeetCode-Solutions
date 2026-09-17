// Problem: Find Two Non-overlapping Sub-arrays Each With Target Sum
// Link: https://leetcode.com/problems/find-two-non-overlapping-sub-arrays-each-with-target-sum/?envType=daily-question&envId=2026-09-17
// Difficulty: Medium

// Approach:
// Use Sliding Window + Prefix Minimum DP.
//
// 1. Since all array values are positive, use sliding window to find
//    every subarray whose sum equals target.
//
// 2. bestMin stores the shortest valid subarray found so far.
//
// 3. When a valid subarray starts at index i, check the shortest
//    previous subarray ending before i using minLenTillIdx[i - 1].
//
// 4. Combine both lengths and update res.
//
// 5. Store the best minimum length up to the current index.

// Time Complexity: O(n)
// Space Complexity: O(n)


class Solution {
    public int minSumOfLengths(int[] arr, int target) {
        int n = arr.length;
        int[] minLenTillIdx = new int[n];

        for (int i = 0; i < n; i++) {
            minLenTillIdx[i] = Integer.MAX_VALUE;
        }

        int i = 0, j = 0;
        int currSum = 0;
        int bestMin = Integer.MAX_VALUE;
        int res = Integer.MAX_VALUE;
        while (j < n) {
            currSum += arr[j];

            while (i < j && currSum > target) {
                currSum -= arr[i];
                i++;
            }

            if (currSum == target) {
                int len = j - i + 1;

                if (i > 0 && minLenTillIdx[i - 1] != Integer.MAX_VALUE) {
                    res = Math.min(res, len + minLenTillIdx[i - 1]);
                }

                bestMin = Math.min(bestMin, len);
            }

            minLenTillIdx[j] = bestMin;
            j++;
        }

        return res == Integer.MAX_VALUE ? -1 : res;
    }
}
