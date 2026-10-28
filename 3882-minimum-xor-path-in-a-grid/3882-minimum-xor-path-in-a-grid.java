// Problem: Minimum XOR Path in a Grid
// Link: https://leetcode.com/problems/minimum-xor-path-in-a-grid/?envType=daily-question&envId=2026-10-28
// Difficulty: Medium

// Approach:
// 1. Create a BitSet for every cell to store all XOR values
//    achievable by paths ending at that cell.
// 2. Initialize dp[0][0] with grid[0][0], since the path
//    starts at the top-left cell.
// 3. For each cell (i, j), combine reachable XOR states from
//    the top and left cells.
// 4. For every reachable XOR value, calculate:
//       newXor = xorVal ^ grid[i][j]
//    Mark newXor as reachable in the current cell's BitSet.
// 5. At the bottom-right cell, scan XOR values from smallest
//    to largest and return the first reachable value.

// Time Complexity: O(m * n * K) approximately,
//                   where K is the number of possible XOR values.
// Space Complexity: O(m * n * K) bits for the DP states,
//                    plus BitSet object overhead.

class Solution {
    public int minCost(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        // Initialize a 2D array of BitSet objects
        BitSet[][] dp = new BitSet[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                dp[i][j] = new BitSet(1024); // Pre-allocates space for 1024 bits
            }
        }

        // Base case initialization
        dp[0][0].set(grid[0][0]);

        for (int i = 0; i < m; i++) { 
            for (int j = 0; j < n; j++) { 
                if (i == 0 && j == 0) {
                    continue;
                }

                // Initialize 'combined' as a single BitSet object instead of up and left
                BitSet combined = new BitSet(1024);

                // Read from the top cell if it exists
                if (i > 0) {
                    combined.or(dp[i - 1][j]);
                }

                // Read from the left cell if it exists
                if (j > 0) {
                    combined.or(dp[i][j - 1]);
                }

                // Initialize a new empty BitSet to hold the final state of dp[i][j]
                BitSet nextState = new BitSet(1024);

                for (int xorVal = combined.nextSetBit(0); xorVal >= 0; xorVal = combined.nextSetBit(xorVal + 1)) {
                    int newXorVal = xorVal ^ grid[i][j];
                    nextState.set(newXorVal);
                }

                // Save the transitioned state into the DP table
                dp[i][j] = nextState;
            }
        }

        // Find the smallest valid XOR value in the destination cell
        for (int xorVal = 0; xorVal < 1024; xorVal++) { 
            if (dp[m - 1][n - 1].get(xorVal)) {
                return xorVal;
            }
        }

        return -1; 
    }
}
