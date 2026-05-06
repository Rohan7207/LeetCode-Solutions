// Problem: Populating Next Right Pointers in Each Node II
// Link: https://leetcode.com/problems/populating-next-right-pointers-in-each-node-ii/
// Difficulty: Medium

// Approach:
// Traverse the tree level by level without using extra space.
// Use the already established "next" pointers to move across
// the current level.
// For each level:
//     - Create a dummy node to act as the head of the next level.
//     - Use a pointer (chain) to build the next level connections.
//     - Traverse current level using curr = curr.next:
//         - If curr.left exists → connect it using chain.next
//         - If curr.right exists → connect it using chain.next
//         - Move chain forward each time we add a node
//     - After finishing the level:
//         - Move to next level using level = dummy.next
// Repeat until all levels are processed.

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
        if(root == null) 
            return null;

        Node level = root;

        while(level != null) {
            Node dummy = new Node(0);
            Node chain = dummy;

            Node curr = level;
            while(curr != null) {
                if(curr.left != null) {
                    chain.next = curr.left;
                    chain = chain.next;
                } 

                if(curr.right != null) {
                    chain.next = curr.right;
                    chain = chain.next;
                }

                curr = curr.next;
            }
            level = dummy.next;
        }

        return root;
    }
}
