// Problem: Valid Mountain Array
// Link: https://leetcode.com/problems/valid-mountain-array/
// Difficulty: Easy

// Approach:
// A valid mountain array has two distinct phases:
// 1. A strictly increasing sequence.
// 2. A strictly decreasing sequence.
// Also:
// - The array must contain at least 3 elements.
// - The peak cannot be the first or the last element.
//
// Step 1:
// If the array size is less than 3, return false.
//
// Step 2:
// Start from index 1 and keep moving forward while the current element
// is strictly greater than the previous element.
// This traverses the increasing part of the mountain.
// Set flag1 to true if an increasing sequence exists.
//
// Step 3:
// From the stopping point, continue moving forward while the current
// element is strictly smaller than the previous element.
// This traverses the decreasing part.
// Set flag2 to true if a decreasing sequence exists.
//
// Step 4:
// If we successfully reach the end of the array and both increasing
// and decreasing phases were present, then the array is a valid mountain.
//
// Otherwise, return false.

// Time Complexity: O(n)
// Space Complexity: O(1)


class Solution {
    public boolean validMountainArray(int[] arr) {
        boolean flag1 = false;
        boolean flag2 = false;
        int n = arr.length;

        if (n < 3) {
            return false;
        }

        int i = 1;

        while (i < n && arr[i] > arr[i - 1]) {
            i++;
            flag1 = true;
        }

        while (i < n && arr[i] < arr[i - 1]) {
            i++;
            flag2 = true;
        }

        if (i == n && flag1 && flag2) {
            return true;
        }

        return false;
    }
}
