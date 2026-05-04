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