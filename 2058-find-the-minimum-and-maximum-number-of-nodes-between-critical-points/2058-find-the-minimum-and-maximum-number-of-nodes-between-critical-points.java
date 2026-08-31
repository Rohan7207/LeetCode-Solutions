// Problem: Find the Minimum and Maximum Number of Nodes Between Critical Points
// Link: https://leetcode.com/problems/find-the-minimum-and-maximum-number-of-nodes-between-critical-points/?envType=daily-question&envId=2026-08-31
// Difficulty: Medium

// Approach:
// Use Linked List Traversal + Critical Point Tracking.
//
// 1. A critical point is a node that is either:
//      - greater than both its neighbors (local maximum), or
//      - smaller than both its neighbors (local minimum).
//
// 2. Traverse the linked list using three nodes:
//      prev → curr → next
//
// 3. Maintain the position of every node using `pos`.
//
// 4. When a critical point is found:
//      - For the first critical point, store its position in `first`.
//      - For every next critical point, calculate the distance from
//        the previous critical point using `pos - temp`.
//      - Update `min` with the smallest distance.
//      - Update `temp` to the current critical point.
//
// 5. The maximum distance is simply the distance between the first
//    and last critical points:
//
//      max = last - first
//
// 6. If fewer than two critical points exist, `last` remains -1,
//    so return {-1, -1}.

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
