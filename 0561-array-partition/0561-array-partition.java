// Problem: Array Partition
// Link: https://leetcode.com/problems/array-partition/
// Difficulty: Easy

// Approach:
// Sort the array in non-decreasing order.
// After sorting, pair every two consecutive elements:
//     (nums[0], nums[1])
//     (nums[2], nums[3])
//     (nums[4], nums[5])
//     ...
// In each pair, the contribution to the answer is the
// smaller element.
// Since the array is sorted, the first element of every
// pair is the minimum.
// Therefore, iterate through the array with a step of 2
// and add every alternate element to the answer.

// Time Complexity:
//     O(n log n)
//     (Sorting dominates)
// Space Complexity:
//     O(1)
//     (Ignoring the sorting algorithm's internal stack)


class Solution {
    public int arrayPairSum(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        int sum = 0;

        for(int i = 0; i < n; i += 2) {
            sum += nums[i];
        }

        return sum;
    }
}
