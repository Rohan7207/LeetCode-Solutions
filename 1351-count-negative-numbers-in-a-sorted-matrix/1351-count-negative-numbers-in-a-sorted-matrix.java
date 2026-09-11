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