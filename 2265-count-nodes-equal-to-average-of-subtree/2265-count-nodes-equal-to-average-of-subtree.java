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

    int ans = 0;
    Map<TreeNode, Integer> map = new HashMap<>();

    public int averageOfSubtree(TreeNode root) {
        countNodes(root);
        avgOfNodes(root);

        return ans;
    }

    private int avgOfNodes(TreeNode root) {
        if(root == null) {
            return 0;
        }

        int leftSum = avgOfNodes(root.left);
        int rightSum = avgOfNodes(root.right);

        int currSum = leftSum + rightSum + root.val;
        int currCount = map.get(root);

        if((currSum / currCount) == root.val) {
            ans++;
        }

        return currSum;
    }

    private int countNodes(TreeNode root) {
        if(root == null) {
            return 0;
        }

        int left = countNodes(root.left);
        int right = countNodes(root.right);

        int count = left + right + 1;
        map.put(root, count);

        return count;
    }
}