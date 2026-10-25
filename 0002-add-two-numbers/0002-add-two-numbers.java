// Problem: Add Two Numbers
// Link: https://leetcode.com/problems/add-two-numbers/
// Difficulty: Medium

// Approach:
// 1. Create a dummy node to simplify building the result linked list.
// 2. Maintain a tail pointer to append new result nodes.
// 3. Initialize carry = 0.
// 4. Continue while either l1 or l2 has nodes:
//    a. Extract the current digits; use 0 if a list has ended.
//    b. Calculate sum = digit1 + digit2 + carry.
//    c. Update carry = sum / 10.
//    d. Create a node containing sum % 10 and append it.
//    e. Move l1 and l2 forward if they are not null.
// 5. If a carry remains after both lists end, append it.
// 6. Return dummy.next.

// Time Complexity: O(max(m, n))
// Space Complexity: O(max(m, n)) for the result list


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
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;
        int carry = 0;

        while (l1 != null || l2 != null) {
            int x = (l1 != null) ? l1.val : 0;
            int y = (l2 != null) ? l2.val : 0;

            int sum = carry + x + y;
            carry = sum / 10;

            tail.next = new ListNode(sum % 10);
            tail = tail.next;

            if (l1 != null) {
                l1 = l1.next;
            }

            if (l2 != null) {
                l2 = l2.next;
            }
        }

        if (carry > 0) {
            tail.next = new ListNode(carry);
        }

        return dummy.next;
    }
}
