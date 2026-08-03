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

    private List<Integer> ans = new ArrayList<>();
    private int index = 0;
    private boolean possible = true;

    public List<Integer> flipMatchVoyage(TreeNode root, int[] voyage) {
        dfs(root, voyage);

        if (!possible) {
            return Arrays.asList(-1);
        }

        return ans;
    }

    private void dfs(TreeNode root, int[] voyage) {
        if (root == null) {
            return;
        }

        if (root.val != voyage[index]) {
            possible = false;
            return;
        }

        index++;

        if (root.left != null && index < voyage.length && root.left.val != voyage[index]) {
            ans.add(root.val);
            dfs(root.right, voyage);
            dfs(root.left, voyage);
        } else {
            dfs(root.left, voyage);
            dfs(root.right, voyage);
        }
    }
}