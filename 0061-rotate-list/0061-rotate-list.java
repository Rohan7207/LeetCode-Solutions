// Problem: Rotate List
// Link:https://leetcode.com/problems/rotate-list/
// Difficulty: Medium

// Approach:
// Find the length of the linked list and the last node.
// Compute the effective rotations using k % length.
// If rotations become 0, return the original head.
// Traverse to the node just before the new head position.
// Make the next node as the new head.
// Break the link at the new tail.
// Connect the old tail to the original head
// to complete the rotation.
// Return the new head.

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
    public ListNode rotateRight(ListNode head, int k) {
        if (head == null) return head;

        int length = 1;
        ListNode temp = head;

        while (temp.next != null) {
            temp = temp.next;
            length++;
        }

        int position = k % length;
        if (position == 0) return head;

        ListNode current = head;
        for (int i = 0; i < length - position - 1; i++) {
            current = current.next;
        }

        ListNode newHead = current.next;
        current.next = null;
        temp.next = head;
        return newHead;
    }
}
