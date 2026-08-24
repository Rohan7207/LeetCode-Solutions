// Problem: Stone Game VIII
// Link: https://leetcode.com/problems/stone-game-viii/?envType=daily-question&envId=2026-08-24
// Difficulty: Hard

// Approach:
// Use Prefix Sum + Greedy-style DP with O(1) extra space.
//
// 1. Convert `stones` into a prefix-sum array in-place.
//    After this:
//
//      stones[i] = sum of stones[0..i]
//
// 2. Start from the final prefix sum:
//
//      best = stones[n - 1]
//
//    This represents the score difference when all stones are
//    eventually merged.
//
// 3. Traverse the possible prefix choices from right to left,
//    starting from index n - 2 down to 1.
//
// 4. At each index:
//
//      stones[i] - best
//
//    represents taking this prefix sum while the opponent can
//    achieve `best` from the remaining game.
//
// 5. We can either keep the previously calculated best result
//    or choose the current prefix:
//
//      best = max(best, stones[i] - best)
//
// 6. Return `best`.
//
//    We start from index 1 because the first move must take
//    at least two stones.

// Time Complexity: O(n)
// Space Complexity: O(1)


class Solution {
    public int stoneGameVIII(int[] stones) {
        int n = stones.length;

        for (int i = 1; i < n; i++) {
            stones[i] += stones[i - 1];
        }

        int best = stones[n - 1];
        for (int i = n - 2; i >= 1; i--) {
            best = Math.max(best, stones[i] - best);
        }

        return best;
    }
}
