// Problem: Remove Duplicates From Sorted List 
// Link: https://leetcode.com/problems/remove-duplicates-from-sorted-list/
// Difficulty: Easy

// Approach:
// Use a pointer curr starting from head.
// Traverse the linked list while curr
// and curr.next are not null:
//     - If curr and curr.next have the same value,
//       remove the duplicate node by linking
//       curr.next to curr.next.next.
//     - Otherwise move curr to the next node.
// Return the head of the modified list.

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
    public ListNode deleteDuplicates(ListNode head) {
        ListNode curr = head;
        while (curr != null && curr.next != null) {
            if (curr.val == curr.next.val) {
                curr.next = curr.next.next;
            } else {
                curr = curr.next;
            }
        }
        return head;
    }
}
