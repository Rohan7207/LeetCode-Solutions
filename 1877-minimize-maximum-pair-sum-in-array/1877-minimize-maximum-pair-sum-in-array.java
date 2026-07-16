// Problem: Minimize Maximum Pair Sum in Array
// Link: https://leetcode.com/problems/minimize-maximum-pair-sum-in-array/
// Difficulty: Medium

// Approach:
// Sort the array in non-decreasing order.
// Use two pointers:
//     left  -> smallest remaining element
//     right -> largest remaining element
// Pair the smallest element with the largest element.
// Compute the sum of the current pair and update the
// maximum pair sum seen so far.
// Move both pointers inward and repeat until all
// elements are paired.
// The maximum pair sum obtained from these optimal
// pairings is the minimum possible answer.

// Time Complexity:
//     O(n log n)
//     (Sorting dominates)
// Space Complexity:
//     O(1)
//     (Ignoring the sorting algorithm's internal stack)


class Solution {
    public int minPairSum(int[] nums) {
        Arrays.sort(nums);
        int left = 0;
        int right = nums.length - 1;
        int max = 0;

        while (left < right) {
            max = Math.max(max, nums[left] + nums[right]);
            left++;
            right--;
        }
        
        return max;
    }
}
