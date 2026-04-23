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