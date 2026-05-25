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
    int i = 0;
    int ans = 0;
    public int kthSmallest(TreeNode root, int k) {
        if(root == null || i >= k) return ans;

        kthSmallest(root.left, k);
        i++;

        if(i == k) ans = root.val;

        kthSmallest(root.right, k);

        return ans;
    }
}

/*
     public ArrayList<Integer> inorder(TreeNode root,ArrayList<Integer> arr){
    if(root==null) return arr;

    inorder(root.left,arr);
    arr.add(root.val);
    inorder(root.right,arr);
    return arr;
    }

    public int kthSmallest(TreeNode root, int k) {
        ArrayList<Integer> res=inorder(root,new ArrayList<Integer>());   

        return res.get(k-1);
    }
*/