// Problem: Implement Stack Using Queues
// Link: https://leetcode.com/problems/implement-stack-using-queues/
// Difficulty: Easy

// Approach:
// Use two queues to simulate stack behavior (LIFO).
//
// push(x):
//     - Insert new element into q2.
//     - Move all elements from q1 to q2.
//     - Swap q1 and q2.
//
// After swapping:
//     - Newly inserted element becomes
//       the front of q1.
//     - q1 always maintains stack order.
//
// pop():
//     - Remove and return front element of q1.
//
// top():
//     - Return front element of q1.
//
// empty():
//     - Check whether q1 is empty.

// Time Complexity:
//     push  : O(n)
//     pop   : O(1)
//     top   : O(1)
//     empty : O(1)
// Space Complexity: O(n)


class MyStack {
    Queue<Integer> q1, q2;

    public MyStack() {
        q1 = new LinkedList<>();
        q2 = new LinkedList<>();
    }

    public void push(int x) {
        q2.offer(x);

        while (!q1.isEmpty()) {
            q2.offer(q1.poll());
        }

        Queue<Integer> temp = q1;
        q1 = q2;
        q2 = temp;
    }

    public int pop() {
        return q1.poll();
    }

    public int top() {
        return q1.peek();
    }

    public boolean empty() {
        return q1.isEmpty();
    }
}

/**
 * Your MyStack object will be instantiated and called as such:
 * MyStack obj = new MyStack();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.top();
 * boolean param_4 = obj.empty();
 */
