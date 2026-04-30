// Problem: Climbing Stairs
// Link: https://leetcode.com/problems/climbing-stairs/
// Difficulty: Easy

// Approach:
// Use dynamic programming with two variables
// to store the previous two staircase counts.
// For each step, calculate the current number
// of ways as the sum of the previous two values.
// Update the variables and continue until n.
// Return the final number of ways.

// Time Complexity: O(n)
// Space Complexity: O(1)


class Solution {
    public int climbStairs(int n) {
        if (n == 0 || n == 1) return 1;

        int prev1 = 1;
        int prev2 = 1;

        for (int i = 2; i <= n; i++) {
            int current = prev1 + prev2; 
            prev2 = prev1; 
            prev1 = current; 
        }

        return prev1;
    }
}
