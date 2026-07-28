// Problem: Minimum Increment to Make Array Unique
// Link: https://leetcode.com/problems/minimum-increment-to-make-array-unique/
// Difficulty: Medium

// Approach:
// To minimize the number of increments, first sort the array.
// After sorting, duplicates and smaller conflicting values become adjacent,
// allowing us to process them greedily from left to right.
// Maintain:
// - numTracker : the smallest value that the current element is allowed to take
//                so that all processed elements remain unique.
// Step 1:
// Sort the array in ascending order.
// Step 2:
// Traverse each number in the sorted array.
// Before assigning a value to the current element,
// update:
// numTracker = max(numTracker, currentNumber)
// This means:
// - If the current number is already greater than all previously assigned values,
//   keep it as it is.
// - Otherwise, assign it the next available unique value stored in numTracker.
// Step 3:
// The number of increments required for the current element is:
// assignedValue - originalValue
// i.e.
// moves += numTracker - currentNumber
// Step 4:
// Reserve the next unique value for future elements by incrementing:
// numTracker++
// Step 5:
// After processing every element, return the total number of moves.

// Time Complexity:
// O(n log n)
// (Sorting dominates the complexity.)
// Space Complexity:
// O(1)
// (Ignoring the space used by the sorting algorithm.)


class Solution {
    public int minIncrementForUnique(int[] nums) {
        Arrays.sort(nums);
        int moves = 0; // Counts the total increments required.
        int numTracker = 0; // Tracks the next unique number that should be set.

        for (int num : nums) {
            numTracker = Math.max(numTracker, num);
            moves += numTracker - num;
            numTracker += 1;
        }

        return moves;
    }
}
