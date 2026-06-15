// Problem: Delete the Middle Node of a Linked List
// Link: https://leetcode.com/problems/delete-the-middle-node-of-a-linked-list/?envType=daily-question&envId=2026-06-15
// Difficulty: Medium

// Approach:
// We need to delete the middle node
// of the linked list.
// If the list contains only one node:
//     Delete it and return null.
// Use Slow and Fast pointers
// to locate the middle node.
// Maintain:
//     slow -> middle candidate
//     fast -> moves 2 steps
//     prev -> node before slow
// Traverse:
//     slow moves 1 step
//     fast moves 2 steps
// When fast reaches the end:
//     slow points to the middle node
//     prev points to the node before middle
// Delete the middle node by:
//     prev.next = slow.next
// Return head.

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
    public ListNode deleteMiddle(ListNode head) {
        if (head.next == null) return null;

        ListNode slow = head;
        ListNode fast = head;
        ListNode prev = null;

        while (fast != null && fast.next != null) {
            prev = slow;
            slow = slow.next;
            fast = fast.next.next;
        }

        ListNode next = slow.next;
        prev.next = next;

        return head;
    }
}
