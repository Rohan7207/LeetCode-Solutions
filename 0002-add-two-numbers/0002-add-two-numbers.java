// Problem: Add Two Numbers
// Link: https://leetcode.com/problems/add-two-numbers/
// Difficulty: Medium

// Approach:
// 1. Use two pointers to traverse both linked lists.
// 2. Add corresponding digits along with carry.
// 3. Create a new linked list to store the result.
// 4. Continue until both lists and carry are processed.

// Time Complexity:
// O(max(n, m)) — where n and m are lengths of the lists

// Space Complexity:
// O(max(n, m)) — for storing the result list

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
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;
        int carry = 0;

        while(l1 != null || l2 != null){
            int x = (l1 != null) ? l1.val : 0;
            int y = (l2 != null) ? l2.val : 0;

            int sum = carry + x + y;
            carry = sum / 10;
            tail.next = new ListNode(sum % 10);
            tail = tail.next;
            if(l1 != null) l1 = l1.next;
            if(l2 != null) l2 = l2.next;
        }

        if(carry > 0){
            tail.next = new ListNode(carry);
        }

        return dummy.next;
    }
}    
