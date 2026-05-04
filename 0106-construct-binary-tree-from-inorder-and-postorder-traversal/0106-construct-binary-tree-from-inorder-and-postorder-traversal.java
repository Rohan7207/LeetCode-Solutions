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
    int postidx;
    Map<Integer, Integer> inorderMap;

    public TreeNode buildTree(int[] inorder, int[] postorder) {
        postidx = postorder.length - 1;
        inorderMap = new HashMap<>();

        for (int i = 0; i < inorder.length; i++) {
            inorderMap.put(inorder[i], i);
        }

        return Tree(postorder, 0, postorder.length - 1);
    }

    private TreeNode Tree(int[] postorder, int left, int right) {
        if (left > right)
            return null;

        int rootVal = postorder[postidx];
        postidx--;

        TreeNode root = new TreeNode(rootVal);
        root.right = Tree(postorder, inorderMap.get(rootVal) + 1, right);
        root.left = Tree(postorder, left, inorderMap.get(rootVal) - 1);

        return root;
    }
}