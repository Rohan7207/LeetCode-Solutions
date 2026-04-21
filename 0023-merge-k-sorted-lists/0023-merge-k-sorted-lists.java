// Problem: Merge k Sorted Lists
// Link: https://leetcode.com/problems/merge-k-sorted-lists/
// Difficulty: Hard

// Approach:
// Use a min-heap (priority queue) to efficiently merge k sorted lists.
// Add the head of each list to the heap.
// While the heap is not empty:
//   - Extract the smallest node from the heap.
//   - Add it to the result list.
//   - If the extracted node has a next node, add it to the heap.
// Continue until all nodes are processed.
// Return dummy.next as the head of the merged list.

// Time Complexity: O(N log k)
// Space Complexity: O(k)

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
    public ListNode mergeKLists(ListNode[] lists) {
        PriorityQueue<ListNode> pq = new PriorityQueue<>(
            (a,b) -> a.val - b.val
        );

        // Add all the heads
        for(ListNode node : lists) {
            if(node != null) {
                pq.add(node);
            }
        }

        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;

        // Process the heap and add sorted lists
        while(!pq.isEmpty()) {
            ListNode minNode = pq.poll();
            tail.next = minNode;
            tail = tail.next;

            if(minNode.next != null) {
                pq.add(minNode.next);
            }
        }

        
        return dummy.next;
    }
}
