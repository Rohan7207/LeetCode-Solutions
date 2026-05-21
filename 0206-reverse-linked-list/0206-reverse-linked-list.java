// Problem: Reverse Linked List 
// Link: https://leetcode.com/problems/reverse-linked-list/
// Difficulty: Easy

// Approach:
// Traverse the linked list using a current pointer.
// For every node:
//     - Store the next node temporarily.
//     - Reverse the current node's next pointer
//       to point to the previous node.
//     - Move previous pointer to current node.
//     - Move current pointer to next node.
// Continue until all nodes are processed.
// The previous pointer will point to the
// new head of the reversed list.
// Return previous pointer.

// Time Complexity: O(n)
// Space Complexity: O(1)


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
class Solution {
    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) return head;
        
        ListNode prev = null;
        ListNode curr = head;

        while (curr != null) {
            ListNode next = curr.next;
            curr.next = prev;

            prev = curr;
            curr = next;
        }

        return prev;
    }
}
