// Problem: Number of Steps to Reduce a Number to Zero
// Link: https://leetcode.com/problems/number-of-steps-to-reduce-a-number-to-zero/
// Difficulty: Easy

// Approach:
// Use Simulation + Mathematical Reduction.
//
// 1. Start with steps = 0 and repeatedly apply the given operations
//    until num becomes 0.
//
// 2. If num is even, divide it by 2.
//
// 3. If num is odd, subtract 1 to make it even.
//
// 4. Increment steps after every operation.
//
// 5. When num reaches 0, return the total number of operations.

// Time Complexity: O(log n)
// Space Complexity: O(1)


class Solution {
    public int numberOfSteps(int num) {
        int steps = 0;

        while (num > 0) {
            if (num % 2 == 0) {
                num -= num / 2;
            } else {
                num -= 1;
            }

            steps++;
        }

        return steps;
    }
}
