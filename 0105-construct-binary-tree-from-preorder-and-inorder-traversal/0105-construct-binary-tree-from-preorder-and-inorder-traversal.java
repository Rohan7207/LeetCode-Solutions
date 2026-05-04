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
    int preidx;
    Map<Integer, Integer> inorderMap;

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        preidx = 0;
        inorderMap = new HashMap<>();

        for (int i = 0; i < inorder.length; i++) {
            inorderMap.put(inorder[i], i);
        }

        return Tree(preorder, 0, preorder.length - 1);
    }

    
    private TreeNode Tree(int[] preorder, int left, int right) {
        if (left > right) return null;

        int rootval = preorder[preidx];
        preidx++;

        TreeNode root = new TreeNode(rootval);
        root.left = Tree(preorder, left, inorderMap.get(rootval) - 1);
        root.right = Tree(preorder, inorderMap.get(rootval) + 1, right);

        return root;
    }
}

//inorder is used to get left and right subtree
//preorder is used to get root
