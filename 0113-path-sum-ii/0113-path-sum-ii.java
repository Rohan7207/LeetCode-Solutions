// Problem: Path Sum II
// Link: https://leetcode.com/problems/path-sum-ii/
// Difficulty: Medium

// Approach:
// Use backtracking (DFS) to find all root-to-leaf paths
// whose sum equals targetSum.
// Maintain a current path list.
// For each node:
//     - Add node value to current path.
//     - If it's a leaf and sum matches,
//       add a copy of current path to result.
//     - Otherwise recursively explore:
//         left subtree
//         right subtree
//     - Backtrack by removing the last element
//       before returning to parent.
// Return all valid paths.

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
    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        List<List<Integer>> res = new ArrayList<>();

        findPaths(root, targetSum, new ArrayList<>(), res);
        return res;
    }

    private void findPaths(TreeNode root, int targetSum, List<Integer> currPath, List<List<Integer>> res) {
        if (root == null) return;

        // 1. Process current node
        currPath.add(root.val);

        // 2. Check if it's a leaf and satisfies the target
        if (root.left == null && root.right == null && root.val == targetSum) {
            // Must create a NEW list copy; currentPath is constantly modified
            res.add(new ArrayList<>(currPath));
        } else {
            // 3. Recursive exploration
            findPaths(root.left, targetSum - root.val, currPath, res);
            findPaths(root.right, targetSum - root.val, currPath, res);
        }

        // 4. Backtrack: remove the node before returning to the parent
        currPath.remove(currPath.size() - 1);
    }
}
