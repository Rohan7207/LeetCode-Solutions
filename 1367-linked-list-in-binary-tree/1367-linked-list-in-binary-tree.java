// Problem: Linked List in Binary Tree
// Link: https://leetcode.com/problems/linked-list-in-binary-tree/
// Difficulty: Medium

// Approach:
// Use tree traversal + recursive path matching.
//
// 1. Traverse every tree node because the linked list can start at any
//    tree node, not necessarily the root.
// 2. If the current tree node matches the linked-list head, call `match()`
//    to check whether the complete list exists downward from this node.
// 3. In `match()`, if `curr == null`, the entire linked list is matched → true.
// 4. If `node == null` before the list ends, the path is invalid → false.
// 5. If the current values don't match, return false.
// 6. If they match, move to `curr.next` and check either the left or right
//    child of the tree.
// 7. Store the result in `ans` and stop the tree traversal once a valid
//    path is found using `root == null || ans`.

// Time Complexity: O(n × m) worst case
// Space Complexity: O(n + m)


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
        if (root == null || ans) {
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
        if (curr == null) {
            return true; // Entire list is matched
        }

        if (node == null) {
            return false; // tree path ended before list match
        }

        if (curr.val != node.val) {
            return false;
        }

        return match(curr.next, node.left) || match(curr.next, node.right);
    }
}
