// Problem: Reorder List
// Link: https://leetcode.com/problems/reorder-list/
// Difficulty: Medium

// Approach:
// Find the middle of linked list using slow and fast pointers.
// Reverse the second half of the list starting from middle.
// Merge the first half and reversed second half alternately:
//     first node -> last node -> second node -> second last node ...
// Continue merging until second half is fully processed.
// This reorders the list in-place without using extra space.

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
    public void reorderList(ListNode head) {
        if (head == null) return;

        ListNode slow = head, fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        ListNode prev = null, curr = slow;
        while (curr != null) {  
            ListNode temp = curr.next;
            curr.next = prev;

            prev = curr;
            curr = temp;
        }

        ListNode first = head, second = prev, temp;
        while (second.next != null) { 
            temp = first.next;
            first.next = second;
            first = temp;

            temp = second.next;
            second.next = first;
            second = temp;
        }
    }
}
