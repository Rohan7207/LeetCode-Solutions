// Problem: Find Greatest Common Divisor of Array
// Link: https://leetcode.com/problems/find-greatest-common-divisor-of-array/?envType=daily-question&envId=2026-07-18
// Difficulty: Easy

// Approach:
// The GCD of the smallest and largest elements is required.
// Traverse the array once to find:
//     • Minimum element
//     • Maximum element
// After obtaining both values, use the Euclidean Algorithm
// to compute their Greatest Common Divisor (GCD).
// The Euclidean Algorithm repeatedly replaces:
//     (a, b)
// with
//     (b, a % b)
// until b becomes 0.
// At that point, a is the required GCD.

// Time Complexity:
//     O(n + log(max))
//     O(n)         -> Finding minimum and maximum
//     O(log(max))  -> Euclidean GCD
// Space Complexity:
//     O(1)


class Solution {
    public int findGCD(int[] nums) {
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] < min) {
                min = nums[i];
            }

            if (nums[i] > max) {
                max = nums[i];
            }
        }

        return gcd(min, max);
    }

    private int gcd(int a, int b) {
        while (b > 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }

        return a;
    }
}
