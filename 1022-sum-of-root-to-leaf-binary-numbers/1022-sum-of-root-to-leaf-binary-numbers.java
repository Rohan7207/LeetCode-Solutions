// Problem: Sum of Root To Leaf Binary Numbers
// Link: https://leetcode.com/problems/sum-of-root-to-leaf-binary-numbers/
// Difficulty: Easy

// Approach:
// Use DFS to traverse every root-to-leaf path while constructing
// the binary number represented by that path.
//
// 1. Maintain `current`, which represents the binary value of the
//    path from the root to the current node.
//
// 2. When visiting a node, append its binary digit using:
//
//       current = current * 2 + root.val
//
//    Multiplying by 2 shifts the existing binary number left by
//    one position, and `root.val` adds the new bit.
//
// 3. When a leaf is reached, `current` represents the complete
//    root-to-leaf binary number, so add it to `ans`.
//
// 4. Pass `current` to both left and right recursive calls.
//    Each recursive call gets its own value, so no backtracking
//    is required.
//
// 5. After DFS visits all root-to-leaf paths, return `ans`.

// Time Complexity: O(n)
// Space Complexity: O(h)


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

    private int ans = 0;

    public int sumRootToLeaf(TreeNode root) {
        dfs(root, 0);
        return ans;
    }

    private void dfs(TreeNode root, int current) {
        if (root == null) {
            return;
        }

        current = current * 2 + root.val;

        if (root.left == null && root.right == null) {
            ans += current;
            return;
        }

        dfs(root.left, current);
        dfs(root.right, current);
    }
}
