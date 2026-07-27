// Problem: Peak Index in a Mountain Array
// Link: https://leetcode.com/problems/peak-index-in-a-mountain-array/
// Difficulty: Medium

// Approach:
// A mountain array first increases and then decreases, so it contains
// exactly one peak element.
// Use Binary Search to locate the peak efficiently.
// Step 1:
// Initialize the search range from index 1 to n - 2 because the peak
// cannot be the first or the last element.
// Step 2:
// Compute the middle index.
// Step 3:
// Check whether the middle element is the peak.
// If:
// arr[mid - 1] < arr[mid] && arr[mid] > arr[mid + 1]
// then mid is the peak, so return it.
// Step 4:
// Otherwise, determine which side of the mountain you're on.
// - If arr[mid - 1] < arr[mid], you're on the increasing slope.
//   The peak lies to the right, so move:
//      left = mid + 1
// - Otherwise, you're on the decreasing slope.
//   The peak lies to the left, so move:
//      right = mid - 1
// Continue until the peak is found.

// Time Complexity: O(log n)
// Space Complexity: O(1)


class Solution {
    public int peakIndexInMountainArray(int[] arr) {
        int l = 1;
        int h = arr.length - 2; //bcz peek cannot be start and last value

        while (l <= h) {
            int mid = l + (h - l) / 2;

            if (arr[mid - 1] < arr[mid] && arr[mid] > arr[mid + 1]) {
                return mid;
            } else if (arr[mid - 1] < arr[mid]) {
                l = mid + 1;
            } else {
                h = mid - 1;
            }
        }

        return -1;
    }
}
