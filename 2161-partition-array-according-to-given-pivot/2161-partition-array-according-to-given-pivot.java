// Problem : Partition Array According to Given Pivot
// Link : https://leetcode.com/problems/partition-array-according-to-given-pivot/?envType=daily-question&envId=2026-06-08
// Difficulty : Medium

// Approach:
// Partition the array into three groups:
//     1. Elements smaller than pivot
//     2. Elements equal to pivot
//     3. Elements greater than pivot
// Since relative order must be preserved,
// traverse the array in original order.
// Create an answer array and maintain
// an index pointer for insertion.
// First pass:
//     - Place all elements < pivot
// Second pass:
//     - Place all elements == pivot
// Third pass:
//     - Place all elements > pivot
// Since elements are added in the order
// they appear, the relative ordering
// within each group remains unchanged.
// Finally return the answer array.

// Time Complexity: O(n)
// Space Complexity: O(n)


class Solution {
    public int[] pivotArray(int[] nums, int pivot) {
        int[] ans = new int[nums.length];
        int index = 0;

        for (int num : nums) {
            if (num < pivot) ans[index++] = num;
        }

        for (int num : nums) {
            if (num == pivot) ans[index++] = num;
        }

        for (int num : nums) {
            if (num > pivot) ans[index++] = num;
        }

        return ans;
    }
}
