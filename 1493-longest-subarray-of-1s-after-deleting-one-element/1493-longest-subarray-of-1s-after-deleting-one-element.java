// Problem: Longest Subarray of 1's After Deleting One Element
// Link: https://leetcode.com/problems/longest-subarray-of-1s-after-deleting-one-element/
// Difficulty: Medium

// Approach:
// 1. Initialize left = 0, countZero = 0, and ans = 0.
// 2. Expand the sliding window by moving right from 0 to n - 1.
// 3. Whenever nums[right] == 0, increment countZero.
// 4. If countZero exceeds 1, shrink the window from the left
//    until the window contains at most one zero.
// 5. Whenever removing nums[left] == 0, decrement countZero.
// 6. Update ans with the maximum valid window length: right - left + 1.
// 7. Subtract 1 from ans because exactly one element must be deleted,
//    even when the window contains no zeros.
// 8. Return ans - 1.

// Time Complexity: O(n), because each element is visited at most twice.
// Space Complexity: O(1).


class Solution {
    public int longestSubarray(int[] nums) {
        int ans = 0;
        int countZero = 0;
        int left = 0;

        for (int right = 0; right < nums.length; right++) {
            if (nums[right] == 0) {
                countZero++;
            }

            while (countZero > 1) {
                if (nums[left] == 0) {
                    countZero--;
                }

                left++;
            }

            ans = Math.max(ans, right - left + 1);
        }

        return ans - 1;
    }
}
