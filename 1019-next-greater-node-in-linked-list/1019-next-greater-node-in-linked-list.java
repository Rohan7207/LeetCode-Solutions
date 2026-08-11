// Problem: Next Greater Node in Linked List
// Link: https://leetcode.com/problems/next-greater-node-in-linked-list/
// Difficulty: Medium

// Approach:
// Convert the linked list into an ArrayList so that we can access
// every node using an index.
//
// Use a monotonic decreasing stack to find the next greater value.
//
// 1. Traverse the linked list and store all node values in `values`.
//
// 2. Create an answer array initialized with 0.
//    If no greater node exists for a position, its answer remains 0.
//
// 3. Traverse the values from left to right.
//
// 4. Store indices in the stack whose next greater value has not
//    been found yet.
//
// 5. When the current value is greater than the value at the index
//    on top of the stack:
//
//       values.get(s.peek()) < values.get(i)
//
//    the current value is the first greater value for that index.
//
// 6. Set the answer for that index and remove it from the stack.
//
// 7. Push the current index into the stack because it may need
//    a greater value later.
//
// 8. Any indices remaining in the stack have no greater value
//    to their right, so their answer stays 0.

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
    public int[] nextLargerNodes(ListNode head) {
        List<Integer> values = new ArrayList<>();
        
        while (head != null) {
            values.add(head.val);
            head = head.next;
        }

        int n = values.size();
        int[] ans = new int[n];
        Stack<Integer> s = new Stack<>();

        for (int i = 0; i < n; i++) {
            while (!s.isEmpty() && values.get(s.peek()) < values.get(i)) {
                ans[s.peek()] = values.get(i);
                s.pop();
            }

            s.push(i);
        }

        return ans;
    }
}
