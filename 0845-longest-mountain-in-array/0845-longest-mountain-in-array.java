// Problem : Longest Mountain in Array
// Link : https://leetcode.com/problems/longest-mountain-in-array/
// Difficulty : Medium

// Approach:
// A mountain always has a peak such that:
//      arr[i-1] < arr[i] > arr[i+1]
// Traverse the array and treat every valid peak as the center.
// For every peak:
//     1. Expand towards the left while the sequence is
//        strictly increasing.
//     2. Expand towards the right while the sequence is
//        strictly decreasing.
//     3. The elements between the left and right boundaries
//        form one complete mountain.
//     4. Compute its length and update the maximum answer.
// If no peak exists, no mountain exists, so return 0.

// Time Complexity: O(n²) in the worst case
// Space Complexity: O(1)


class Solution {
    public int longestMountain(int[] arr) {
        int n = arr.length;
        int ans = 0;

        for (int i = 1; i < n - 1; i++) {
            // Check if i is a peak
            if (arr[i] > arr[i - 1] && arr[i] > arr[i + 1]) {
                int left = i;
                // Expand to left
                while (left > 0 && arr[left] > arr[left - 1]) {
                    left--;
                }

                int right = i;
                // Expand right
                while (right < n - 1 && arr[right] > arr[right + 1]) {
                    right++;
                }

                ans = Math.max(ans, right - left + 1);
            }
        }

        return ans;
    }
}
