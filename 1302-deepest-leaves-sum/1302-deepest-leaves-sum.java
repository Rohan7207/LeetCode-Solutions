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
    public int deepestLeavesSum(TreeNode root) {
        int depth = maxDepth(root);

        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);

        int level = 1;
        int sum = 0;

        while(!q.isEmpty()) {
            int size = q.size();

            if(level == depth) {
                for(int i = 0; i < size; i++) {
                    TreeNode curr = q.poll();

                    sum += curr.val;
                }

                return sum;
            }

            for(int i = 0; i < size; i++) {
                TreeNode curr = q.poll();

                if(curr.left != null) {
                    q.offer(curr.left);
                }

                if(curr.right != null) {
                    q.offer(curr.right);
                }
            }

            level++;
        }

        return -1;
    }

    private int maxDepth(TreeNode root) {
        if(root == null) {
            return 0;
        }

        int h1 = maxDepth(root.left);
        int h2 = maxDepth(root.right);

        return Math.max(h1, h2) + 1;
    }
}