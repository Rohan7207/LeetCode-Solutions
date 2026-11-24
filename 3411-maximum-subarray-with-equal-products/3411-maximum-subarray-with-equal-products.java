// Problem: Maximum Subarray With Equal Products
// Link: https://leetcode.com/problems/maximum-subarray-with-equal-products/?envType=daily-question&envId=2026-11-24
// Difficulty: Easy

// Approach:
// 1. Generate every possible subarray using two nested loops.
// 2. For each starting index i, initialize:
//      product = 1
//      gcd = 0
//      lcm = 1
// 3. Extend the subarray by moving j from i to n-1.
// 4. Update product incrementally by multiplying nums[j].
// 5. Update GCD using gcd(previousGcd, nums[j]).
// 6. Update LCM using:
//      lcm = (lcm / gcd(lcm, nums[j])) * nums[j]
// 7. If product == gcd * lcm, update the maximum length.
// 8. Return the maximum length.

// Time Complexity: O(n² * log M)
// Space Complexity: O(1)
// M = maximum element in nums


class Solution {
    public int maxLength(int[] nums) {
        int n = nums.length;
        int res = 0;

        for (int i = 0; i < n; i++) {
            long prod = 1;
            long gcd = 0;
            long lcm = 1;

            for (int j = i; j < n; j++) {
                prod *= nums[j];

                gcd = findGcd((int) gcd, nums[j]);

                lcm = (lcm / findGcd((int) lcm, nums[j])) * nums[j];

                if (prod == lcm * gcd) {
                    res = Math.max(res, j - i + 1);
                }
            }
        }

        return res;
    }

    private long findGcd(int a, int b) {
        while (b > 0) {
            int temp = a % b;
            a = b;
            b = temp;
        }

        return a;
    }
}
