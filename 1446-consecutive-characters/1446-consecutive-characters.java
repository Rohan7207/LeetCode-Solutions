// Problem: Consecutive Characters
// Link: https://leetcode.com/problems/consecutive-characters/
// Difficulty: Easy

// Approach:
// 1. Maintain `count` = length of the current consecutive-character streak.
// 2. Start with count = 1 because the string is non-empty.
// 3. Compare s[i] with s[i - 1]:
//      - Same character → count++.
//      - Different character → reset count = 1.
// 4. Update `ans` with the maximum streak after every position.
// 5. Return the maximum streak.

// Time Complexity: O(n)
// Space Complexity: O(1)


class Solution {
    public int maxPower(String s) {
        int count = 1;
        int ans = 1;

        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i - 1) == s.charAt(i)) {
                count++;
            } else {
                count = 1;
            }

            ans = Math.max(ans, count);
        }

        return ans;
    }
}
