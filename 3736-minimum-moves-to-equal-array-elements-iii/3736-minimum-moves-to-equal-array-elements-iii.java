// Problem: Minimum Moves To Equal Array Elements III
// Link: https://leetcode.com/problems/minimum-moves-to-equal-array-elements-iii/?envType=daily-question&envId=2026-11-03
// Difficulty: Easy

// Approach:
// 1. Traverse the array once to find:
//    - max = maximum element.
//    - sum = sum of all elements.
//
// 2. Since we can only increase elements, the final equal value
//    must be at least the maximum element.
//
// 3. To minimize moves, choose the smallest possible final value,
//    which is the maximum element itself.
//
// 4. Calculate the moves required for each element:
//    - moves for an element = max - num.
//
// 5. Sum all required moves:
//    - total moves = n * max - sum.

// Time Complexity: O(n)
// Space Complexity: O(1)


class Solution {
    public int minMoves(int[] nums) {
        int max = Integer.MIN_VALUE;
        int sum = 0;

        for (int num : nums) {
            max = Math.max(max, num);
            sum += num;
        }

        return nums.length * max - sum;
    }
}
