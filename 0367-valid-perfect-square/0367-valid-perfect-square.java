// Problem: Valid Perfect Square
// Link: https://leetcode.com/problems/valid-perfect-square/
// Difficulty: Easy

// Approach:
// Use Binary Search to find the integer
// square root of the number.
// Search in the range [0, num].
// For every mid:
//     - Check whether mid * mid <= num.
//     - To avoid overflow, use:
//           mid <= num / mid
//     - If true, store mid as a possible
//       square root and search on the right.
//     - Otherwise search on the left.
// After binary search,
// check whether:
//     result * result == num
// If yes, num is a perfect square.

// Time Complexity: O(log n)
// Space Complexity: O(1)


class Solution {
    public boolean isPerfectSquare(int num) {
        if (num == 1) return true;

        int low = 0;
        int high = num;
        int result = 0;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (mid <= num / mid) {
                result = mid;
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        if (result * result == num) return true;

        return false;
    }
}
