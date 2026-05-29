// Problem: Minimum Element After Replacement With Digit Sum
// Link: https://leetcode.com/problems/minimum-element-after-replacement-with-digit-sum/?envType=daily-question&envId=2026-05-29
// Difficulty: Easy

// Approach:
// Traverse each number in the array.
// For every number:
//     - Extract its digits using modulo (% 10).
//     - Compute the sum of all digits.
//     - Compare the digit sum with the
//       current minimum digit sum.
// Keep updating the minimum digit sum
// encountered so far.
// Return the minimum digit sum.

// Time Complexity: O(n × d)
// Space Complexity: O(1)
//
// d = number of digits in a number


class Solution {
    public int minElement(int[] nums) {
        int min = Integer.MAX_VALUE;

        for (int num : nums) {
            int sum = 0;

            while (num != 0) {
                sum += num % 10;
                num /= 10;
            }

            min = Math.min(min, sum);
        }

        return min;
    }
}
