// Problem: Merge Two Sorted Lists
// Link: https://leetcode.com/problems/merge-two-sorted-lists/
// Difficulty: Easy

// Approach:
// Create a dummy node and use a current pointer to build the merged list.
// Compare nodes from both lists and attach the smaller one to current.
// Move the corresponding pointer forward.
// Continue until one list is exhausted.
// Attach the remaining nodes of the non-empty list.
// Return dummy.next as the head of merged list.

// Time Complexity: O(n + m)
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
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode dummy = new ListNode(0);
        ListNode curr = dummy;

        while(list1 != null && list2 != null) {
            if(list1.val <= list2.val) {
                curr.next = list1;
                list1 = list1.next;
            } else {
                curr.next = list2;
                list2 = list2.next;
            }

            curr = curr.next;
        }

        if(list1 == null) {
            curr.next = list2;
        } else {
            curr.next = list1;
        }

        return dummy.next;
    }
}
