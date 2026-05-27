// Problem: Delete Node in a Linked List
// Link: https://leetcode.com/problems/delete-node-in-a-linked-list/
// Difficulty: Medium

// Approach:
// Since only the node to be deleted is given,
// we cannot access its previous node.
// Copy the value of the next node into the
// current node.
// Then skip the next node by updating the
// current node's next pointer.
// This effectively removes the next node,
// making it appear as if the given node
// was deleted.

// Time Complexity: O(1)
// Space Complexity: O(1)


/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public void deleteNode(ListNode node) {
        node.val = node.next.val;
        node.next = node.next.next;
    }
}
