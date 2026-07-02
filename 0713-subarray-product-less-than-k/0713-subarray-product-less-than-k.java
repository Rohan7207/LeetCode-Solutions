// Problem: Subarray Product Less Than K
// Link: https://leetcode.com/problems/subarray-product-less-than-k/
// Difficulty: Medium

// Approach:
// Since all numbers are positive,
// multiplying by a new element can only increase the product.
// This allows us to use a sliding window.
//
// Step 1:
// Maintain a window:
//     [left ... right]
// and keep the product of all elements
// inside this window.
//
// Step 2:
// Expand the window by moving 'right'.
// Multiply:
//     product *= nums[right]
//
// Step 3:
// If the product becomes greater than or equal to k,
// shrink the window from the left.
// Keep removing elements until:
//     product < k
// Since every number is positive,
// removing elements always decreases the product.
//
// Step 4:
// Once the window becomes valid,
// every subarray ending at 'right'
// and starting anywhere from
// 'left' to 'right'
// is also valid.
// Number of such subarrays:
//     right - left + 1
// Add them to the answer.
//
// Step 5:
// Continue until every element
// has been processed.

// Time Complexity: O(n)
// Space Complexity: O(1)


class Solution {
    public int numSubarrayProductLessThanK(int[] nums, int k) {
        int count = 0;
        int left = 0;

        int product = 1;
        for (int right = 0; right < nums.length; right++) {
            product *= nums[right];

            while (product >= k) {
                product /= nums[left];
                left++;
            }

            count += right - left + 1;
        }

        return count;
    }
}
