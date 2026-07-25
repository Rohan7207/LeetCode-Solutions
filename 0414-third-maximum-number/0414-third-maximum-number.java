// Problem: Third Maximum Number
// Link: https://leetcode.com/problems/third-maximum-number/
// Difficulty: Easy

// Approach:
// Traverse the array once while maintaining the three largest distinct
// numbers seen so far.
// Maintain three variables:
// - first  -> largest distinct number.
// - second -> second largest distinct number.
// - third  -> third largest distinct number.
// Initialize all three with Long.MIN_VALUE so they are smaller than any
// possible integer value.
// For every number:
// 1. If the number is already equal to first, second, or third,
//    skip it because only distinct values are considered.
// 2. If the number is greater than or equal to first,
//    shift first to second, second to third,
//    and update first with the current number.
// 3. Otherwise, if the number is greater than or equal to second,
//    shift second to third,
//    and update second.
// 4. Otherwise, if the number is greater than or equal to third,
//    update third.
// After processing all elements:
// - If third was never updated, there are fewer than three distinct numbers,
//   so return the largest number (first).
// - Otherwise, return the third largest distinct number.

// Time Complexity: O(n)
// Space Complexity: O(1)


class Solution {
    public int thirdMax(int[] nums) {
        long first = Long.MIN_VALUE;
        long second = Long.MIN_VALUE;
        long third = Long.MIN_VALUE;

        for (int num : nums) {
            // This number is already used once, thus we skip it.
            if (first == num || second == num || third == num) {
                continue;
            }

            if (first <= num) {
                third = second;
                second = first;
                first = num;
            } else if (second <= num) {
                third = second;
                second = num;
            } else if (third <= num) {
                third = num;
            }
        }

        if (third == Long.MIN_VALUE) {
            return (int) first;
        }

        return (int) third;
    }
}
