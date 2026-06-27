// Problem: Two Sum IV - Input is a BST
// Link: https://leetcode.com/problems/two-sum-iv-input-is-a-bst/
// Difficulty: Easy

// Approach:
// Convert the BST problem into the Two Sum problem.
// Use DFS traversal + HashSet.
// During traversal:
//     For every node:
//     1. Calculate the required partner value:
//            k - root.val
//     2. Check if this required value already exists in HashSet.
//        If yes:
//             We found two numbers whose sum is k.
//             Return true.
//     3. Otherwise:
//             Store current node value in HashSet.
//     4. Continue searching:
//             - left subtree
//             - right subtree
// The same HashSet is passed to every recursive call,
// so it stores all previously visited nodes.
// Base case:
//     If root == null:
//          return false

// Time Complexity: O(n)
// Space Complexity: O(n)


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
    public boolean findTarget(TreeNode root, int k) {
        Set<Integer> set = new HashSet<>();

        return dfs(root, k, set);
    }

    private boolean dfs(TreeNode root, int k, Set<Integer> set) {
        if (root == null)
            return false;

        if (set.contains(k - root.val)) {
            return true;
        }

        set.add(root.val);

        return dfs(root.left, k, set) || dfs(root.right, k, set);
    }
}
