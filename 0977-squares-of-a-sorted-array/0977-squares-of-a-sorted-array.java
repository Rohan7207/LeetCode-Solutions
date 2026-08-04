// Problem: Squares of a Sorted Array
// Link: https://leetcode.com/problems/squares-of-a-sorted-array/
// Difficulty: Easy

// Approach:
// Since the array is already sorted, the largest square can
// only come from either end of the array.
//
// This is because the numbers with the largest absolute values
// are located at the leftmost (most negative) or rightmost
// (largest positive) positions.
//
// Use two pointers:
//
// - left  → beginning of the array.
// - right → end of the array.
//
// Also maintain an index pointing to the last position of the
// answer array since we place the largest squares first.
//
// At every step:
//
// 1. Compute the squares of nums[left] and nums[right].
// 2. Compare the two squared values.
// 3. Place the larger square at ans[idx].
// 4. Move the corresponding pointer.
// 5. Decrement idx.
//
// Continue until the two pointers cross.
//
// Since the largest remaining square is always placed at the
// current last available position, the resulting array is
// automatically sorted in non-decreasing order.

// Time Complexity: O(n)
// Space Complexity: O(n)


class Solution {
    public int[] sortedSquares(int[] nums) {
        int n = nums.length;
        int left = 0;
        int right = n - 1;
        int idx = n - 1;

        int[] ans = new int[n];
        while (left <= right) {
            int v1 = nums[left] * nums[left];
            int v2 = nums[right] * nums[right];

            if (v1 >= v2) {
                ans[idx--] = v1;
                left++;
            } else if (v2 >= v1) {
                ans[idx--] = v2;
                right--;
            }
        }

        return ans;
    }
}
