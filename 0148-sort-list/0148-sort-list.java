// Problem: Sort List
// Link:https://leetcode.com/problems/sort-list/
// Difficulty: Medium

// Approach:
// Use Merge Sort on the linked list.
// Find the middle of the list using slow
// and fast pointers and split the list
// into two halves.
// Recursively sort both halves.
// Merge the two sorted linked lists
// using the merge procedure.
// Return the merged sorted list.

// Time Complexity: O(n log n)
// Space Complexity: O(log n) // recursion stack


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
    public ListNode sortList(ListNode head) {
        if(head == null || head.next == null) return head;

        ListNode slow = head;
        ListNode fast = head.next;

        while(fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        ListNode mid = slow.next;
        slow.next = null;

        ListNode l1 = sortList(head);
        ListNode l2 = sortList(mid);

        return merge(l1, l2);
    }

    private ListNode merge(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(-1);

        ListNode temp = dummy;

        while(l1 != null && l2 != null) {
            if(l1.val < l2.val) {
                temp.next = l1;
                l1 = l1.next;
            } else {
                temp.next = l2;
                l2 = l2.next;
            }

            temp = temp.next;
        }

        if(l1 != null) {
            temp.next = l1;
        }

        if(l2 != null) {
            temp.next = l2;
        }

        return dummy.next;
    }
}
