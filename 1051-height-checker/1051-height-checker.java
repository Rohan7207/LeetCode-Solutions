// Problem : Height Checker
// Link : https://leetcode.com/problems/height-checker/
// Difficulty : Easy

// Approach:
// Create a copy of the original heights array because we need to
// compare the original order with the expected sorted order.
//
// 1. Store the original array in `temp`.
// 2. Sort `heights` to get the expected order.
// 3. Compare the original value at each index with the sorted value.
// 4. If they are different, that student is not in the expected position,
//    so increment the count.
// 5. Return the total count of mismatched positions.

// Time Complexity: O(n log n)
// Space Complexity: O(n)


class Solution {
    public int heightChecker(int[] heights) {
        int[] temp = heights.clone();
        Arrays.sort(heights);
        int count = 0;

        for (int i = 0; i < temp.length; i++) {
            if (temp[i] != heights[i]) {
                count++;
            }
        }

        return count;
    }
}
