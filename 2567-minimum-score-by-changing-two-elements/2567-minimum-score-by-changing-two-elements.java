// Problem: Minimum Score by Changing Two Elements
// Link: https://leetcode.com/problems/minimum-score-by-changing-two-elements/?envType=daily-question&envId=2026-11-19
// Difficulty: Medium

// Approach:
// 1. Sort the array in ascending order.
//
// 2. Observe that we can make the low score 0
//    by changing two elements to become equal.
//
// 3. Therefore, we only need to minimize the high score,
//    which is the difference between the maximum and minimum.
//
// 4. Consider the three possible ways to eliminate extremes:
//
//    a. Change the two smallest elements:
//       nums[n - 1] - nums[2]
//
//    b. Change one smallest and one largest element:
//       nums[n - 2] - nums[1]
//
//    c. Change the two largest elements:
//       nums[n - 3] - nums[0]
//
// 5. Return the minimum of these three differences.

// Time Complexity: O(n log n)
// Space Complexity: O(1) auxiliary space (excluding sorting).


class Solution {
    public int minimizeSum(int[] nums) {
        int n = nums.length;

        if (n == 3) {
            return 0;
        }

        Arrays.sort(nums);

        int a = nums[n - 2] - nums[1];
        int b = nums[n - 1] - nums[2];
        int c = nums[n - 3] - nums[0];

        return Math.min(a, Math.min(b, c));
    }
}
