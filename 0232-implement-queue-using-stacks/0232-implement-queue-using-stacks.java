// Problem: Implement Queue Using Stacks
// Link: https://leetcode.com/problems/implement-queue-using-stacks/
// Difficulty: Easy

// Approach:
// Use two stacks to simulate FIFO behavior.
// push(x):
//     - Move all elements from s1 to s2.
//     - Push the new element into s1.
//     - Move all elements back from s2 to s1.
// After rearrangement:
//     - Oldest element remains at the top of s1.
//     - Front of queue is always on top of s1.
//
// pop():
//     - Remove and return top element of s1.
//
// peek():
//     - Return top element of s1.
//
// empty():
//     - Check whether s1 is empty.

// Time Complexity:
//     push  : O(n)
//     pop   : O(1)
//     peek  : O(1)
//     empty : O(1)
// Space Complexity: O(n)


class MyQueue {
    Stack<Integer> s1, s2;

    public MyQueue() {
        s1 = new Stack<>();
        s2 = new Stack<>();
    }

    public void push(int x) {
        while (!s1.empty()) {
            s2.push(s1.pop());
        }

        s1.push(x);

        while (!s2.empty()) {
            s1.push(s2.pop());
        }
    }

    public int pop() {
        return s1.pop();
    }

    public int peek() {
        return s1.peek();
    }

    public boolean empty() {
        return s1.isEmpty();
    }
}

/**
 * Your MyQueue object will be instantiated and called as such:
 * MyQueue obj = new MyQueue();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.peek();
 * boolean param_4 = obj.empty();
 */
