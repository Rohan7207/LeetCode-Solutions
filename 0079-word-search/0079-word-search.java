// Problem: Word Search
// Link: https://leetcode.com/problems/word-search/
// Difficulty: Medium

// Approach:
// Traverse each cell of the board as a possible
// starting position for the word.
// Use DFS with backtracking to search characters
// in all four directions.
// In DFS:
//     - If all characters are matched, return true.
//     - Check boundary conditions and whether
//       the current cell matches the character.
//     - Mark the current cell as visited
//       to avoid reusing it in the same path.
//     - Recursively search in up, down,
//       left, and right directions.
//     - Backtrack by restoring the original value.
// Return true if the word is found,
// otherwise return false.

// Time Complexity: O(m * n * 4^L) where L = length of word
// Space Complexity: O(L) // recursion stack


class Solution {
    int rows, cols;

    public boolean exist(char[][] board, String word) {
        rows = board.length;
        cols = board[0].length;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (dfs(board, word, i, j, 0)) {
                    return true;
                }
            }
        }

        return false;
    }

    public boolean dfs(char[][] board, String word, int row, int col, int idx) {
        if (idx == word.length()) {
            return true;
        }

        if (row < 0 || row >= board.length || col < 0 || col >= board[0].length
                || board[row][col] != word.charAt(idx)) {
            return false;
        }

        char temp = board[row][col];
        board[row][col] = '#';

        boolean found = dfs(board, word, row + 1, col, idx + 1) ||
                dfs(board, word, row - 1, col, idx + 1) ||
                dfs(board, word, row, col + 1, idx + 1) ||
                dfs(board, word, row, col - 1, idx + 1);

        board[row][col] = temp;
        return found;
    }
}
