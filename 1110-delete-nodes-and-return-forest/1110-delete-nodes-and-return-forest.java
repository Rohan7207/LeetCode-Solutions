// Problem: Delete Nodes And Return Forest
// Link: https://leetcode.com/problems/delete-nodes-and-return-forest/
// Difficulty: Medium

// Approach:
// Use postorder DFS so that the children of a node are processed
// before deciding whether to delete the current node.
//
// 1. Store all values that need to be deleted in a HashSet for O(1)
//    average-time lookup.
//
// 2. Recursively process the left and right subtrees first.
//
// 3. After both children are processed, check whether the current
//    node needs to be deleted.
//
// 4. If the current node is deleted:
//    - Its remaining left child becomes a new tree root, so add it
//      to the forest.
//    - Its remaining right child also becomes a new tree root.
//    - Return null so the parent disconnects this deleted node.
//
// 5. If the node is not deleted, return it so it remains connected
//    to its parent.
//
// 6. After DFS finishes, if the original root was not deleted,
//    add it to the forest.

// Time Complexity: O(n)
// Space Complexity: O(n)
//    HashSet + recursion stack + resulting forest.


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
    public List<TreeNode> delNodes(TreeNode root, int[] to_delete) {
        // Approach : Recursion (Postorder Traversal)
        Set<Integer> toDeleteSet = new HashSet<>();

        for (int val : to_delete) {
            toDeleteSet.add(val);
        }

        List<TreeNode> forest = new ArrayList<>();

        root = processNode(root, toDeleteSet, forest);

        // If the root is not deleted, add it to the forest
        if (root != null) {
            forest.add(root);
        }

        return forest;
    }

    private TreeNode processNode(TreeNode root, Set<Integer> toDeleteSet, List<TreeNode> forest) {
        if (root == null) {
            return null;
        }

        root.left = processNode(root.left, toDeleteSet, forest);
        root.right = processNode(root.right, toDeleteSet, forest);

        // Node Evaluation: Check if the current node needs to be deleted
        if (toDeleteSet.contains(root.val)) {
            // If the node has left or right children, add them to the forest
            if (root.left != null) {
                forest.add(root.left);
            }

            if (root.right != null) {
                forest.add(root.right);
            }

            // Return null to its parent to delete the current node
            return null;
        }

        return root;
    }
}
