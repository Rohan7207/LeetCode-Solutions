// Problem: Smallest Missing Integer Greater Than Sequential Prefix Sum
// Link: https://leetcode.com/problems/smallest-missing-integer-greater-than-sequential-prefix-sum/?envType=daily-question&envId=2026-08-11
// Difficulty: Easy

// Approach:
// 1. Find the longest consecutive prefix starting from nums[0].
//    For example:
//       [1, 2, 3, 5, 6]
//        └───┘
//    The consecutive prefix is [1, 2, 3].
//
// 2. Calculate the sum of this consecutive prefix.
//    This gives the first candidate value.
//
// 3. Store all elements of nums in a HashSet so we can check
//    whether a candidate value already exists in O(1) average time.
//
// 4. If the prefix sum already exists in the array, keep increasing
//    it until we find a value that does not exist.
//
// 5. Return that first missing value.

// Time Complexity: O(n)
// Space Complexity: O(n)


class Solution {
    public int missingInteger(int[] nums) {
        Set<Integer> set = new HashSet<>();
        int prefixSum = nums[0];

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == nums[i - 1] + 1) {
                prefixSum += nums[i];
            } else {
                break;
            }
        }

        for (int num : nums) {
            set.add(num);
        }

        while (set.contains(prefixSum)) {
            prefixSum++;
        }

        return prefixSum;
    }
}
