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
    public TreeNode createBinaryTree(int[][] descriptions) {
        Map<Integer, TreeNode> map = new HashMap<>();

        Set<Integer> children = new HashSet<>();

        for (int[] des : descriptions) {
            int parent = des[0];
            int child = des[1];

            map.putIfAbsent(parent, new TreeNode(parent));
            map.putIfAbsent(child, new TreeNode(child));

            children.add(child);
        }

        TreeNode root = null;

        for (Map.Entry<Integer, TreeNode> entry : map.entrySet()) {
            int value = entry.getKey();

            if (!children.contains(value)) {
                root = entry.getValue();
                break;
            }
        }

        for (int[] des : descriptions) {
            int parent = des[0];
            int child = des[1];
            int isLeft = des[2];

            TreeNode p = map.get(parent);
            TreeNode c = map.get(child);

            if (isLeft == 1) {
                p.left = c;
            } else {
                p.right = c;
            }
        }

        return root;
    }
}