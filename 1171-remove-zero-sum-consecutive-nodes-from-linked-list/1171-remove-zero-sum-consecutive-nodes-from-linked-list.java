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
    public ListNode removeZeroSumSublists(ListNode head) {
        ListNode front = new ListNode(0, head);
        ListNode current = front;

        int prefixSum = 0;
        Map<Integer, ListNode> prefixSumToNode = new HashMap<>();
        prefixSumToNode.put(0, front);

        // Calculate the prefix sum for each node and add to the hashmap
        // Duplicate prefix sum values will be replaced
        while(current != null) {
            prefixSum += current.val;
            prefixSumToNode.put(prefixSum, current);

            current = current.next;
        }

        // Reset prefix sum and current
        prefixSum = 0;
        current = front;

        // Delete zero sum consecutive sequences 
        // by setting node before sequence to node after
        while(current != null) {
            prefixSum += current.val;

            current.next = prefixSumToNode.get(prefixSum).next;
            current = current.next;
        }

        return front.next;
    }
}

/*
    The crucial insight is that the prefix sum from the front node to node A will be equal to the sum from the front node to node B if and only if the sum from node A.next to node B is 0.
    
    https://leetcode.com/problems/remove-zero-sum-consecutive-nodes-from-linked-list/Figures/1171/1171ExampleA3.png$0
    The prefix sum at the end of this consecutive sequence, 5, is the same as the prefix sum before the sequence.

This makes sense; a zero-sum consecutive sequence will have a prefix sum of zero. The prefix sum before and at the end of the sequence will be the same. When we encounter a prefix sum that we have seen before, we have discovered a zero-sum consecutive sequence.

*/


/*
    Brute Force: O(n ^ 2) and O(1)
    ListNode front = new ListNode(0);
        front.next = head;
        ListNode start = front;

        while(start != null) {
            int prefixSum = 0;
            ListNode end = start.next;

            while(end != null) {
                // Add end's value to the prefix sum
                prefixSum += end.val;

                // Delete zero sum consecutive sequence by setting node before sequence to node after
                if(prefixSum == 0) {
                    start.next = end.next;
                }

                end = end.next;
            }

            start = start.next;
        }

        return front.next;
*/