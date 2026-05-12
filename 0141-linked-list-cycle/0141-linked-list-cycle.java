// Problem: Linked List Cycle
// Link: https://leetcode.com/problems/linked-list-cycle/
// Difficulty: Easy

// Approach:
// Use Floyd’s Cycle Detection Algorithm
// (Tortoise and Hare approach).
// Maintain two pointers:
//     - slow moves one step at a time
//     - fast moves two steps at a time
// Initialize both pointers at head.
// Traverse the list while fast and fast.next exist:
//     - Move slow by one node.
//     - Move fast by two nodes.
//     - If slow and fast meet,
//       cycle exists in linked list.
// If traversal ends with fast reaching null,
// no cycle exists.

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
    public boolean hasCycle(ListNode head) {
        if (head == null || head.next == null) return false;

        ListNode fast = head;
        ListNode slow = head;

        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;

            if (fast == slow) return true;
        }

        return false;
    }
}
