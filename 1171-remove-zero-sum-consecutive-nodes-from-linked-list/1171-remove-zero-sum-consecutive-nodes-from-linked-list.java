// Problem: Remove Zero Sum Consecutive Nodes from Linked List
// Link: https://leetcode.com/problems/remove-zero-sum-consecutive-nodes-from-linked-list/
// Difficulty: Medium

// Approach:
// Use Prefix Sum + HashMap to remove every consecutive sequence
// whose sum is 0.
//
// 1. Create a dummy node `front` before the actual head.
//    This makes it possible to remove a zero-sum sequence that
//    starts from the head.
//
// 2. First traversal:
//      - Calculate the prefix sum up to every node.
//      - Store:
//            prefixSum → latest node having that prefix sum
//
// 3. Why does the repeated prefix sum help?
//
//      If two nodes have the same prefix sum:
//
//          prefixSum at A = prefixSum at B
//
//      then the sum of nodes between A and B is 0.
//
// 4. Store the LAST node for each prefix sum.
//    This is important because it allows us to remove the largest
//    zero-sum sequence possible from that starting point.
//
// 5. Second traversal:
//      - Calculate prefix sum again.
//      - Find the last node having the same prefix sum.
//      - Skip everything between the current node and that node:
//
//            current.next = map.get(prefixSum).next
//
// 6. Return `front.next`.

// Time Complexity: O(n)
// Space Complexity: O(n)


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
