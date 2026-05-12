// Problem: Linked List Cycle II
// Link: https://leetcode.com/problems/linked-list-cycle-ii/
// Difficulty: Medium

// Approach:
// Use Floyd’s Cycle Detection Algorithm
// to detect the cycle first.
// Step 1: Detect cycle
//     - Maintain slow and fast pointers.
//     - Move slow by one step.
//     - Move fast by two steps.
//     - If they meet, cycle exists.
//
// Step 2: Find cycle starting node
//     - Move one pointer back to head.
//     - Keep the other pointer at meeting point.
//     - Move both one step at a time.
//     - The node where they meet again
//       is the starting node of cycle.
//
// If no cycle exists return null.

// Time Complexity: O(n)
// Space Complexity: O(1)


/**
 * Definition for singly-linked list.
 * class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class Solution {
    public ListNode detectCycle(ListNode head) {
        if (head == null || head.next == null)
            return null;

        ListNode fast = head;
        ListNode slow = head;

        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;

            if (fast == slow) {
                return getStartIndex(head, slow);
            }
        }

        return null;
    }

    private ListNode  getStartIndex(ListNode head, ListNode slow) {
        ListNode temp = head;
    
        while (temp != slow) {
            temp = temp.next;
            slow = slow.next;
        }

        return temp;
    }
}
