// Problem : Next Greater Element II
// Link : https://leetcode.com/problems/next-greater-element-ii/
// Difficulty : Medium

// Approach:
// Find Next Greater Element in a circular array.
// Since the array is circular,
// each element can search for next greater element
// even after the last index by wrapping around.
// To simulate circular traversal:
//     - Traverse from 2*n - 1 down to 0
//     - Use i % n to access original array index
// Use a monotonic decreasing stack.
// Stack stores possible next greater elements
// for the current number.
// For every index from right to left:
//     - Remove all elements from stack
//       that are smaller than or equal to current number
//     - After popping:
//           if stack is empty:
//               no greater element exists -> answer is -1
//           else:
//               stack top is the next greater element
//
//     - Push current number into stack
// Finally return answer array.

// Time Complexity: O(n)
// Space Complexity: O(n)


class Solution {
    public int[] nextGreaterElements(int[] nums) {
        int n = nums.length;
        int[] ans = new int[n];
        Deque<Integer> st = new ArrayDeque<>();
        
        for (int i = n * 2 - 1; i >= 0; i--) {
            while (!st.isEmpty() && st.peek() <= nums[i % n]) {
                st.pop();
            }

            ans[i % n] = st.isEmpty() ? -1 : st.peek();
            st.push(nums[i % n]);
        }

        return ans;
    }
}
