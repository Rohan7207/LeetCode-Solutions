// Problem: Remove Nth Node From End of List
// Link: https://leetcode.com/problems/remove-nth-node-from-end-of-list/
// Difficulty: Medium

// Approach:
// Use two pointers (fast and slow).
// Move the fast pointer n steps ahead to maintain a gap of n nodes.
// Then move both fast and slow together until fast reaches the end.
// At this point, slow will be just before the node to be deleted.
// Adjust the next pointer of slow to remove the target node.
// Use a dummy node to handle edge cases like removing the head.

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
    public ListNode removeNthFromEnd(ListNode head, int n) {

        ListNode fast = head;
        ListNode slow = head;
        ListNode prev = null;

        for(int i = 0; i < n; i++){
            fast = fast.next;
        }

        while(fast != null){
            fast = fast.next;
            prev = slow;
            slow = slow.next;
        }

        if(prev != null){
            prev.next = slow.next;
        }else{
            head = head.next;
        }

        return head;
    }
}
