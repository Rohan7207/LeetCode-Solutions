// Problem: Remove Duplicates From Sorted List II
// Link: https://leetcode.com/problems/remove-duplicates-from-sorted-list-ii/
// Difficulty: Medium

// Approach:
// Use a dummy node before the head
// to handle deletions at the beginning.
// Maintain two pointers:
//     - prev -> last confirmed distinct node
//     - curr -> current node being checked
// Traverse the list while curr and curr.next exist:
//     - If current value is duplicated,
//       skip all nodes with that value.
//       Connect prev.next to the next distinct node.
//     - Otherwise move prev forward.
//     - Move curr forward in each step.
// Return dummy.next as the updated head.

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
        if (head == null || head.next == null) {
            return head;
        }

        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode prev = dummy;
        ListNode curr = head;

        while (curr != null && curr.next != null) {
            if (curr.val == curr.next.val) {
                //Skip all nodes with same val
                while (curr.next != null && curr.val == curr.next.val) {
                    curr = curr.next;
                }
                prev.next = curr.next; //Remove duplicates
            } else {
                prev = prev.next; //Move to next distinct node
            }
            curr = curr.next;
        }

        return dummy.next;
    }
}
