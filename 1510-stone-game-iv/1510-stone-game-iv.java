class Solution {
    public boolean winnerSquareGame(int n) {
        boolean[] dp = new boolean[n + 1];
        dp[0] = false;  // With 0 stones, the current player cannot make a move.

        for(int i = 1; i <= n; i++) {
            for(int j = 1; j * j <= i; j++) {
                if(!dp[i - j * j]) {
                    dp[i] = true;
                    break;
                }
            }
        }

        return dp[n];
    }
}

/*
    If I can move to a false, I become true. If every possible move goes to true, I become false.
    If last move is fasle i can win, or else i can't.
*/