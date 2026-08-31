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

/*
    while(temp!=null){
        ans = ans * 2 + temp.val;
        temp = temp.next;
    }
*/