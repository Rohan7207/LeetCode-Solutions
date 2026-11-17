// Problem: Minimum Changes to Make K Semi-palindromes
// Link: https://leetcode.com/problems/minimum-changes-to-make-k-semi-palindromes/?envType=daily-question&envId=2026-11-17
// Difficulty: Hard

// Approach:
//
// 1. Divide the string into exactly k non-empty substrings.
//    Each substring must have a length of at least 2.
//
// 2. Use solve(start, k) to find the minimum cost of partitioning
//    the remaining string starting at `start` into exactly k substrings.
//
// 3. Try every possible ending index `end` for the current substring.
//
// 4. Calculate the cost of making str[start...end] a valid
//    special substring using getCost(start, end).
//
// 5. Recursively solve the remaining string:
//       solve(end + 1, k - 1)
//
// 6. Add the current substring's cost to the remaining cost
//    and take the minimum among all possible partitions.
//
// 7. Memoize solve(start, k) to avoid recalculating states.
//
// 8. In getCost(i, j), try every divisor `d` of the substring length.
//    Each divisor represents a possible period of the substring.
//
// 9. For each divisor, compare characters at corresponding positions
//    in each repeating group. Count mismatches that must be changed
//    to make the substring satisfy the required periodic-palindrome
//    condition.
//
// 10. Take the minimum cost among all valid divisors and memoize it.

// Time Complexity: O(n^4 + n^3 × k) approximately
// Space Complexity: O(n^2 + n × k)


class Solution {

    private int n;
    private String str;
    private int[][] costMemo;
    private int[][] memo;

    public int minimumChanges(String s, int k) {
        n = s.length();
        str = s;

        costMemo = new int[n + 1][n + 1];
        for (int[] row : costMemo) {
            Arrays.fill(row, -1);
        }

        memo = new int[n + 1][k + 1];
        for (int[] row : memo) {
            Arrays.fill(row, -1);
        }

        return solve(0, k);
    }

    private int solve(int start, int k) {
        if (k == 0) {
            if (start == n) {
                return 0;
            }

            return Integer.MAX_VALUE;
        }

        // Early return
        if (start + 2 * k > n) {
            return Integer.MAX_VALUE;
        }

        if (memo[start][k] != -1) {
            return memo[start][k];
        }

        int res = Integer.MAX_VALUE;
        for (int end = start + 1; end < n; end++) {
            // start...end -> 1 substring
            int remainingCost = solve(end + 1, k - 1);

            if (remainingCost == Integer.MAX_VALUE) {
                continue; // Invalid partition
            }

            res = Math.min(res, getCost(start, end) + remainingCost);
        }

        return memo[start][k] = res;
    }

    private int getCost(int i, int j) {
        int len = j - i + 1;

        if (costMemo[i][j] != -1) {
            return costMemo[i][j];
        }

        int minCost = Integer.MAX_VALUE;

        for (int d = 1; d < len; d++) {
            // Not a divisor
            if (len % d != 0) {
                continue;
            }

            int cost = 0;
            for (int g = 0; g < d; g++) {
                int l = i + g;
                int r = l + (len / d - 1) * d;

                while (l < r) {
                    if (str.charAt(l) != str.charAt(r)) {
                        cost++;
                    }

                    l += d;
                    r -= d;
                }
            }

            minCost = Math.min(minCost, cost);
        }

        return costMemo[i][j] = minCost;
    }
}
