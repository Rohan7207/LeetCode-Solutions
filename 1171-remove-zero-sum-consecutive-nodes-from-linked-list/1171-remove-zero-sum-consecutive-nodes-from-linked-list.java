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
        while (current != null) {
            prefixSum += current.val;
            prefixSumToNode.put(prefixSum, current);

            current = current.next;
        }

        // Reset prefix sum and current
        prefixSum = 0;
        current = front;

        // Delete zero sum consecutive sequences 
        // by setting node before sequence to node after
        while (current != null) {
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
    class Solution {
    public ListNode removeZeroSumSublists(ListNode head) {
        ListNode front = new ListNode(0, head);
        ListNode current = front;
        int prefixSum = 0;
        Map<Integer, ListNode> prefixSumToNode = new HashMap<>();
        while (current != null) {
            // Add current's value to the prefix sum
            prefixSum += current.val;

            // If prefixSum is already in  the hashmap, 
            // we have found a zero-sum sequence:
             if (prefixSumToNode.containsKey(prefixSum)) {
                ListNode prev = prefixSumToNode.get(prefixSum);
                current = prev.next;

                // Delete zero sum nodes from hashmap
                // to prevent incorrect deletions from linked list
                int p =  prefixSum + current.val;
                while (p != prefixSum) {
                    prefixSumToNode.remove(p); 
                    current = current.next;
                    p +=  current.val;
                }

                // Make connection from the node before 
                // the zero sum sequence to the node after
                prev.next = current.next;
            } else {
                // Add new prefixSum to hashmap
                prefixSumToNode.put(prefixSum, current);
            }
            // Progress to next element in list
            current = current.next;
        }
        return front.next;
    }
}

Although we use a nested while loop, the inner loop deletes nodes that are part of zero-sum sequences, and once a node is deleted, it will not be re-visited. We handle each node of the linked list at most twice, once to add it to the hash table and once to delete it. In the previous implementation, we were visiting each node exactly twice.
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