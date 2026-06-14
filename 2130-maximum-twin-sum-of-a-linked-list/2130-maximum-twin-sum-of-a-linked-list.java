// Problem: Maximum Twin Sum of a Linked List
// Link: https://leetcode.com/problems/maximum-twin-sum-of-a-linked-list/?envType=daily-question&envId=2026-06-14
// Difficulty: Medium

// Approach:
// We need to find the maximum twin sum
// in an even-length linked list.
// Twin nodes are:
//     1st ↔ Last
//     2nd ↔ Second Last
//     3rd ↔ Third Last
// Instead of storing values in an array,
// use the linked list itself.
// Step 1:
//     Find the middle of the linked list
//     using Slow and Fast pointers.
// Step 2:
//     Reverse the second half of the list.
// Step 3:
//     Use two pointers:
//         p1 -> first half
//         p2 -> reversed second half
//     Compute:
//         p1.val + p2.val
//     for every twin pair.
// Step 4:
//     Keep track of the maximum twin sum.
// Return the maximum sum found.

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
    public int pairSum(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        ListNode prev = null;
        ListNode curr = slow;

        while (curr != null) {
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }

        int max = 0;
        int sum = 0;

        ListNode p1 = head;
        ListNode p2 = prev;

        while (p2 != null) {
            sum = p1.val + p2.val;

            p1 = p1.next;
            p2 = p2.next;

            if (max < sum) {
                max = sum;
            }
        }

        return max;
    }
}
