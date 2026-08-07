// Problem: Maximize Sum Of Array After K Negations
// Link: https://leetcode.com/problems/maximize-sum-of-array-after-k-negations/
// Difficulty: Easy

// Approach:
// To maximize the final sum, every flip should provide the
// maximum possible increase.
//
// Step 1:
// Sort the array in ascending order so that all negative
// numbers appear first.
//
// Step 2:
// Traverse the array from left to right.
//
// While k > 0 and the current element is negative,
// flip it to positive.
//
// Flipping a negative number always increases the total sum,
// and flipping the most negative numbers first gives the
// greatest increase.
//
// Step 3:
// After all beneficial flips are done, compute:
//
// - The total sum of the array.
// - The smallest absolute value in the array.
//
// Step 4:
// If there are flips remaining:
//
// • If k is even:
//   Flip the same element twice.
//   The array remains unchanged.
//
// • If k is odd:
//   One flip is unavoidable.
//   Flip the element with the smallest absolute value,
//   since it causes the minimum decrease in the total sum.
//
// Return the final maximum sum.

// Time Complexity:
// O(n log n)   // Sorting dominates
//
// Space Complexity:
// O(1)


class Solution {
    public int largestSumAfterKNegations(int[] nums, int k) {
        Arrays.sort(nums);

        for (int i = 0; i < nums.length; i++) {
            while (k > 0 && nums[i] < 0) {
                nums[i] *= -1;
                k--;
            }
        }

        int sum = 0;
        int min = Integer.MAX_VALUE;

        for (int num : nums) {
            sum += num;
            min = Math.min(min, Math.abs(num));
        }

        if (k % 2 != 0) {
            sum -= 2 * min;
        }
        
        return sum;
    }
}
