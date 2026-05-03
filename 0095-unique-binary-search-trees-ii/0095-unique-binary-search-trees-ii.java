// Problem: Unique Binary Search Trees II
// Link: https://leetcode.com/problems/unique-binary-search-trees-ii/
// Difficulty: Medium

// Approach:
// Use recursion to generate all unique BSTs
// for a given range of values.
// For every value in the range:
//     - Treat it as the root node.
//     - Recursively generate all possible
//       left subtrees using smaller values.
//     - Recursively generate all possible
//       right subtrees using larger values.
// Combine every left subtree with every
// right subtree and attach them to the root.
// Add each constructed tree to the result.
// If start > end, return a list containing null
// to represent an empty subtree.
// Return the list of all unique BSTs.

// Time Complexity: Exponential
// Space Complexity: O(n) // recursion stack


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
    public List<TreeNode> generateTrees(int n) {
        return n > 0 ? helper(1, n) : new ArrayList<>();
    }

    private List<TreeNode> helper(int start, int end) {
        List<TreeNode> all_Trees = new ArrayList<>();
        if (start > end) {
            all_Trees.add(null); 
            return all_Trees;
        }

        for (int i = start; i <= end; i++) {
            List<TreeNode> left_Trees = helper(start, i - 1);
            List<TreeNode> right_Trees = helper(i + 1, end);

            for (TreeNode l : left_Trees) {
                for (TreeNode r : right_Trees) {
                    TreeNode current_Tree = new TreeNode(i);
                    current_Tree.left = l;
                    current_Tree.right = r;
                    all_Trees.add(current_Tree);
                }
            }
        }

        return all_Trees;
    }
}
