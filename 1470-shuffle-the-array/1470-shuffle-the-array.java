// Problem: Shuffle the Array
// Link: https://leetcode.com/problems/shuffle-the-array/
// Difficulty: Easy

// Approach:
// 1. Create an answer array of the same length as nums.
// 2. Initialize i = 0 for the first half and j = n for the second half.
// 3. Use idx to track the next available position in ans.
// 4. While both pointers are within their respective halves:
//      - Copy nums[i] into ans[idx], then increment i and idx.
//      - Copy nums[j] into ans[idx], then increment j and idx.
// 5. Return the rearranged array.

// Time Complexity: O(n)
// Space Complexity: O(n)


class Solution {
    public int[] shuffle(int[] nums, int n) {
        int len = nums.length;
        int[] ans = new int[len];
        int i = 0, j = n;
        int idx = 0;

        while (i < n && j < len) {
            ans[idx++] = nums[i++];
            ans[idx++] = nums[j++];
        }

        return ans;
    }
}
