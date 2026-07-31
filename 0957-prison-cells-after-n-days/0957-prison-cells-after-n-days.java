// Problem: Prison Cells After N Days
// Link: https://leetcode.com/problems/prison-cells-after-n-days/
// Difficulty: Medium

// Approach:
// The prison states eventually repeat in a fixed cycle.
//
// After the first day, the first and last cells always become 0,
// so only the middle 6 cells can change.
//
// It can be proven that these states repeat every 14 days.
// Therefore, instead of simulating all n days, reduce n using:
//
//      n = (n - 1) % 14 + 1
//
// This keeps only the effective number of days that actually need
// to be simulated.
//
// Then simulate the prison changes day by day.
//
// For every day:
//
// 1. Create a new array `next`.
// 2. The first and last cells remain 0.
// 3. For every middle cell (1 to 6):
//      - If both neighbors are equal, the cell becomes occupied (1).
//      - Otherwise, it becomes vacant (0).
// 4. Replace the current state with the newly computed state.
//
// After simulating the reduced number of days, return the final state.

// Time Complexity:
// O(14 × 8) = O(1)
//
// Space Complexity:
// O(8) = O(1)


class Solution {
    public int[] prisonAfterNDays(int[] cells, int n) {
        n = (n - 1) % 14 + 1;

        while (n > 0) {
            int[] next = new int[8];

            for (int i = 1; i < 7; i++) {
                if (cells[i - 1] == cells[i + 1]) {
                    next[i] = 1;
                } else {
                    next[i] = 0;
                }
            }

            cells = next;
            n--;
        }

        return cells;
    }
}
