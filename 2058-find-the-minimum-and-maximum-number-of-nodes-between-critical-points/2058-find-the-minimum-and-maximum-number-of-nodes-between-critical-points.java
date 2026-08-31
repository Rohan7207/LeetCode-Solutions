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
    public int[] nodesBetweenCriticalPoints(ListNode head) {
        if (head == null || head.next == null || head.next.next == null) {
            return new int[] { -1, -1 };
        }

        ListNode prev = head;
        ListNode curr = head.next;

        int min = Integer.MAX_VALUE;
        int first = -1;
        int last = -1;
        int temp = -1;
        int pos = 2;

        while (curr.next != null) {
            ListNode next = curr.next;

            if (((curr.val < prev.val) && (curr.val < next.val)) ||
              ((curr.val > prev.val) && (curr.val > next.val))) {
                if (first == -1) {
                    first = pos;
                    temp = pos;
                } else {
                    last = pos;
                    min = Math.min(min, pos - temp);
                    temp = pos;
                }
            }

            pos++;
            prev = curr;
            curr = curr.next;
        }

        if (last == -1) {
            return new int[] { -1, -1 };
        }

        int max = last - first;

        return new int[] { min, max };
    }
}