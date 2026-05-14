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

        //Middle of list
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        //Reverse from middle to end
        ListNode prev = null, curr = slow;
        while (curr != null) {  
            ListNode temp = curr.next;
            curr.next = prev;

            prev = curr;
            curr = temp;
        }

        //Merge from starting middle and reversed list till reverse list is null
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

/*
    public void reorderList(ListNode head) {
        if(head == null) return;

        ListNode fast = head, slow = head;

        while(fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        ListNode curr = slow, prev = null;

        while(curr != null) {
            ListNode temp = curr.next;
            curr.next = prev;

            prev = curr;
            curr = temp;
        }

        ListNode first = head, second = prev, temp;

        while(second.next != null) {
            temp = first.next;
            first.next = second;
            first = temp;

            temp = second.next;
            second.next = first;
            second = temp;
        }
    }
*/