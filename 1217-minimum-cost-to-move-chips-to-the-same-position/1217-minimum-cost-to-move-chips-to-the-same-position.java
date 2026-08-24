// Problem: Minimum Cost to Move Chips to the Same Position
// Link: https://leetcode.com/problems/minimum-cost-to-move-chips-to-the-same-position/
// Difficulty: Easy

// Approach:
// Use parity (even/odd) counting.
//
// 1. A move of +2 or -2 costs 0.
//    Therefore, a chip always stays in the same parity:
//
//      even → even
//      odd  → odd
//
// 2. A move of +1 or -1 costs 1.
//    Therefore, moving between even and odd positions costs 1.
//
// 3. Count how many chips are at even positions and how many
//    are at odd positions.
//
// 4. If we choose an even position as the final position,
//    all even chips can reach it for free.
//    Only odd chips need to change parity.
//
//      cost = odd
//
// 5. If we choose an odd position as the final position,
//    all odd chips can reach it for free.
//    Only even chips need to change parity.
//
//      cost = even
//
// 6. Choose the smaller cost:
//
//      min(even, odd)

// Time Complexity: O(n)
// Space Complexity: O(1)


class Solution {
    public int minCostToMoveChips(int[] position) {
        int even = 0;
        int odd = 0;

        for (int pos : position) {
            if (pos % 2 == 0) {
                even++;
            } else {
                odd++;
            }
        }

        return Math.min(even, odd);
    }
}
