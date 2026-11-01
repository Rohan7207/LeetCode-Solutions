// Problem: Minimum Moves To Equal Array Elements
// Link: https://leetcode.com/problems/minimum-moves-to-equal-array-elements/?envType=daily-question&envId=2026-11-01
// Difficulty: Medium

// Approach:
// 1. Traverse the array once to calculate:
//    - sum = sum of all elements.
//    - min = minimum element in the array.
//
// 2. Understand the operation:
//    - Incrementing n - 1 elements by 1 is equivalent to
//      decrementing the remaining one element by 1.
//
// 3. To make all elements equal with minimum moves,
//    reduce every element to the minimum value.
//
// 4. Each element needs (num - min) decrements.
//
// 5. Total moves = sum of all elements - (n * min).

// Time Complexity: O(n)
// Space Complexity: O(1)


class Solution {
    public int minMoves(int[] nums) {
        int n = nums.length;
        int min = Integer.MAX_VALUE;
        int sum = 0;

        for (int num : nums) {
            sum += num;
            min = Math.min(min, num);
        }

        return sum - (n * min);
    }
}
