// Problem: Minimum Operations to Reduce X to Zero
// Link: https://leetcode.com/problems/minimum-operations-to-reduce-x-to-zero/?envType=daily-question&envId=2026-09-20
// Difficulty: Medium

// Approach:
//
// 1. Instead of directly finding the minimum number of elements to
//    remove from the left/right whose sum is `x`, look at what remains.
//
// 2. Let:
//       total = sum of the entire array
//
//    If we remove elements with sum `x`, the remaining subarray has:
//
//       remainingSum = total - x
//
// 3. Therefore, the problem becomes:
//
//       Find the LONGEST contiguous subarray
//       whose sum = total - x.
//
// 4. Why longest?
//    If the remaining subarray has length `L`, then we removed:
//
//       n - L
//
//    elements.
//
//    So maximizing the remaining subarray minimizes the number
//    of operations.
//
// 5. Since the array contains positive numbers, we can use a
//    sliding window.
//
// 6. Expand the window using `right` and add `nums[right]`.
//
// 7. If the current sum becomes greater than `target`, move `left`
//    forward and subtract elements until:
//
//       currSum <= target
//
// 8. Whenever:
//
//       currSum == target
//
//    we found a valid remaining subarray. Update `maxLength`.
//
// 9. Finally:
//
//       answer = n - maxLength

// Time Complexity: O(n)
// Space Complexity: O(1)


class Solution {
    public int minOperations(int[] nums, int x) {
        int n = nums.length;
        int total = 0;

        for (int num : nums) {
            total += num;
        }

        int target = total - x;
        // If target is 0, we need to remove all elements
        if (target == 0) {
            return n;
        }

        // If target is negative, impossible
        if (target < 0) {
            return -1;
        }

        int left = 0;
        int currSum = 0;
        int maxLength = -1;
        for (int right = 0; right < n; right++) {
            currSum += nums[right];

            while (left < n && currSum > target) {
                currSum -= nums[left];
                left++;
            }

            if (currSum == target) {
                maxLength = Math.max(maxLength, right - left + 1);
            }
        }

        if (maxLength == -1) {
            return -1;
        }

        return n - maxLength;
    }
}
