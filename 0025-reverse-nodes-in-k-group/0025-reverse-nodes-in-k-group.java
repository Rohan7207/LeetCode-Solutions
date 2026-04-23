// Problem: Reverse Nodes in k-Group
// Link: https://leetcode.com/problems/reverse-nodes-in-k-group/
// Difficulty: Hard

// Approach:
// Check if there are at least k nodes remaining in the list.
// If fewer than k nodes exist, return the current head.
// Recursively process the remaining list after the first k nodes.
// Reverse the current group of k nodes and connect it
// with the recursively processed remaining list.
// Return the new head of the reversed group.

// Time Complexity: O(n)
// Space Complexity: O(n / k) (recursive call stack)

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
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode temp = head;
        int count = 0;

        //Check whether k-node exists or not
        while(count < k) {
            if(temp == null) {
                return head;
            }

            temp = temp.next;
            count++;
        }

        //Recusive call for rest list
        ListNode prevNode = reverseKGroup(temp, k);

        //Reverse current group        
        temp = head;
        count = 0;
        while(count < k) {
            ListNode next = temp.next;
            temp.next = prevNode;

            prevNode = temp;
            temp = next;

            count++;
        }

        return prevNode;   
    }
}
