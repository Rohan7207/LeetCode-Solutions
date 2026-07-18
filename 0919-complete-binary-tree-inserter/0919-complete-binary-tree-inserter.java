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

        while(!level.isEmpty()) {
            TreeNode curr = level.poll();

            if(curr.left != null) level.offer(curr.left);
            if(curr.right != null) level.offer(curr.right);

            if(curr.left == null || curr.right == null) {
                q.offer(curr);
            }
        }
    }
    
    public int insert(int val) {
        TreeNode node = q.peek();

        TreeNode curr = new TreeNode(val);

        if(node.left == null) {
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