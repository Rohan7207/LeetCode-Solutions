// Problem: Length of Longest Subarray With at Most K Frequency
// Link: https://leetcode.com/problems/length-of-longest-subarray-with-at-most-k-frequency/?envType=daily-question&envId=2026-08-12
// Difficulty: Medium

// Approach:
// Use the Sliding Window technique with a frequency map.
//
// 1. Maintain a window from `left` to `right`.
//    The window must satisfy the condition that every number
//    appears at most `k` times.
//
// 2. Expand the window by moving `right` and increase the
//    frequency of nums[right].
//
// 3. If the frequency of nums[right] becomes greater than `k`,
//    the current window becomes invalid.
//
// 4. Shrink the window from the left until the frequency becomes
//    valid again.
//
// 5. After the window becomes valid, calculate its length and
//    update the maximum answer.
//
// 6. Continue until `right` reaches the end of the array.

// Time Complexity: O(n)
// Space Complexity: O(n)


class Solution {
    public int maxSubarrayLength(int[] nums, int k) {
        Map<Integer, Integer> freqMap = new HashMap<>();
        int ans = 0;
        int left = 0;

        for (int right = 0; right < nums.length; right++) {
            freqMap.put(nums[right], freqMap.getOrDefault(nums[right], 0) + 1);

            while (freqMap.get(nums[right]) > k) {
                freqMap.put(nums[left], freqMap.get(nums[left]) - 1);
                left++;
            }

            ans = Math.max(ans, right - left + 1);
        }
        
        return ans;
    }
}
