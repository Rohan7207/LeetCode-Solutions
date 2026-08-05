// Problem: Smallest String Starting From Leaf
// Link: https://leetcode.com/problems/smallest-string-starting-from-leaf/
// Difficulty: Medium

// Approach:
// We need the lexicographically smallest string formed from
// a leaf to the root.
//
// Perform a DFS traversal while maintaining the current path
// from the root to the current node using a StringBuilder.
//
// For every visited node:
// 1. Convert its value (0–25) to the corresponding character
//    ('a'–'z') and append it to the current path.
//
// 2. If the current node is a leaf:
//    - Reverse the current path to obtain the required
//      leaf-to-root string.
//    - Compare it with the best answer found so far.
//    - If it is lexicographically smaller, update the answer.
//    - Reverse the StringBuilder again to restore the original
//      root-to-leaf order before returning.
//
// 3. Recursively explore the left and right subtrees.
//
// 4. Backtrack by removing the last character from the
//    StringBuilder so that sibling recursive calls start with
//    the correct root-to-parent path.
//
// The global answer stores the smallest leaf-to-root string
// encountered during the DFS.

// Time Complexity:
// O(n × h)
// where n is the number of nodes and h is the height of the tree.
// Each leaf comparison requires reversing/copying a path of
// length at most h.
//
// Space Complexity:
// O(h)
// due to the recursion stack and the StringBuilder storing
// the current root-to-node path.


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

    private String ans = null;
    private StringBuilder sb = new StringBuilder();

    public String smallestFromLeaf(TreeNode root) {
        dfs(root);
        return ans;
    }

    private void dfs(TreeNode root) {
        if (root == null) {
            return;
        }

        sb.append((char) (root.val + 'a'));

        if (root.left == null && root.right == null) {
            String curr = sb.reverse().toString();
            
            if (ans == null || curr.compareTo(ans) < 0) {
                ans = curr;
            }

            sb.reverse();
        }

        dfs(root.left);
        dfs(root.right);

        sb.deleteCharAt(sb.length() - 1);
    }
}
