// Problem: Middle of the Linked List
// Link: https://leetcode.com/problems/middle-of-the-linked-list/
// Difficulty: Easy

// Approach:
// We need to find the middle node of the linked list.
// Use two pointers:
//     slow moves one step at a time
//     fast moves two steps at a time
// Since fast moves twice as fast as slow,
// when fast reaches the end of the list,
// slow will be at the middle.
// Start both slow and fast at head.
// Keep moving:
//     slow = slow.next
//     fast = fast.next.next
// Continue while fast and fast.next are not null.
// When the loop ends, slow is pointing to the middle node.
// If the linked list has odd length:
//     slow points to the exact middle.
// If the linked list has even length:
//     slow points to the second middle node.
// Return slow.

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
    public ListNode middleNode(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;

        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }

        return slow;
    }
}
