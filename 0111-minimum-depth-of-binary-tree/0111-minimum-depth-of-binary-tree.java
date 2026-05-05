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
    public int minDepth(TreeNode root) {
        if (root == null) return 0;

        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        int depth = 1;

        while (!q.isEmpty()) {
            int levelSize = q.size();

            for (int i = 0; i < levelSize; i++) {
                TreeNode current = q.poll();

                // First leaf node encountered is the minimum depth
                if (current.left == null && current.right == null) {
                    return depth;
                }

                if (current.left != null) q.offer(current.left);
                if (current.right != null) q.offer(current.right);
            }

            depth++;
        }

        return depth;
    }
}

//BFS approach time = O(N) in worst case scenario space = O(queue size), it terminates when first leaf node is found

/* 
    DFS approach time = O(N) space = O(H), it terminates when it visits
        both subtress runtime = 6ms 7.60%
        if(root == null) return 0;

        // If only one child exists, we must go down that path to find a leaf
        if(root.left == null) return minDepth(root.right) + 1;
        if(root.right == null) return minDepth(root.left) + 1;

        // If both children exist, take the minimum of both subtrees
        return Math.min(minDepth(root.left), minDepth(root.right)) + 1;

*/