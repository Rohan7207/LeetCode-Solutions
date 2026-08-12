// Problem: Divisor Game
// Link: https://leetcode.com/problems/divisor-game/
// Difficulty: Easy

// Approach:
// Use Dynamic Programming based on winning and losing states.
//
// 1. Define `dp[i]` as whether the player whose turn it is can
//    force a win when the current number is `i`.
//
// 2. Base case:
//       dp[1] = false
//
//    With 1, there is no valid x such that 0 < x < 1, so the
//    current player cannot make a move and loses.
//
// 3. For every number `i`, try every possible divisor `x` where:
//       0 < x < i
//       i % x == 0
//
// 4. Choosing `x` changes the game from:
//       i → i - x
//
//    Now it is the opponent's turn.
//
// 5. If `dp[i - x]` is false, the opponent is in a losing state.
//    Therefore, the current player can choose `x` and win.
//
//       if (!dp[i - x])
//           dp[i] = true;
//
// 6. We only need ONE such winning move, so we break immediately.
//
// 7. If every valid divisor leads to a winning state for the
//    opponent, `dp[i]` remains false.

// Time Complexity: O(n²)
// Space Complexity: O(n)


class Solution {
    public boolean divisorGame(int n) {
        boolean[] dp = new boolean[n + 1];
        dp[1] = false; // With 1, there is no valid divisor x < 1.

        for (int i = 2; i <= n; i++) {
            for (int x = 1; x < i; x++) {
                if (i % x == 0 && !dp[i - x]) {
                    dp[i] = true;
                    break;
                }
            }
        }
        
        return dp[n];
    }
}
