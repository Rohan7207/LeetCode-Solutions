// Problem: Substract the Product and Sum of Digits of an Integer
// Link: https://leetcode.com/problems/subtract-the-product-and-sum-of-digits-of-an-integer/
// Difficulty: Easy

// Approach:
// Extract each digit of n and simultaneously calculate its sum
// and product.
//
// 1. Initialize:
//      sum = 0
//      product = 1
//
// 2. Extract the last digit using:
//      n % 10
//
// 3. Add the digit to sum and multiply it into product.
//
// 4. Remove the last digit using:
//      n /= 10
//
// 5. Repeat until all digits are processed.
//
// 6. Return product - sum.

// Time Complexity: O(log10(n))
// Space Complexity: O(1)


class Solution {
    public int subtractProductAndSum(int n) {
        int product = 1;
        int sum = 0;

        while (n != 0) {
            int lastDigit = n % 10;

            sum += lastDigit;
            product *= lastDigit;

            n /= 10;
        }

        return product - sum;
    }
}
