class Solution {
    public boolean stoneGame(int[] piles) {
        // Maximum score difference the current player can achieve from piles[left...right].
        int n = piles.length;
        int[][] dp = new int[n][n];

        // Fill the base case left == right
        for (int i = 0; i < n; i++) {
            dp[i][i] = piles[i];
        }

        // Decide traversal order, Start with intervals of length 2.
        for (int len = 2; len <= n; len++) {
            // Find left and right
            for (int left = 0; left <= n - len; left++) {
                int right = left + len - 1;

                // Fill recurrence
                int takeLeft = piles[left] - dp[left + 1][right];
                int takeRight = piles[right] - dp[left][right - 1];

                dp[left][right] = Math.max(takeLeft, takeRight);
            }
        }

        return dp[0][n - 1] > 0;
    }
}

// Time Complexity:
//     DP Table : O(n²)
//
// Space Complexity:
//     DP Table : O(n²)

/*
    Interview Observation

Instead of storing Alice's score and Bob's score separately, store only their score difference.

This converts a two-player game into a single DP state, making the transition much simpler while naturally accounting for both players playing optimally.
*/

/*
    The hidden fact about this problem

For Stone Game (877):

The number of piles is even.
The total number of stones is odd (so no ties).
Alice always has a winning strategy, regardless of the values in the piles.

In fact, there is an even simpler accepted solution:

class Solution {
    public boolean stoneGame(int[] piles) {
        return true;
    }
}

This is accepted because mathematically Alice can always force a win.

Why does your code pass?

Your recursion is effectively asking:

"Is there some sequence of moves where Alice ends up with more than half the stones?"

Since Alice always has a winning strategy for every valid test case in this problem, the answer is always true.
*/

/*
    🔑 Key Observation

After the current player picks a pile, the roles swap.

Therefore:

Current player gains the chosen pile immediately.
The remaining game is played optimally by the opponent.
Since dp stores the opponent's future advantage, we subtract it from the current gain.

✨ Magic Lines
int takeLeft = piles[left] - dp[left + 1][right];
int takeRight = piles[right] - dp[left][right - 1];

These lines capture the idea:

My immediate gain − Opponent's best future advantage

Another important line:

dp[left][right] = Math.max(takeLeft, takeRight);

The current player always chooses the move that maximizes their final advantage.

💡 How We Thought to Reach This Solution

At first, it seems natural to track:

Alice's score
Bob's score

However, that complicates the DP because both players influence each other's totals.

Observation:

Track only:

Current Player's Score − Opponent's Score

This single value is enough to determine the winner and leads to a clean interval DP recurrence.

✅ Why It Works
Every state represents the best advantage the current player can guarantee.
Taking a pile gives an immediate gain.
The remaining game is solved optimally by the opponent.
Subtracting the opponent's advantage gives the current player's net advantage.
Filling intervals from smaller to larger ensures all dependent states are already computed.
🧩 Pattern Recognition

Whenever you see:

Two players
Both play optimally
Pick from the left or right end
Need to determine the winner or maximum advantage

Think:

Interval DP
        ↓
dp[left][right]
        ↓
Store Score Difference
        ↓
Current Gain − Opponent's Best Result
⭐ Interview Importance

⭐⭐⭐⭐⭐

Tests:

Interval DP
Game Theory
Minimax reasoning
Dynamic Programming on subarrays
Optimal decision making

📚 Similar Problems
LeetCode 486 – Predict the Winner
LeetCode 1690 – Stone Game VII
LeetCode 1406 – Stone Game III
LeetCode 1140 – Stone Game II
LeetCode 312 – Burst Balloons (Interval DP)

🔄 Common Pattern
Subarray [left...right]
        ↓
Choose Left or Right
        ↓
Opponent Plays Optimally
        ↓
Subtract Opponent's Advantage
        ↓
Take Maximum
        ↓
Build Interval DP
        ↓
Answer = dp[0][n-1] > 0
*/