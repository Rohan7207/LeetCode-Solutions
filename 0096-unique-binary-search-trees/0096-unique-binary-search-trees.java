// Problem: Unique Binary Search Trees
// Link: https://leetcode.com/problems/unique-binary-search-trees/
// Difficulty: Medium

// Approach:
// Use dynamic programming to count the number
// of unique BSTs that can be formed.
// Let dp[i] represent the number of unique BSTs
// that can be formed using i nodes.
// For every number of nodes i:
//     - Try each node as the root.
//     - Nodes on the left form left subtree.
//     - Nodes on the right form right subtree.
//     - Total trees for a root is:
//           leftSubtrees * rightSubtrees
//     - Add all possible combinations to dp[i].
// Return dp[n].

// Time Complexity: O(n^2)
// Space Complexity: O(n)


class Solution {
    public int numTrees(int n) {
        int dp[] = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        
        for (int i = 2; i <= n; i++) {
            for (int j = 0; j < i; j++) {
                dp[i] += dp[j] * dp[i - j - 1];
            }
        }
        return dp[n];
    }
}
