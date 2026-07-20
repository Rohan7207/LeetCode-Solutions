// Problem: Binary SubArrays With Sum
// Link: https://leetcode.com/problems/binary-subarrays-with-sum/
// Difficulty: Medium

// Approach:
// Instead of counting subarrays whose sum is exactly equal to 'goal'
// directly, first count:
// • Number of subarrays with sum <= goal.
// • Number of subarrays with sum <= (goal - 1).
// Their difference gives the number of subarrays whose sum is exactly
// equal to 'goal'.
//      Exactly(goal) = AtMost(goal) - AtMost(goal - 1)
// Helper Function (countAtMost):
// Use a sliding window because the array contains only 0s and 1s.
// Maintain:
// • left  -> starting index of the window.
// • right -> ending index of the window.
// • sum   -> sum of the current window.
// Expand the window by moving 'right' and adding nums[right] to sum.
// If the window sum becomes greater than 'goal',
// shrink the window by moving 'left' until the sum becomes valid.
// Once the window satisfies:
//      sum <= goal
// every subarray ending at 'right' and starting anywhere from
// 'left' to 'right' is also valid.
// Therefore, the number of valid subarrays ending at 'right' is:
//      right - left + 1
// Add this value to the answer.
// Finally, return:
//      countAtMost(goal) - countAtMost(goal - 1)

// Time Complexity: O(n)
// Space Complexity: O(1)


class Solution {
    public int numSubarraysWithSum(int[] nums, int goal) {
        return countAtMost(nums, goal) - countAtMost(nums, goal - 1);
    }

    private int countAtMost(int[] nums, int goal) {
        if (goal < 0) {
            return 0;
        }

        int left = 0;
        int sum = 0;
        int count = 0;

        for (int right = 0; right < nums.length; right++) {
            sum += nums[right];

            while (sum > goal) {
                sum -= nums[left];
                left++;
            }

            count += right - left + 1;
        }

        return count;
    }
}
