// Problem: Binary Search 
// Link: https://leetcode.com/problems/binary-search/
// Difficulty: Easy

// Approach:
// We need to find the target element
// in a sorted array.
// Use Binary Search.
// Maintain:
//     low  -> start of search space
//     high -> end of search space
// While search space exists:
//     Find middle element.
// If:
//     nums[mid] == target
//     Target found.
//     Return mid.
// Else if:
//     target < nums[mid]
//     Target must be in left half.
//     high = mid - 1
// Else:
//     Target must be in right half.
//     low = mid + 1
// Continue until:
//     low > high
// If target is not found:
//     return -1

// Time Complexity: O(log n)
// Space Complexity: O(1)


class Solution {
    public int search(int[] nums, int target) {
        int low = 0;
        int high = nums.length - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (target == nums[mid]) {
                return mid;
            } else if (target < nums[mid]) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        return -1;
    }
}
