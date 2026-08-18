// Problem: Find the Largest Almost Missing Integer
// Link: https://leetcode.com/problems/find-the-largest-almost-missing-integer/?envType=daily-question&envId=2026-08-18
// Difficulty: Easy

// Approach:
// The key is to determine which elements can appear in exactly one
// subarray of length k.
//
// 1. If k == n, there is only one subarray: the entire array.
//    Therefore, the largest element can be returned directly.
//
// 2. Count the frequency of every value in nums.
//
// 3. If k == 1, every subarray contains exactly one element.
//    Therefore, an element can be chosen uniquely only if it occurs
//    exactly once in the array. Find the largest such element.
//
// 4. If 1 < k < n:
//    - nums[0] belongs to only one length-k subarray: the subarray
//      starting at index 0.
//    - nums[n-1] belongs to only one length-k subarray: the subarray
//      ending at index n-1.
//    - Every middle element belongs to at least two length-k subarrays.
//
// 5. Therefore, only the first and last elements can be valid candidates.
//    If either occurs exactly once in the whole array, consider it and
//    return the larger candidate.

// Time Complexity: O(n)
// Space Complexity: O(1)
// Since the frequency array has a fixed size of 51.


class Solution {
    public int largestInteger(int[] nums, int k) {
        int n = nums.length;
        
        if(k == n) {
            int res = nums[0];
            for(int num : nums) {
                res = Math.max(res, num);
            }

            return res;
        }

        int[] count = new int[51];
        for(int x : nums) {
            count[x]++;
        }

        if(k == 1) {
            for(int i = 50; i >= 0; i--) {
                if(count[i] == 1) {
                    return i;
                }
            }

            return -1;
        }

        int res = -1;
        if(count[nums[0]] == 1) {
            res = Math.max(res, nums[0]);
        }

        if(count[nums[n - 1]] == 1) {
            res = Math.max(res, nums[n - 1]);
        }

        return res;
    }
}
