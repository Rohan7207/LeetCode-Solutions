// Problem: Minimum Common Value
// Link: https://leetcode.com/problems/minimum-common-value/?envType=daily-question&envId=2026-05-19
// Difficulty: Easy

// Approach:
// Since both arrays are sorted,
// use two pointers to traverse them.
// Compare current elements of both arrays:
//     - If nums1[i] < nums2[j],
//       move pointer i forward because
//       smaller element cannot match later.
//     - If nums2[j] < nums1[i],
//       move pointer j forward.
//     - If both elements are equal,
//       return that value immediately
//       because it is the smallest common value.
// If traversal finishes without match,
// return -1.

// Time Complexity: O(n + m)
// Space Complexity: O(1)


class Solution {
    public int getCommon(int[] nums1, int[] nums2) {
        int i = 0, j = 0;

        while (i < nums1.length && j < nums2.length) {
            if(nums1[i] < nums2[j]) {
                i++;
            } else if(nums2[j] < nums1[i]) {
                j++;
            } else {
                return nums1[i];
            }
        }

        return -1;
    }
}
