/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class Solution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode temp1 = headA;
        ListNode temp2 = headB;
        while(temp1 != temp2) {
            temp1 = (temp1 == null) ? headB : temp1.next;
            temp2 = (temp2 == null) ? headA : temp2.next;
        }

        return temp1;
    }
}

/*
    // Optimal appraoch is two-pointer sync approach with O(M+N) with O(1)
    // When Pointer A reaches the end of its list, redirected it to the head of List B. When Pointer B reaches its end, redirect it to the head of List A.They will eventually "sync up" and meet exactly at the intersection node (or at null if there isn't one).
    // 1ms with 99.89%
*/

/*
    O(M*N) where M and N are length of two lists and O(1) beats 5.01% and 1443ms
        ListNode temp1 = headA;

        while(temp1 != null) {
            ListNode temp2 = headB;
            while(temp2 != null) {
                if(temp1 == temp2) {
                    return temp1;
                }

                temp2 = temp2.next;
            }

            temp1 = temp1.next;
        }
       
        return null;


        It is optimal in terms of time i.e. O(M+N) but in space O(M) or O(N) with 10ms beats 13.96%

        Set<ListNode> s = new HashSet<>();
        ListNode temp1 = headA;
        while(temp1 != null) {
            s.add(temp1);
            temp1 = temp1.next;
        }

        ListNode temp2 = headB;
        while(temp2 != null) {
            if(s.contains(temp2)) {
                return temp2;
            }

            temp2 = temp2.next;
        }

        return null;
*/