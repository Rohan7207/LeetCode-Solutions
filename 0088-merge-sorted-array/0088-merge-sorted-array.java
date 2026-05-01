// Problem: Merge Sorted Array
// Link: https://leetcode.com/problems/merge-sorted-array/
// Difficulty: Easy

// Approach:
// Use three pointers starting from the end
// of nums1, nums2, and merged position.
// Compare elements from both arrays:
//     - Place the larger element at the end
//       of nums1 and move pointers accordingly.
// Continue until one array is exhausted.
// If elements remain in nums2,
// copy them into nums1.
// No need to copy remaining nums1 elements
// because they are already in correct position.

// Time Complexity: O(m + n)
// Space Complexity: O(1)


class Solution {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        if (n == 0) return;

        int i = m - 1, j = n - 1, k = m + n - 1;

        while (i >= 0 && j >= 0) {
            if (nums1[i] > nums2[j]) {
                nums1[k] = nums1[i];
                i--;
            } else {
                nums1[k] = nums2[j];
                j--;
            }
            k--;
        }

        while (j >= 0) {
            nums1[k] = nums2[j];
            j--;
            k--;
        }
    }
}
