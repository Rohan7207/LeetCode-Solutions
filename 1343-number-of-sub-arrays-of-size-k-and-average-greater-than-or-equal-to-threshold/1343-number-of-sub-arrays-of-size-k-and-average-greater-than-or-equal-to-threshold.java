// Problem: Number of Sub-arrays of Size K and Average Greater than or Equal to Threshold
// Link: https://leetcode.com/problems/number-of-sub-arrays-of-size-k-and-average-greater-than-or-equal-to-threshold/
// Difficulty: Medium

// Approach:
// Use Fixed-Size Sliding Window.
//
// 1. Maintain the sum of the current window of size k.
//
// 2. Expand the window by adding arr[right].
//
// 3. If the window becomes larger than k, remove the
//    leftmost element and move left forward.
//
// 4. Once the window size becomes k, calculate its average.
//
// 5. If the average is at least the given threshold,
//    increment the count.
//
// 6. Continue until all possible windows are checked.

// Time Complexity: O(n)
// Space Complexity: O(1)


class Solution {
    public int numOfSubarrays(int[] arr, int k, int threshold) {
        int sum = 0;
        int count = 0;
        int size = 0;

        int left = 0;
        for (int right = 0; right < arr.length; right++) {
            sum += arr[right];
            size++;

            while (size > k) {
                size--;
                sum -= arr[left];
                left++;
            }

            if (size == k) {
                int avg = sum / k;

                if (avg >= threshold) {
                    count++;
                }
            }
        }

        return count;
    }
}
