/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
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

    private boolean ans;
    
    public boolean isSubPath(ListNode head, TreeNode root) {
        dfs(head, root);

        return ans;
    }

    private void dfs(ListNode head, TreeNode root) {
        if (root == null) {
            return;
        }

        // For every valid start node search for downward path by calling recursive function
        if ((head.val == root.val) && match(head, root)) {  
            ans = true;
            return;
        }

        // Try exploring the children nodes
        dfs(head, root.left);   
        dfs(head, root.right);
    }

    private boolean match(ListNode curr, TreeNode node) {
        if(curr == null) {
            return true;   // Entire list is matched
        }

        if(node == null) {
            return false;  // tree path ended before list match
        }

        if(curr.val != node.val) {
            return false;
        }

        return match(curr.next, node.left) || match(curr.next, node.right);
    }
}