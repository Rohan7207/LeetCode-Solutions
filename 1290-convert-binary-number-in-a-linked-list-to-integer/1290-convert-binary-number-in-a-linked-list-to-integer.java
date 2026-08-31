// Problem: Convert Binary Number in a Linked List to Integer
// Link: https://leetcode.com/problems/convert-binary-number-in-a-linked-list-to-integer/
// Difficulty: Easy

// Approach:
// Use Bit Manipulation + Linked List Traversal.
//
// 1. Traverse the linked list from left to right.
//
// 2. `res` stores the decimal value of the binary digits processed
//    so far.
//
// 3. For every bit, shift `res` left by 1 position:
//
//      res << 1
//
//    This is equivalent to multiplying the current value by 2.
//
// 4. Add the current binary digit using bitwise OR:
//
//      (res << 1) | curr.val
//
// 5. Continue until the end of the linked list.
//
// 6. The final value of `res` is the decimal representation.

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
    public int getDecimalValue(ListNode head) {
        ListNode curr = head;
        int res = 0;

        while (curr != null) {
            res = (res << 1) | curr.val;
            curr = curr.next;
        }

        return res;
    }
}
