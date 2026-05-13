// Problem: Copy List with Random Pointer
// Link: https://leetcode.com/problems/copy-list-with-random-pointer/
// Difficulty: Medium

// Approach:
// Use a HashMap to store mapping between
// original nodes and copied nodes.
// Step 1:
//     - Create copy nodes for every original node.
//     - Store mapping:
//           original node -> copied node
//     - Connect copied nodes using next pointers.
// Step 2:
//     - Traverse both lists again.
//     - Assign random pointers for copied nodes
//       using hashmap mapping.
// Return the head of copied linked list.

// Time Complexity: O(n)
// Space Complexity: O(n)


/*
// Definition for a Node.
class Node {
    int val;
    Node next;
    Node random;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}
*/

class Solution {
    public Node copyRandomList(Node head) {
        if (head == null) return null;

        HashMap<Node, Node> m = new HashMap<>();

        Node newhead = new Node(head.val);
        Node oldtemp = head.next;
        Node newtemp = newhead;
        m.put(head, newhead);

        while (oldtemp != null) {
            Node copynode = new Node(oldtemp.val);
            m.put(oldtemp, copynode);
            newtemp.next = copynode;
            oldtemp = oldtemp.next;
            newtemp = newtemp.next;
        }

        oldtemp = head;
        newtemp = newhead;

        while (oldtemp != null) {
            newtemp.random = (oldtemp.random == null) ? null : m.get(oldtemp.random);
            oldtemp = oldtemp.next;
            newtemp = newtemp.next;
        }

        return newhead;
    }
}
