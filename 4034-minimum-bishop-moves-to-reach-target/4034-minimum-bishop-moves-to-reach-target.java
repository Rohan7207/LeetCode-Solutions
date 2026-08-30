// Problem: Minimum Bishop Moves to Reach Target
// Link: https://leetcode.com/problems/minimum-bishop-moves-to-reach-target/
// Difficulty: Medium

// Approach:
// Use Greedy + Parity + Diagonal Observation.
//
// 1. A bishop can only move diagonally, so it always stays on
//    the same color square.
//
// 2. The color of a chessboard square is determined by:
//      (row + col) % 2
//
//    If source and target have different parity, they are on
//    different colors and the bishop can never reach the target.
//
// 3. If both positions are on the same diagonal, then the bishop
//    can reach the target in exactly one move.
//
//    Two cells are on the same diagonal when:
//      |sourceRow - targetRow| == |sourceCol - targetCol|
//
// 4. If they have the same color but are not on the same diagonal,
//    the bishop can always reach the target in exactly two moves.
//
// 5. Therefore, the answer can only be:
//      -1 → different colors, impossible
//       1 → same diagonal
//       2 → same color but different diagonal

// Time Complexity: O(1)
// Space Complexity: O(1)


class Solution {
    public int minBishopMoves(int[] source, int[] target) {
        //If there is differenct colour parity then bishop cannot reach target
        if ((source[0] + source[1]) % 2 != (target[0] + target[1]) % 2) {
            return -1;
        }

        // Both source and target are in same diagonal then in one move we can reach target
        if (Math.abs(source[0] - target[0]) == Math.abs(source[1] - target[1])) {
            return 1;
        }

        return 2;
    }
}
