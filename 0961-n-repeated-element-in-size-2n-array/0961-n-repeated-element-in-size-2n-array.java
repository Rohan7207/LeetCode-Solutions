// Problem: N-Repeated Elements in Size 2N Array
// Link: https://leetcode.com/problems/n-repeated-element-in-size-2n-array/
// Difficulty: Easy

// Approach:
// We are given an array of size 2N where exactly one element appears N times
// and every other element appears only once.
// Key Observation:
// The repeated element must appear at least twice within a distance of 2.
// Why?
// - If every occurrence of the repeated element were separated by at least
//   two other elements, there wouldn't be enough positions in an array of
//   size 2N to place all N occurrences.
// - Therefore, at least one pair of repeated elements must be either:
//      1. Adjacent      -> A[i] == A[i + 1]
//      2. One apart     -> A[i] == A[i + 2]
// Algorithm:
// 1. Traverse the array from index 0 to n-3.
// 2. For each index, compare:
//      - A[i] with A[i + 1]
//      - A[i] with A[i + 2]
// 3. If either comparison matches, return A[i] immediately.
// 4. If no match is found during the loop, the only remaining possible
//    arrangement is that the repeated element is the last element.
//    Hence, return A[n - 1].

// Time Complexity: O(n)
// Space Complexity: O(1)

class Solution {
    public int repeatedNTimes(int[] nums) {
        for (int i = 0; i < nums.length - 2; i++)
            if (nums[i] == nums[i + 1] || nums[i] == nums[i + 2]) {
                return nums[i];
            }

        return nums[nums.length - 1];
    }
}  
