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

/*
 // intuition, it's hard to go in backwards in a list. So we need 2 pointers when traversing the list, one fast one slow so the slow one can do the removal when the fast one reaches the end of the list.
// actually we need another prev before the slow pointer to do the removal.


     if(head.next == null){
            return null;
        }

        int size = 0;
        ListNode current = head;
        while(current != null){
            current = current.next;
            size++;
        }

        //If given node is 1st or head
        if(n == size){
            return head.next;
        }

        int indextosearch = size-n; //returns the search index -1
        ListNode prev = head;
        int i = 1;
        while(i < indextosearch){
            prev = prev.next;
            i++;
        }
        prev.next = prev.next.next;
        return head;

*/