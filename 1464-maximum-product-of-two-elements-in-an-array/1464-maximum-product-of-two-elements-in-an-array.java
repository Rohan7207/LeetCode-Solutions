// Problem: Maximum Product of Two Elements in an Array
// Link: https://leetcode.com/problems/maximum-product-of-two-elements-in-an-array/?envType=daily-question&envId=2026-07-27
// Difficulty: Easy

// Approach:
// Traverse the array once while maintaining the two largest numbers.
// Maintain two variables:
// - first  -> largest number seen so far.
// - second -> second largest number seen so far.
// For every element:
// 1. If the current number is greater than the largest number,
//    shift the current largest to second,
//    and update first with the current number.
// 2. Otherwise, if the current number is greater than the second largest,
//    update second.
// After processing all elements,
// first and second contain the two largest values in the array.
// The required answer is:
// (first - 1) × (second - 1)
// Return this product.

// Time Complexity: O(n)
// Space Complexity: O(1)

class Solution {
    public int maxProduct(int[] nums) {
        int first = -1;
        int second = -1;

        for (int num : nums) {
            if (num > first) {
                second = first;
                first = num;
            } else if (num > second) {
                second = num;
            }
        }

        return (first - 1) * (second - 1);
    }
}
