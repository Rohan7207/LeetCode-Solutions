// Problem: Largest Number At Least Twice of Others
// Link: https://leetcode.com/problems/largest-number-at-least-twice-of-others/
// Difficulty: Easy

// Approach:
// We need to check whether the largest number
// is at least twice the second largest number.
// Why only second largest?
//     If largest >= 2 * secondLargest,
//     then largest is automatically at least twice
//     every smaller number also.
// So instead of checking largest with every element,
// find:
//     max       -> largest number
//     secondMax -> second largest number
//     index     -> index of largest number
// Traverse the array:
//     If nums[i] > max:
//         Current number becomes new largest.
//         Old max becomes secondMax.
//         Update index.
//     Else if nums[i] > secondMax:
//         Current number becomes secondMax.
// After traversal:
//     If max >= 2 * secondMax:
//         return index
//     Else:
//         return -1

// Time Complexity: O(n)
// Space Complexity: O(1)


class Solution {
    public int dominantIndex(int[] nums) {
        int max = -1;
        int secondMax = -1;
        int index = 0;

        for (int i = 0; i < nums.length; i++) {
            if (max < nums[i]) {
                secondMax = max;
                max = nums[i];
                index = i;
            } else if (secondMax < nums[i]) {
                secondMax = nums[i];
            }
        }

        if (max >= 2 * secondMax) {
            return index;
        }

        return -1;
    }
}
