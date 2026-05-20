// Problem: Remove Linked List Elements
// Link: https://leetcode.com/problems/remove-linked-list-elements/
// Difficulty: Easy

// Approach:
// First remove all leading nodes whose value
// matches the target value by moving head forward.
// After obtaining a valid head,
// traverse the remaining list using a current pointer.
// For each node:
//     - If next node contains target value,
//       remove it by skipping that node.
//     - Otherwise move current pointer forward.
// Continue until end of list.
// Return the modified head.

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
    public ListNode removeElements(ListNode head, int val) {
        if (head == null) return head;
        
        while (head != null && head.val == val) {
            head = head.next;
        }

        ListNode curr = head;

        while (curr != null && curr.next != null) {
            if (curr.next.val == val) {
                curr.next = curr.next.next;
            } else {
                curr = curr.next;
            }
        }

        return head;
    }
}
