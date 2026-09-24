// Problem: Smallest Index With Digit Sum Equal to Index
// Link: https://leetcode.com/problems/smallest-index-with-digit-sum-equal-to-index/?envType=daily-question&envId=2026-09-24
// Difficulty: Easy

// Approach:
//
// 1. Traverse the array from left to right because we need the
//    SMALLEST index satisfying the condition.
//
// 2. For every index `i`, take the corresponding value `nums[i]`.
//
// 3. Calculate the sum of digits of `nums[i]`:
//    - `x % 10` gives the last digit.
//    - `x /= 10` removes the last digit.
//    - Repeat until all digits are processed.
//
// 4. Compare the digit sum with the current index:
//
//       i == digitSum
//
// 5. Since we traverse from index 0 onward, the first match is
//    automatically the smallest valid index.
//
// 6. If no index satisfies the condition, return `-1`.

// Time Complexity: O(n × d), where d = number of digits in nums[i]
// Space Complexity: O(1)


class Solution {
    public int smallestIndex(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            int x = nums[i];
            int sum = 0;

            while (x > 0) {
                sum += x % 10;
                x /= 10;
            }

            if (i == sum) {
                return i;
            }
        }

        return -1;
    }
}
