// Problem: Count the Digits That Divide a Number
// Link: https://leetcode.com/problems/count-the-digits-that-divide-a-number/
// Difficulty: Easy

// Approach:
// Extract each digit of num and check whether the original number
// is divisible by that digit.
//
// 1. Store the original number because num will be reduced to 0.
//
// 2. Extract the last digit using:
//      digit = num % 10
//
// 3. Check:
//      original % digit == 0
//
//    If true, increment count.
//
// 4. Remove the last digit:
//      num /= 10
//
// 5. Continue until all digits are processed.
//
// 6. Return count.

// Time Complexity: O(log10(num))
// Space Complexity: O(1)


class Solution {
    public int countDigits(int num) {
        int count = 0;
        int original = num;

        while (num > 0) {
            int digit = num % 10;

            if (original % digit == 0) {
                count++;
            }

            num /= 10;
        }

        return count;
    }
}
