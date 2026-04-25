// Problem: Valid Sudoku
// Link: https://leetcode.com/problems/valid-sudoku/
// Difficulty: Medium

// Approach:
// Use hash sets to track values in each row, column, and 3x3 box.
// Traverse each cell in the board:
//     - Skip the cell if it contains '.'.
//     - Check whether the digit already exists in the corresponding
//       row, column, or box.
//     - If it exists, return false.
//     - Otherwise, add the digit to the respective hash sets.
// If no duplicates are found, return true.

// Time Complexity: O(1)
// Space Complexity: O(1)

class Solution {
    public boolean isValidSudoku(char[][] board) {
        int N = 9;

        //Use hashSet to record status
        HashSet<Character>[] rows = new HashSet[N];
        HashSet<Character>[] cols = new HashSet[N];
        HashSet<Character>[] boxes = new HashSet[N];

        for (int r = 0; r < N; r++) {
            rows[r] = new HashSet<Character>();
            cols[r] = new HashSet<Character>();
            boxes[r] = new HashSet<Character>();
        }

        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                char val = board[r][c];

                //Check if position is filled with number
                if (val == '.') {
                    continue;
                }

                //Check row set contains element
                if (rows[r].contains(val)) {
                    return false;
                }

                //If not present add value to set
                rows[r].add(val);

                //Check col set contains value
                if (cols[c].contains(val)) {
                    return false;
                }

                cols[c].add(val);

                //Check boxes contains element
                int idx = (r / 3) * 3 + (c / 3);
                if (boxes[idx].contains(val)) {
                    return false;
                }
                
                boxes[idx].add(val);
            }
        }

        return true;
    }
}
