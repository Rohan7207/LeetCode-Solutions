// Problem: N-th Tribonacci Number
// Link: https://leetcode.com/problems/n-th-tribonacci-number/
// Difficulty: Easy

// Approach:
// Use an iterative Dynamic Programming approach with only three variables.
//
// 1. Handle the base cases:
//      T(0) = 0
//      T(1) = 1
//      T(2) = 1
//
// 2. Maintain three variables:
//      a = T(i-3)
//      b = T(i-2)
//      c = T(i-1)
//
// 3. For every i from 3 to n:
//      next = a + b + c
//
// 4. Shift the variables forward:
//      a = b
//      b = c
//      c = next
//
// 5. After the loop, c contains T(n).

// Time Complexity: O(n)
// Space Complexity: O(1)


class Solution {
    public int tribonacci(int n) {
        if (n == 0) return 0;
        if (n == 1 || n == 2) return 1;

        int a = 0, b = 1, c = 1;

        for (int i = 3; i <= n; i++) {
            int next = a + b + c;
            a = b;
            b = c;
            c = next;
        }

        return c;
    }
}
