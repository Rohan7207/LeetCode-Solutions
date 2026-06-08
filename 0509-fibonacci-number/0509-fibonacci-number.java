// Problem: Fibonacci Number
// Link: https://leetcode.com/problems/fibonacci-number/
// Difficulty: Easy

// Approach:
// Compute the nth Fibonacci number using
// space-optimized dynamic programming.
// Fibonacci sequence:
//     F(0) = 0
//     F(1) = 1
//     F(n) = F(n-1) + F(n-2)
// Instead of storing all previous Fibonacci values,
// keep only the last two numbers:
//     first  -> F(n-2)
//     second -> F(n-1)
// For every iteration:
//     - Store current second temporarily
//     - Compute next Fibonacci number:
//           second = first + second
//     - Move first forward:
//           first = previous second
// Repeat until nth Fibonacci number is generated.
// Finally return second.

// Time Complexity: O(n)
// Space Complexity: O(1)


class Solution {
    public int fib(int n) {
        int first = 0;
        int second = 1;

        if(n == 0) return 0;

        for(int i = 1; i < n; i++) {
            int ans = second;
            second = first + second;
            first = ans;
        }

        return second;
    }
}
