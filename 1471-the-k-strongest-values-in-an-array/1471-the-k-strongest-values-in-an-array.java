// Problem: The k Strongest Values in an Array
// Link: https://leetcode.com/problems/the-k-strongest-values-in-an-array/
// Difficulty: Medium

// Approach:
// 1. Sort the array so the smallest value is at left and largest at right.
// 2. Find the median m = arr[(n - 1) / 2].
// 3. Initialize two pointers: left = 0 and right = n - 1.
// 4. Compare the absolute distances of arr[left] and arr[right] from m.
// 5. If dist1 <= dist2, choose arr[right] because the larger value wins ties.
// 6. Otherwise, choose arr[left].
// 7. Move the pointer corresponding to the chosen value.
// 8. Repeat until k strongest values are collected.

// Time Complexity: O(n log n + k)
// Space Complexity: O(k) for the answer array, excluding sorting overhead.


class Solution {
    public int[] getStrongest(int[] arr, int k) {
        Arrays.sort(arr);
        int n = arr.length;
        int m = arr[(n - 1) / 2];

        int[] ans = new int[k];
        int idx = 0;
        int left = 0, right = n - 1;

        while (left <= right && idx < k) {
            int dist1 = Math.abs(arr[left] - m);
            int dist2 = Math.abs(arr[right] - m);

            if (dist1 <= dist2) {
                ans[idx++] = arr[right--];
            } else {
                ans[idx++] = arr[left++];
            }
        }

        return ans;
    }
}
