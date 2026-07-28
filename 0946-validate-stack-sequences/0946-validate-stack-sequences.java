// Problem: Validate Stack Sequences
// Link: https://leetcode.com/problems/validate-stack-sequences/
// Difficulty: Medium

// Approach:
// Simulate the push and pop operations using an actual stack.
//
// Step 1:
// Initialize an empty stack.
//
// Maintain:
//
// - i -> points to the next element to be pushed.
// - j -> points to the next element expected to be popped.
//
// Step 2:
// Traverse the pushed array.
//
// For every element:
//
// - Push pushed[i] onto the stack.
// - Increment i.
//
// Step 3:
// After every push, repeatedly check:
//
// - Is the stack non-empty?
// - Does the top of the stack equal popped[j]?
//
// If yes:
//
// - Pop the top element.
// - Move j to the next expected popped element.
//
// Keep popping until the top no longer matches.
//
// Step 4:
// Continue until all elements from the pushed array have been processed.
//
// Step 5:
// If the stack becomes empty at the end, every push and pop operation
// matched the given sequences, so return true.
// Otherwise, return false.

// Time Complexity:
// O(n)
// (Every element is pushed once and popped at most once.)
// Space Complexity:
// O(n)
// (Stack may contain all elements in the worst case.)


class Solution {
    public boolean validateStackSequences(int[] pushed, int[] popped) {
        Stack<Integer> s = new Stack<>();

        int i = 0, j = 0;

        while (i < pushed.length && j < popped.length) {
            s.push(pushed[i]);
            i++;

            while (!s.isEmpty() && s.peek() == popped[j]) {
                s.pop();
                j++;
            }
        }

        return s.isEmpty();
    }
}
