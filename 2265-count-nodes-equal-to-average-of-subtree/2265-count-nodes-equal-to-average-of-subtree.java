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

    class Info {
        int sum;
        int count;

        Info(int sum, int count) {
            this.sum = sum;
            this.count = count;
        }
    }

    int ans = 0;

    public int averageOfSubtree(TreeNode root) {
        dfs(root);

        return ans;
    }

    private Info dfs(TreeNode root) {
        if(root == null) {
            return new Info(0, 0);
        }

        Info left = dfs(root.left);
        Info right = dfs(root.right);

        int sum = left.sum + right.sum + root.val;
        int count = left.count + right.count + 1;

        if((sum / count) == root.val) {
            ans++;
        }

        return new Info(sum, count);
    }
}

/*
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
*/