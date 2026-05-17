// Problem: Intersection of Two Linked Lists
// Link: https://leetcode.com/problems/intersection-of-two-linked-lists/
// Difficulty: Easy

// Approach:
// Find the intersection node of two singly linked lists
// using a two-pointer switching technique.
// Use two pointers:
//     - temp1 starts at headA
//     - temp2 starts at headB
// Traverse both lists simultaneously:
//     - Move each pointer one step at a time
//     - If a pointer reaches null, redirect it to the head of the other list
// Logic behind switching:
//     - Both pointers travel equal total distance:
//           (length of A + length of B)
//     - If an intersection exists, they will meet at the intersection node
//     - If not, both will eventually become null at the same time
// Continue until temp1 == temp2 (either at intersection node or null)
// Return temp1 (intersection node or null if no intersection)

// Time Complexity: O(m + n)
// Space Complexity: O(1)


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
