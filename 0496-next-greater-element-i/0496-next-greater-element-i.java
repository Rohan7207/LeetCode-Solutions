// Problem : Next Greater Element I
// Link : https://leetcode.com/problems/next-greater-element-i/
// Difficulty : Easy

// Approach:
// Find the Next Greater Element for every number in nums2
// using a Monotonic Decreasing Stack.
// Maintain a stack such that:
//     elements are stored in decreasing order
// Traverse nums2:
//     For each current number:
//         While stack is not empty and
//         current number > stack top:
//             - Current number is the next greater element
//               for the stack top
//             - Store mapping:
//                   stackTop -> current number
//             - Pop stack top
//     Push current number into stack
// After traversal:
//     Any elements still present in stack
//     do not have a greater element on the right
//     Map them to -1
// Finally:
//     For every element in nums1,
//     directly fetch its next greater element
//     from the HashMap.

// Time Complexity: O(n + m)
// Space Complexity: O(n)
//
// where:
//     n = nums2.length
//     m = nums1.length


class Solution {
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        Map<Integer, Integer> map = new HashMap<>();
        Stack<Integer> s = new Stack<>();

        for (int num : nums2) {
            while (!s.isEmpty() && s.peek() < num) {
                map.put(s.pop(), num);
            }

            s.push(num);
        }

        while (!s.isEmpty()) {
            map.put(s.pop(), -1);
        }

        int[] ans = new int[nums1.length];
        for (int i = 0; i < nums1.length; i++) {
            ans[i] = map.get(nums1[i]);
        }

        return ans;
    }
}
