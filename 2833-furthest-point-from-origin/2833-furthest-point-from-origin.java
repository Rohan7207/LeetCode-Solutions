// Problem: Furthest Point From Origin
// Link: https://leetcode.com/problems/furthest-point-from-origin/?envType=daily-question&envId=2026-04-24
// Difficulty: Easy

// Approach:
// Count the number of 'L', 'R', and '_' characters.
// The current distance from origin is the absolute difference
// between left and right moves.
// To maximize the distance, use all '_' moves in the same direction.
// Return abs(left - right) + blanks.
//
// Time Complexity: O(n)
// Space Complexity: O(1)

class Solution {
    public int furthestDistanceFromOrigin(String moves) {
        int left = 0, right = 0, blanks = 0;

        for(char c : moves.toCharArray()) {
            if(c == 'L') left++;
            else if(c == 'R') right++;
            else blanks++;
        }

        return Math.abs(left - right) + blanks;
    }
}
