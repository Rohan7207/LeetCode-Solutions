// Problem: Count Commas in Range II
// Link: https://leetcode.com/problems/count-commas-in-range-ii/?envType=daily-question&envId=2026-09-09
// Difficulty: Easy

// Approach:
// Use Mathematical Counting + Digit-Place Observation.
//
// 1. A comma appears starting from 1,000.
// 2. For the thousands place, every number from 1,000 to n
//    contributes one comma.
// 3. Starting from 1,000,000, every number contributes another
//    comma, so we add the count of numbers from 1,000,000 to n.
// 4. Continue multiplying start by 1000 to handle:
//      1,000       → 1 comma
//      1,000,000   → 2 commas
//      1,000,000,000 → 3 commas
// 5. For each comma position, add:
//
//      n - start + 1
//
// 6. Stop when start > n.

// Time Complexity: O(log₁₀ n)
// Space Complexity: O(1)


class Solution {
    public long countCommas(long n) {
        long res = 0;
        long start = 1000;

        while (start <= n) {
            res += (n - start + 1);

            start *= 1000;
        }

        return res;
    }
}
