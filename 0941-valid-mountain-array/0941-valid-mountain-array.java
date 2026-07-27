// Problem: Valid Mountain Array
// Link: https://leetcode.com/problems/valid-mountain-array/
// Difficulty: Easy

// Approach:
// A valid mountain array has exactly two phases:
//
// 1. A strictly increasing sequence.
// 2. A strictly decreasing sequence.
//
// The peak must lie somewhere in the middle, so it cannot be the
// first or the last element.
//
// Step 1:
// If the array has fewer than 3 elements, it cannot form a mountain.
//
// Step 2:
// Start from index 1 and keep moving forward while the current element
// is strictly greater than the previous element.
// This traverses the increasing part.
//
// Step 3:
// After the increasing traversal:
//
// - If i == 1, there was no increasing part.
// - If i == n, the array is only increasing.
//
// In either case, return false because a valid peak does not exist.
//
// Step 4:
// Continue from the current position and keep moving forward while the
// current element is strictly smaller than the previous element.
// This traverses the decreasing part.
//
// Step 5:
// If the traversal reaches the end of the array (i == n),
// both increasing and decreasing phases were completed successfully,
// so the array is a valid mountain.
//
// Otherwise, return false.

// Time Complexity: O(n)
// Space Complexity: O(1)


class Solution {
    public boolean validMountainArray(int[] arr) {
        int n = arr.length;

        if (n < 3) {
            return false;
        }

        int i = 1;

        while (i < n && arr[i] > arr[i - 1]) {
            i++;
        }

        if (i == 1 || i == n) {
            return false;
        }

        while (i < n && arr[i] < arr[i - 1]) {
            i++;
        }

        return i == n;
    }
}
