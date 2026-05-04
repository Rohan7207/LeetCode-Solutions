// Problem: Binary Tree Zigzag Level Order Traversal
// Link: https://leetcode.com/problems/binary-tree-zigzag-level-order-traversal/
// Difficulty: Medium

// Approach:
// Use BFS (level order traversal) with a queue.
// Add root to queue and a null marker to indicate
// end of the current level.
// Maintain a flag is_order_left to control direction.
// For each node:
//     - If not null:
//         - Add value to level list:
//             left → addLast
//             right → addFirst
//         - Add left and right children to queue.
//     - If null:
//         - One level is completed.
//         - Add current level list to result.
//         - Reset level list.
//         - Add another null marker if queue not empty.
//         - Toggle direction (left ↔ right).
// Return the zigzag level order traversal.

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
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        if (root == null) {
            return new ArrayList<List<Integer>>();
        }

        List<List<Integer>> res = new ArrayList<>();

        //Add root to queue and initialise another with null to break the bfs loop
        LinkedList<TreeNode> q = new LinkedList<TreeNode>();
        q.addLast(root);
        q.addLast(null);

        LinkedList<Integer> level_list = new LinkedList<Integer>();
        boolean is_order_left = true;

        while (q.size() > 0) {
            TreeNode curr = q.pollFirst();
            if (curr != null) {
                if (is_order_left)
                    level_list.addLast(curr.val);
                else
                    level_list.addFirst(curr.val);

                if (curr.left != null)
                    q.addLast(curr.left);
                if (curr.right != null)
                    q.addLast(curr.right);
            } else {
                //we finished the scan of one level
                res.add(level_list);
                level_list = new LinkedList<Integer>();

                //For next level
                if (q.size() > 0)
                    q.addLast(null);
                is_order_left = !is_order_left;
            }
        }

        return res;
    }
}
