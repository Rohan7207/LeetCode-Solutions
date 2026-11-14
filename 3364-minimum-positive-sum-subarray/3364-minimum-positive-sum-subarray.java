// Problem: Minimum Positive Sum Subarray
// Link: https://leetcode.com/problems/minimum-positive-sum-subarray/?envType=daily-question&envId=2026-11-14
// Difficulty: Easy

// Approach:
// 1. Create a prefix sum array.
//
// 2. prefixSum[i] stores the sum of elements from index 0 to i.
//
// 3. Fix every starting index i.
//
// 4. Try every possible subarray length from l to r.
//
// 5. Calculate the ending index:
//    j = i + len - 1
//
// 6. If j is within array bounds, calculate:
//    sum = prefixSum[j] - prefixSum[i - 1]
//
//    If i == 0, simply use prefixSum[j].
//
// 7. If sum > 0, update the minimum answer.
//
// 8. Return -1 if no positive subarray sum exists.

// Time Complexity: O(n * (r - l + 1))
// Space Complexity: O(n)


class Solution {
    public int minimumSumSubarray(List<Integer> nums, int l, int r) {
        int n = nums.size();
        int sum = 0;
        int ans = Integer.MAX_VALUE;

        int[] prefixSum = new int[n + 1];
        prefixSum[0] = nums.get(0);
        for (int i = 1; i < n; i++) { 
            prefixSum[i] = prefixSum[i - 1] + nums.get(i);
        }

        for (int i = 0; i < n; i++) { 
            for (int len = l; len <= r && i + len - 1 < n; len++) { 
                int j = i + len - 1; 
                sum = prefixSum[j] - (i == 0 ? 0 : prefixSum[i - 1]); 

                if (sum > 0) {
                    ans = Math.min(ans, sum);
                }
            }
        }

        return ans == Integer.MAX_VALUE ? -1 : ans;
    }
}
