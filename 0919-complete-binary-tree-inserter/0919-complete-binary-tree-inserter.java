// Problem: Complete Binary Tree Inserter
// Link: https://leetcode.com/problems/complete-binary-tree-inserter/
// Difficulty: Medium

// Approach:
// The goal is to maintain the Complete Binary Tree property
// while supporting insertions in O(1) time.
// Instead of traversing the tree for every insertion,
// maintain a queue containing all nodes that have
// less than two children.
// During construction:
// • Perform a level-order traversal (BFS) of the tree.
// • Visit every node exactly once.
// • Add its left and right children to the BFS queue.
// • If the current node has less than two children,
//   add it to the insertion queue.
// After the constructor finishes, the insertion queue
// contains all incomplete nodes in level-order.
//
// During insertion:
// • The front of the insertion queue is always the
//   correct parent for the next node.
// • If the parent's left child is empty,
//   insert the new node there.
// • Otherwise, insert it as the right child.
//   Since the parent now has both children,
//   remove it from the queue.
// • The newly inserted node has no children,
//   so add it to the back of the queue.
//
// get_root() simply returns the original root.

// Time Complexity:
// Constructor : O(n)
// Insert      : O(1)
// get_root    : O(1)
//
// Space Complexity:
// O(n)


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
class CBTInserter {

    private TreeNode root;
    private Queue<TreeNode> q; //stores all nodes that have less than two children.

    public CBTInserter(TreeNode root) {
        this.root = root;
        q = new LinkedList<>();

        Queue<TreeNode> level = new LinkedList<>();
        level.offer(root);

        while (!level.isEmpty()) {
            TreeNode curr = level.poll();

            if (curr.left != null)
                level.offer(curr.left);
            if (curr.right != null)
                level.offer(curr.right);

            if (curr.left == null || curr.right == null) {
                q.offer(curr);
            }
        }
    }

    public int insert(int val) {
        TreeNode node = q.peek();

        TreeNode curr = new TreeNode(val);

        if (node.left == null) {
            node.left = curr;
        } else {
            node.right = curr;
            q.poll();
        }

        q.offer(curr);

        return node.val;
    }

    public TreeNode get_root() {
        return root;
    }
}

/**
 * Your CBTInserter object will be instantiated and called as such:
 * CBTInserter obj = new CBTInserter(root);
 * int param_1 = obj.insert(val);
 * TreeNode param_2 = obj.get_root();
 */
