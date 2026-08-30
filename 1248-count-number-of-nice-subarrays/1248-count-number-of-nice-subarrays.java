// Problem: Count Number of Nice Subarrays
// Link: https://leetcode.com/problems/count-number-of-nice-subarrays/
// Difficulty: Medium

// Approach:
// Use Sliding Window + At Most K Transformation.
//
// 1. We need to count subarrays containing exactly k odd numbers.
//
// 2. Instead of directly counting exactly k, calculate:
//
//      exactly(k) = atMost(k) - atMost(k - 1)
//
// 3. Use a sliding window to count subarrays containing at most k
//    odd numbers.
//
// 4. Maintain:
//      oddCount = number of odd elements inside the current window.
//
// 5. Expand the window by moving `right`.
//
// 6. If oddCount becomes greater than k, move `left` forward until
//    the window again contains at most k odd numbers.
//
// 7. Once the window [left ... right] is valid, every subarray
//    ending at `right` and starting anywhere from `left` to `right`
//    is valid.
//
//    Number of such subarrays:
//
//      right - left + 1
//
// 8. Therefore, add this value to `ans`.
//
// 9. Finally:
//
//      countExactlyK = countAtMost(K) - countAtMost(K - 1)

// Time Complexity: O(n)
// Space Complexity: O(1)


class Solution {
    public int numberOfSubarrays(int[] nums, int k) {
        return countAtMost(nums, k) - countAtMost(nums, k - 1);
    }

    private int countAtMost(int[] nums, int k) {
        int n = nums.length;
        int ans = 0;
        int oddCount = 0;

        int left = 0;
        for (int right = 0; right < n; right++) {
            if (nums[right] % 2 == 1)
                oddCount++;

            while (oddCount > k) {
                if (nums[left] % 2 == 1)
                    oddCount--;

                left++;
            }

            ans += right - left + 1;
        }

        return ans;
    }
}
