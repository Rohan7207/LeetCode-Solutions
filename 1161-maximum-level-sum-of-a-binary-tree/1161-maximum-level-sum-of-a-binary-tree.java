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
    public int maxLevelSum(TreeNode root) {
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        int maxSum = Integer.MIN_VALUE;
        int level = 1;
        int ans = 0;

        while (!q.isEmpty()) {
            int size = q.size();
            int sum = 0;

            for (int i = 0; i < size; i++) {
                TreeNode curr = q.poll();
                sum += curr.val;

                if (curr.left != null) {
                    q.offer(curr.left);
                }

                if (curr.right != null) {
                    q.offer(curr.right);
                }
            }

            if (sum > maxSum) {
                ans = level;
                maxSum = sum;
            }

            level++;
        }
        
        return ans;
    }
}

/*
       Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        List<Integer> levelSum = new ArrayList<>();

        while(!q.isEmpty()) {
            int size = q.size();
            int sum = 0;

            for(int i = 0; i < size; i++) {
                TreeNode curr = q.poll();
                sum += curr.val;

                if(curr.left != null) {
                    q.offer(curr.left);
                } 

                if(curr.right != null) {
                    q.offer(curr.right);
                }
            }

            levelSum.add(sum);
        }

        int maxLevel = 0;
        int max = Integer.MIN_VALUE;
        for(int i = 0; i < levelSum.size(); i++) {
            if(max < levelSum.get(i)) {
                maxLevel = i + 1;
                max = levelSum.get(i);
            }
        }

        return maxLevel;
*/