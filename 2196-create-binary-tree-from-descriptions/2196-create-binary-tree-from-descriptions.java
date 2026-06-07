// Problem : Create Binary Tree From descriptions
// Link : https://leetcode.com/problems/create-binary-tree-from-descriptions/?envType=daily-question&envId=2026-06-07
// Difficulty : Medium

// Approach:
// Build the binary tree using parent-child descriptions.
// Each description contains:
//     - parent value
//     - child value
//     - isLeft value
// Use a HashMap to store:
//     value -> TreeNode
// This is needed because the same node value
// can appear multiple times, so we must reuse
// the same TreeNode object.
// Use a HashSet to store all child values.
// First pass:
//     - Create parent node if not already present
//     - Create child node if not already present
//     - Add child value to children set
// Root finding:
//     - Root is the only node that never appears as a child
//     - So the node whose value is not in children set is root
// Second pass:
//     - Get parent node from map
//     - Get child node from map
//     - If isLeft == 1:
//           attach child as left child
//     - Else:
//           attach child as right child
// Finally return root.

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
    public TreeNode createBinaryTree(int[][] descriptions) {
        Map<Integer, TreeNode> map = new HashMap<>();

        Set<Integer> children = new HashSet<>();

        for (int[] des : descriptions) {
            int parent = des[0];
            int child = des[1];

            map.putIfAbsent(parent, new TreeNode(parent));
            map.putIfAbsent(child, new TreeNode(child));

            children.add(child);
        }

        TreeNode root = null;

        for (Map.Entry<Integer, TreeNode> entry : map.entrySet()) {
            int value = entry.getKey();

            if (!children.contains(value)) {
                root = entry.getValue();
                break;
            }
        }

        for (int[] des : descriptions) {
            int parent = des[0];
            int child = des[1];
            int isLeft = des[2];

            TreeNode p = map.get(parent);
            TreeNode c = map.get(child);

            if (isLeft == 1) {
                p.left = c;
            } else {
                p.right = c;
            }
        }

        return root;
    }
}
