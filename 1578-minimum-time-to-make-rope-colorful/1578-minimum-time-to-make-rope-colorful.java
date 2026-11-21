// Problem: Minimum Time to Make Rope Colorful
// Link: https://leetcode.com/problems/minimum-time-to-make-rope-colorful/
// Difficulty: Medium

// Approach:
// 1. Traverse the balloons from left to right.
// 2. If two adjacent balloons have different colors, no removal is needed.
// 3. If they have the same color:
//    a. We must remove one of them.
//    b. Remove the balloon with the smaller neededTime.
//    c. Add the smaller time to res.
// 4. Keep the balloon with the larger neededTime by updating neededTime[i].
// 5. Continue comparing the current balloon with the next balloon.
// 6. Return the total removal time.

// Time Complexity: O(n)
// Space Complexity: O(1)


class Solution {
    public int minCost(String colors, int[] neededTime) {
        int res = 0;
        int n = colors.length();

        for (int i = 1; i < n; i++) {
            if (colors.charAt(i) == colors.charAt(i - 1)) {
                res += Math.min(neededTime[i], neededTime[i - 1]);

                neededTime[i] = Math.max(neededTime[i], neededTime[i - 1]);
            }
        }

        return res;
    }
}
