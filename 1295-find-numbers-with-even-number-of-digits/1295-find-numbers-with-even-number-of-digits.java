// Problem: Find Numbers wwith Even Number of Digits
// Link: https://leetcode.com/problems/find-numbers-with-even-number-of-digits/
// Difficulty: Easy

// Approach:
// Count the number of digits in every number and check whether
// the digit count is even.
//
// 1. Traverse every number in the array.
//
// 2. For each number:
//      - Repeatedly divide it by 10.
//      - Each division removes one digit.
//      - Count how many divisions are performed.
//
// 3. If the digit count is even, increment the answer.
//
// 4. Return the total count.

// Time Complexity: O(n × log(max(nums)))
// Space Complexity: O(1)


class Solution {
    public int findNumbers(int[] nums) {
        int ans = 0;

        for (int num : nums) {
            int count = 0;
            while (num > 0) {
                count++;
                num /= 10;
            }

            if (count % 2 == 0) {
                ans++;
            }
        }

        return ans;
    }
}
