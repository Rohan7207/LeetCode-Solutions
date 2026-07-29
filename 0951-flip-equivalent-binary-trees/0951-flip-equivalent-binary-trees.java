// Problem: Flip Equivalent Binary Trees
// Link: https://leetcode.com/problems/flip-equivalent-binary-trees/
// Difficulty: Medium

// Approach:
// Two trees are flip equivalent if they can become identical after
// swapping the left and right children of any number of nodes.
// Traverse both trees simultaneously using recursion.
// At every pair of nodes:
// 1. If both nodes are null, they are equivalent.
// 2. If one node is null or their values differ, they cannot be equivalent.
// 3. Otherwise, there are only two possible ways for their subtrees to match:
//    a) No Flip:
//       Compare left with left and right with right.
//    b) Flip:
//       Compare left with right and right with left.
// If either comparison succeeds, the current pair of subtrees is
// flip equivalent.
// The recursion checks every node pair until the entire trees are
// successfully matched or a mismatch is found.

// Time Complexity:
// O(min(n, m)) in the best case (early mismatch),
// O(n) in the average case,
// O(2^h) in the worst case because both flip and no-flip
// possibilities may be explored at many levels.
//
// Space Complexity:
// O(h)
// where h is the height of the tree due to recursion stack.


/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public boolean flipEquiv(TreeNode root1, TreeNode root2) {
        return dfs(root1, root2);
    }

    private boolean dfs(TreeNode root1, TreeNode root2) {
        if (root1 == null && root2 == null) {
            return true;
        }

        if (root1 == null || root2 == null) {
            return false;
        }

        if (root1.val != root2.val) {
            return false;
        }

        boolean noFlip = dfs(root1.left, root2.left) &&
                dfs(root1.right, root2.right);

        boolean flip = dfs(root1.left, root2.right) &&
                dfs(root1.right, root2.left);

        return noFlip || flip;
    }
}
