// Problem: Find Subarrays With Equal Sum
// Link: https://leetcode.com/problems/find-subarrays-with-equal-sum/
// Difficulty: Easy

// Approach:
// Use HashSet + Adjacent Pair Sum.
//
// 1. Traverse every adjacent pair in the array.
// 2. Calculate the sum of the current pair.
// 3. If the sum already exists in the HashSet, return true.
// 4. Otherwise, add the sum to the HashSet.
// 5. If no duplicate sum is found, return false.

// Time Complexity: O(n)
// Space Complexity: O(n)


class Solution {
    public boolean findSubarrays(int[] nums) {
        int currSum = 0;
        Set<Integer> seen = new HashSet<>();

        for (int i = 0; i < nums.length - 1; i++) {
            currSum = nums[i] + nums[i + 1];

            if (seen.contains(currSum)) {
                return true;
            }

            seen.add(currSum);
        }

        return false;
    }
}
