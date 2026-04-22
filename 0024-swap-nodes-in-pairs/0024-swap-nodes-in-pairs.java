// Problem: Swap Nodes in Pairs
// Link: https://leetcode.com/problems/swap-nodes-in-pairs/
// Difficulty: Medium

// Approach:
// Use pointers to swap nodes in pairs.
// Store the second node as the new head after the first swap.
// For each pair:
//   - Store the next pair starting node.
//   - Reverse the links between the two nodes.
//   - Connect the previous swapped pair to the current swapped pair.
// Move pointers forward and continue until fewer than two nodes remain.
// Return the updated head.

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
    public ListNode swapPairs(ListNode head) {
        if(head == null || head.next == null) return head;
        
        ListNode first = head;
        ListNode second = head.next;
        ListNode prev = null;

        while(first != null && second != null) {
            ListNode third = second.next;

            second.next = first;
            first.next = third;

            if(prev != null) {
                prev.next = second;
            } else {
                head = second;
            }

            //update
            prev = first;
            first = third;

            if(third != null) {
                second = third.next;
            } else {
                second = null;
            }
        }

        return head;
    }
}
