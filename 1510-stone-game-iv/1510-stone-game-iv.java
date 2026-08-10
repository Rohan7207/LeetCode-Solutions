// Problem: Stone Game IV
// Link: https://leetcode.com/problems/stone-game-iv/?envType=daily-question&envId=2026-08-10
// Difficulty: Hard

// Approach:
// Use Dynamic Programming where dp[i] represents whether the
// current player can win when there are i stones remaining.
//
// 1. Base case:
//    dp[0] = false because a player with 0 stones cannot make
//    any move and therefore loses.
//
// 2. For every number of stones i, try removing every possible
//    perfect square:
//       1, 4, 9, 16, ...
//
// 3. After removing j * j stones, the opponent gets:
//       i - j * j
//
// 4. If dp[i - j * j] is false, the opponent is in a losing
//    position. Therefore, the current player can make that move
//    and win:
//
//       dp[i] = true
//
// 5. If every possible move gives the opponent a winning
//    position, dp[i] remains false.
//
// 6. Finally, return dp[n].

// Time Complexity: O(n√n)
// Space Complexity: O(n)


class Solution {
    public boolean winnerSquareGame(int n) {
        boolean[] dp = new boolean[n + 1];
        dp[0] = false; 

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j * j <= i; j++) {
                if (!dp[i - j * j]) {
                    dp[i] = true;
                    break;
                }
            }
        }

        return dp[n];
    }
}
