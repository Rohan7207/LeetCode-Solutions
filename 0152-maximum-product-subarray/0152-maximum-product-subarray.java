// Problem: Maximum Product Subarray
// Link: https://leetcode.com/problems/maximum-product-subarray/
// Difficulty: Medium

// Approach:
// Traverse the array while maintaining:
//     - maximum product ending at current index
//     - minimum product ending at current index
//
// Minimum product is also tracked because
// multiplying two negative numbers can produce
// a large positive product.
//
// For every element:
//     - Compute current maximum product using:
//           current number,
//           previous max * current,
//           previous min * current
//     - Similarly compute current minimum product.
//     - Update answer with maximum product found.

// Time Complexity: O(n)
// Space Complexity: O(1)


class Solution {
    public int maxProduct(int[] nums) {
        int n = nums.length;
        
        int max = nums[0];
        int min = nums[0];
        int ans = max;

        for (int i = 1; i < n; i++) {
            int cur = nums[i];
            int temp = Math.max(cur, Math.max(max * cur, min * cur));
            min = Math.min(cur, Math.min(min * cur, max * cur));
            max = temp;

            ans = Math.max(ans, max);
        }

        return ans;
    }
}
