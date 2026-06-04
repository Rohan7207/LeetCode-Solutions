// Problem: Flatten a Multilevel Doubly Linked List
// Link: https://leetcode.com/problems/flatten-a-multilevel-doubly-linked-list/
// Difficulty: Medium

// Approach:
// Use DFS preorder traversal to flatten
// the multilevel doubly linked list.
//
// For every node:
//     - Connect previous node with current node.
//     - Store current node's child.
//     - Store current node's next.
//     - Set child pointer to null.
//     - First flatten the child list.
//     - Then attach the original next list
//       after the child list tail.
//
// Dummy node is used to simplify connection
// with the head node.

// Time Complexity: O(n)
// Space Complexity: O(n)


/*
// Definition for a Node.
class Node {
    public int val;
    public Node prev;
    public Node next;
    public Node child;
};
*/

class Solution {
    public Node flatten(Node head) {
        return solution_dfs(head);
    }

    private Node solution_dfs(Node head) {
        Node dummy = new Node(-1, null, null, null);
        dfs(dummy, head);
        Node res = dummy.next;
        if (res != null) {
            res.prev = null;
        }

        return res;
    }

    private Node dfs(Node pre, Node cur) {
        if (cur == null) {
            return pre;
        }

        pre.next = cur;
        cur.prev = pre;

        Node child = cur.child;
        cur.child = null;

        Node next = cur.next;

        Node tail = dfs(cur, child);

        return dfs(tail, next);
    }
}
