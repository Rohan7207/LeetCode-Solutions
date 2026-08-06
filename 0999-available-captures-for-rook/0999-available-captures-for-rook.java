// Problem: Available Captures for Rook
// Link: https://leetcode.com/problems/available-captures-for-rook/
// Difficulty: Easy

// Approach:
// First, locate the rook ('R') on the chessboard.
//
// From the rook's position, explore the four possible
// directions independently:
//
// - Left
// - Right
// - Up
// - Down
//
// During exploration in a direction:
//
// 1. If the position goes outside the board or encounters a
//    bishop ('B'), stop exploring in that direction since the
//    rook cannot move further.
//
// 2. If a pawn ('p') is encountered, increment the capture
//    count and stop exploring in that direction because the
//    rook captures the first pawn it reaches.
//
// 3. Otherwise, continue moving one step further in the same
//    direction.
//
// Since each direction is explored independently, the total
// number of captured pawns is the sum of captures from all
// four directions.

// Time Complexity:
// O(8 + 8 + 8 + 8) = O(1)
// (The board size is fixed at 8 × 8.)
//
// Space Complexity:
// O(1)
// (Ignoring the recursion stack, whose maximum depth is 8.)


class Solution {

    private int sum = 0;

    public int numRookCaptures(char[][] board) {
        // Find Rook's position
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (board[i][j] == 'R') {
                    // Explore all four directions
                    dfs(board, i, j - 1, 'L');
                    dfs(board, i, j + 1, 'R');
                    dfs(board, i - 1, j, 'U');
                    dfs(board, i + 1, j, 'D');
                }
            }

        }
        return sum;
    }

    private void dfs(char[][] board, int i, int j, char ch) {
        // Stop if out of bounds or blocked by a bishop
        if (i < 0 || i >= 8 || j < 0 || j >= 8 || board[i][j] == 'B') {
            return;
        }

        // If a pawn is found, capture it and stop further movement
        if (board[i][j] == 'p') {
            sum++;
            return;
        }

        // Continue in the same direction
        if (ch == 'L')
            dfs(board, i, j - 1, 'L');
        if (ch == 'R')
            dfs(board, i, j + 1, 'R');
        if (ch == 'U')
            dfs(board, i - 1, j, 'U');
        if (ch == 'D')
            dfs(board, i + 1, j, 'D');
    }
}
