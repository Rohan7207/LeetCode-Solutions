// Problem: Sum of Squares of Special Elements
// Link: https://leetcode.com/problems/sum-of-squares-of-special-elements/?envType=daily-question&envId=2026-10-11
// Difficulty: Easy

// Approach:
// 1. Let n be the length of nums.
// 2. Initialize sum with the square of the last element because its 1-based index n always divides n.
// 3. Traverse 1-based indices i from 1 to n / 2.
// 4. If n % i == 0, index i is a divisor of n.
// 5. Add the square of nums[i - 1] to sum because array indices are 0-based.
// 6. Return sum.

// Time Complexity: O(n)
// Space Complexity: O(1)


class Solution {
    public int sumOfSquares(int[] nums) {
        int n = nums.length;
        int sum = nums[n - 1] * nums[n - 1];

        for (int i = 1; i <= n / 2; i++) {
            if (n % i == 0) {
                sum += (nums[i - 1] * nums[i - 1]);
            }
        }

        return sum;
    }
}
