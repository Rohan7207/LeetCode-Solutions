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

        if(i == k) {
            ans = root.val;
            return ans;
        }

        kthSmallest(root.right, k);

        return ans;
    }
}

// Time Complexity:
//     O(k) average
//     O(n) worst case
//
// Space Complexity: O(h) recursion stack

// “I perform inorder traversal and terminate recursion as soon as the kth node is visited since BST inorder produces sorted order.”

/*
    Useful Interview Follow-up

If interviewer asks:

"Can we do better than O(n)?"

Answer:

For a static BST, inorder traversal is sufficient. If frequent kth-smallest queries are required, store subtree sizes at each node and use an Order Statistic Tree to answer queries in O(log n).
*/

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