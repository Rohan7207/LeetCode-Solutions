// Problem: Populating Next Right Pointers in Each Node
// Link: https://leetcode.com/problems/populating-next-right-pointers-in-each-node/
// Difficulty: Medium

// Approach:
// Use level-by-level traversal in a perfect binary tree.
// Since every node has both left and right child,
// connect nodes using already established next pointers.
// For each node at current level:
//     - Connect left child to right child.
//     - Connect right child to next node's left child
//       if next node exists.
// Traverse current level using curr = curr.next.
// Move down level-by-level using level = level.left.

// Time Complexity: O(n)
// Space Complexity: O(1)


/*
// Definition for a Node.
class Node {
    public int val;
    public Node left;
    public Node right;
    public Node next;

    public Node() {}
    
    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, Node _left, Node _right, Node _next) {
        val = _val;
        left = _left;
        right = _right;
        next = _next;
    }
};
*/

class Solution {
    public Node connect(Node root) {
        if(root == null) return null;

        Node level = root;

        while(level.left != null) {
            Node curr = level;   

            while(curr != null) {
                curr.left.next = curr.right;

                if(curr.next != null) {
                    curr.right.next = curr.next.left;
                }
                curr = curr.next;
            }

            level = level.left;
        }

        return root;
    }
}
