// Problem: Find the Duplicate Number
// Link: https://leetcode.com/problems/find-the-duplicate-number/
// Difficulty: Medium

// Approach:
// Treat the array like a linked list where:
//     index -> node
//     nums[index] -> next pointer
// Since one number is duplicated,
// multiple indices point to the same node,
// creating a cycle.
// Use Floyd’s Cycle Detection Algorithm:
// Phase 1:
//     - Move slow pointer one step.
//     - Move fast pointer two steps.
//     - Find intersection point inside cycle.
// Phase 2:
//     - Reset slow to start.
//     - Move both pointers one step.
//     - The point where they meet again
//       is the duplicate number.

// Time Complexity: O(n)
// Space Complexity: O(1)


class Solution {
    public int findDuplicate(int[] nums) {
        int slow = nums[0];
        int fast = nums[nums[0]];

        while (slow != fast) {
            slow = nums[slow];
            fast = nums[nums[fast]];
        }

        slow = 0;
        while (slow != fast) {
            slow = nums[slow];
            fast = nums[fast];
        }

        return slow;
    }
}
