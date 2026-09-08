// Problem: Count Commas in Range
// Link: https://leetcode.com/problems/count-commas-in-range/?envType=daily-question&envId=2026-09-08
// Difficulty: Easy

// Approach:
// Use a simple mathematical observation.
//
// 1. A comma first appears when writing 1000.
//
// 2. Every integer from 1000 to n contributes exactly one comma
//    (for this problem's range).
//
// 3. Therefore, the number of commas is the count of integers
//    in the inclusive range [1000, n]:
//        n - 1000 + 1
//    which simplifies to:
//        n - 999
//
// 4. If n < 1000, no number contains a comma, so return 0.

// Time Complexity: O(1)
// Space Complexity: O(1)


class Solution {
    public int countCommas(int n) {
        if (n < 1000) {
            return 0;
        }

        return n - 999;
    }
}
