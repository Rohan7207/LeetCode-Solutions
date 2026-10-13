// Problem: Maximum Alternating Sum of Squares
// Link: https://leetcode.com/problems/maximum-alternating-sum-of-squares/?envType=daily-question&envId=2026-10-13
// Difficulty: Medium

// Approach:
// 1. Square every element in the array to obtain its contribution to the sum.
// 2. Sort the squared values in ascending order.
// 3. Use two pointers: left at the smallest value and right at the largest.
// 4. Add the largest value and subtract the smallest value to maximize the alternating sum.
// 5. Move both pointers inward and repeat until all elements are processed.
// 6. If one element remains, add it because it occupies a positive position.
// 7. Return the accumulated sum.

// Time Complexity: O(n log n), due to sorting.
// Space Complexity: O(log n) auxiliary space for sorting (implementation-dependent).


class Solution {
    public long maxAlternatingSum(int[] nums) {
        long ans = 0;
        int n = nums.length;

        for (int i = 0; i < n; i++) {
            nums[i] *= nums[i];
        }

        Arrays.sort(nums);
        int left = 0;
        int right = n - 1;

        while (left <= right) {
            if (left == right) {
                ans += nums[left];
                break;
            }

            ans += nums[right] - nums[left];
            left++;
            right--;
        }
        
        return ans;
    }
}
