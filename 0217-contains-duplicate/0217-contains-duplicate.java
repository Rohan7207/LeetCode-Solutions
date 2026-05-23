// Problem: Contains Duplicate
// Link: https://leetcode.com/problems/contains-duplicate/
// Difficulty: Easy

// Approach:
// Use a HashSet to keep track of
// elements seen so far.
// Traverse the array:
//     - Try to insert the current element
//       into the HashSet.
//     - If insertion fails,
//       the element already exists,
//       so a duplicate is found.
//       Return true immediately.
//
// If all elements are processed without
// finding duplicates, return false.

// Time Complexity: O(n)
// Space Complexity: O(n)


class Solution {
    public boolean containsDuplicate(int[] nums) {
        Set<Integer> seen = new HashSet<>();

        for (int num : nums) {
            if (!seen.add(num)) {
                return true;
            }
        }
        
        return false;
    }
}
