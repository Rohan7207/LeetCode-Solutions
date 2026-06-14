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
    public int pairSum(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        ListNode prev = null;
        ListNode curr = slow;

        while (curr != null) {
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }

        int max = 0;
        int sum = 0;

        ListNode p1 = head;
        ListNode p2 = prev;

        while (p2 != null) {
            sum = p1.val + p2.val;

            p1 = p1.next;
            p2 = p2.next;

            if (max < sum) {
                max = sum;
            }
        }

        return max;
    }
}

/*
    public int pairSum(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        ListNode prev = null;

        while (fast != null && fast.next != null) {
            prev = slow;
            slow = slow.next;
            fast = fast.next.next;
        }

        if (prev != null)
            prev.next = null;

        ListNode reverseHead = reverseList(slow);

        int max = 0;
        while (head != null && reverseHead != null) {
            int sum = 0;

            sum = head.val + reverseHead.val;

            max = Math.max(max, sum);

            head = head.next;
            reverseHead = reverseHead.next;
        }

        return max;
    }

    private ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;

        while (curr != null) {
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }

        return prev;
    }
*/