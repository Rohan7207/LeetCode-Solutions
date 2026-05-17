// Problem: Majority Element
// Link: https://leetcode.com/problems/majority-element/
// Difficulty: Easy

// Approach:
// Find the majority element using Boyer-Moore Voting Algorithm.
// The idea is that the majority element appears more than n/2 times,
// so it will survive even after cancelling out different elements.
// Maintain two variables:
//     - element: current candidate for majority
//     - count: vote balance for the candidate
// For every element in the array:
//     - If current element == candidate:
//           increment count
//     - Else:
//           decrement count
// If count becomes 0:
//     - Choose current element as new candidate
//     - Reset count to 1
// At the end, the remaining candidate is the majority element.

// Time Complexity: O(n)
// Space Complexity: O(1)


class Solution {
    public int majorityElement(int[] nums) {
        int count = 1;
        int candidate = nums[0];

        for (int i = 1; i < nums.length; i++) {
            if (candidate == nums[i])
                count++;
            else
                count--;

            if (count == 0) {
                candidate = nums[i];
                count = 1;
            }
        }

        return candidate;
    }
}
