// Problem: Sqrt(x)
// Link: https://leetcode.com/problems/sqrtx/
// Difficulty: Easy

// Approach:
// Use binary search to find the integer square root.
// Handle edge case where x is 0.
// Set search range from 1 to x.
// Perform binary search:
//     - Find the middle value.
//     - If mid * mid is less than or equal to x,
//       store mid as a possible answer
//       and search on the right side
//       for a larger valid value.
//     - Otherwise search on the left side.
// Use mid <= x / mid instead of mid * mid
// to avoid integer overflow.
// Return the stored result.

// Time Complexity: O(log n)
// Space Complexity: O(1)

class Solution {
    public int mySqrt(int x) {
        if (x == 0)
            return 0;

        int low = 1;
        int high = x;
        int result = 0;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (mid <= x / mid) {
                result = mid;
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return result;
    }
}
