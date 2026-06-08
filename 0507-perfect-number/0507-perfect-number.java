// Problem : Perfect Number
// Link : https://leetcode.com/problems/perfect-number/
// Difficulty : Easy

// Approach:
// Check whether a number is a Perfect Number.
// A perfect number is a number whose sum of
// proper divisors (excluding itself)
// is equal to the number itself.
// First handle odd numbers:
//     - All perfect numbers in the valid range
//       are even
//     - So if number is odd, return false
// Initialize:
//     end = num / 2
// Since no proper divisor can be greater than num/2.
// Start sum with:
//     sum = end
// because num/2 is always a divisor for even numbers.
// Traverse from 1 to end - 1:
//     - If i divides num:
//           add i to sum
//     - If sum becomes greater than num:
//           return false immediately
// After traversal:
//     - If sum equals num:
//           number is perfect
//     - Otherwise:
//           not a perfect number

// Time Complexity: O(n)
// Space Complexity: O(1)


class Solution {
    public boolean checkPerfectNumber(int num) {
        if (num % 2 != 0) return false;
        int end = num / 2;
        int sum = end;

        for (int i = 1; i < end; i++) {
            if (num % i == 0) sum += i;
            if (sum > num) return false;
        }

        if (sum == num) return true;
        return false;
    }
}
