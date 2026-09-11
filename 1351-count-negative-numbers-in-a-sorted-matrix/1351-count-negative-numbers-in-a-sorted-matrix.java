// Problem: Count Negative Numbers in a Sorted Matrix
// Link: https://leetcode.com/problems/count-negative-numbers-in-a-sorted-matrix/
// Difficulty: Easy

// Approach:
// Use binary search to find the first negative number in each sorted row.
//
// 1. Since every row is sorted in non-increasing order, all negative
//    numbers form a suffix of the row.
//
// 2. For each row, use binary search to find the first index where
//    the value becomes negative.
//
// 3. If the first negative index is idx, then every element from idx
//    to the end is negative.
//
// 4. Therefore, the number of negative elements in that row is
//    n - idx.
//
// 5. Initialize firstNegativeIndex to arr.length so that a row with
//    no negative values contributes 0.

// Time Complexity: O(m log n)
// Space Complexity: O(1)
// where m = number of rows and n = number of columns.
    

class Solution {
    public int countNegatives(int[][] grid) {
        int n = grid[0].length;
        int ans = 0;

        for (int[] row : grid) {
            int idx = helper(row, 0, n - 1);

            ans += n - idx;
        }

        return ans;
    }

    private int helper(int[] arr, int low, int high) {
        int firstNegativeIndex = arr.length;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (arr[mid] < 0) {
                firstNegativeIndex = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        return firstNegativeIndex;
    }
}
