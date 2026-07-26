// Problem: Maximum Product of Three Numbers
// Link: https://leetcode.com/problems/maximum-product-of-three-numbers/?envType=daily-question&envId=2026-07-26
// Difficulty: Easy

// Approach:
// The maximum product of three numbers can come from only two possibilities:
// 1. The three largest numbers.
// 2. The two smallest (most negative) numbers and the largest number.
// This is because multiplying two negative numbers produces a positive
// number, which may result in a larger product than using the second and
// third largest positive numbers.
// 
// Traverse the array once while maintaining:
// - max1 -> largest number
// - max2 -> second largest number
// - max3 -> third largest number
//
// - min1 -> smallest number
// - min2 -> second smallest number
//
// For every element:
// 1. Update the three largest numbers by shifting existing values whenever
//    a larger number is encountered.
//
// 2. Update the two smallest numbers similarly whenever a smaller number
//    is encountered.
//
// After processing the entire array, compute:
//
// - Product of the three largest numbers.
// - Product of the two smallest numbers and the largest number.
//
// Return the larger of these two products.

// Time Complexity: O(n)
// Space Complexity: O(1)


class Solution {
    public int maximumProduct(int[] nums) {
        int max1 = Integer.MIN_VALUE;
        int max2 = Integer.MIN_VALUE;
        int max3 = Integer.MIN_VALUE;

        int min1 = Integer.MAX_VALUE;
        int min2 = Integer.MAX_VALUE;

        for (int num : nums) {
            if (num >= max1) {
                max3 = max2;
                max2 = max1;
                max1 = num;
            } else if (num >= max2) {
                max3 = max2;
                max2 = num;
            } else if (num >= max3) {
                max3 = num;
            }

            if (num <= min1) {
                min2 = min1;
                min1 = num;
            } else if (num <= min2) {
                min2 = num;
            }
        }

        return Math.max(max1 * max2 * max3, min1 * min2 * max1);
    }
}
