// Problem: Longest Continuous Subarray With Absolute Diff Less Than or Equal to Limit
// Link: https://leetcode.com/problems/longest-continuous-subarray-with-absolute-diff-less-than-or-equal-to-limit/
// Difficulty: Medium

// Approach:
// 1. Use a sliding window [left...right] to maintain the current subarray.
//
// 2. We need the maximum and minimum value of the current window.
//    Recalculating them every time left moves would make the solution O(n²).
//
// 3. Use two monotonic deques:
//    - maxDeque → decreasing order, so the front is the maximum.
//    - minDeque → increasing order, so the front is the minimum.
//
// 4. When adding nums[right]:
//    - Remove smaller values from the back of maxDeque.
//    - Remove larger values from the back of minDeque.
//    - Add nums[right] to both deques.
//
// 5. If maxDeque.front - minDeque.front > limit,
//    the current window is invalid.
//
// 6. Move left forward until the window becomes valid again.
//    If nums[left] is at the front of either deque, remove it.
//
// 7. The current valid window length is:
//    right - left + 1
//
// 8. Keep the maximum window length found.

// Time Complexity: O(n)
// Space Complexity: O(n)


class Solution {
    public int longestSubarray(int[] nums, int limit) {
        int n = nums.length;
        int ans = 0;

        Deque<Integer> maxDeque = new ArrayDeque<>();
        Deque<Integer> minDeque = new ArrayDeque<>();

        int left = 0;
        for (int right = 0; right < n; right++) {
            while (!maxDeque.isEmpty() && maxDeque.peekLast() < nums[right]) {
                maxDeque.pollLast();
            }

            maxDeque.offerLast(nums[right]);

            while (!minDeque.isEmpty() && minDeque.peekLast() > nums[right]) {
                minDeque.pollLast();
            }

            minDeque.offerLast(nums[right]);

            while (maxDeque.peekFirst() - minDeque.peekFirst() > limit) {
                if (maxDeque.peekFirst() == nums[left]) {
                    maxDeque.pollFirst();
                }

                if (minDeque.peekFirst() == nums[left]) {
                    minDeque.pollFirst();
                }

                left++;
            }

            ans = Math.max(ans, right - left + 1);
        }

        return ans;
    }
}
