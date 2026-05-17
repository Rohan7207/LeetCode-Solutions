// Problem: Min Stack
// Link: https://leetcode.com/problems/min-stack/
// Difficulty: Medium

// Approach:
// Design a stack that supports retrieving the minimum element in O(1) time.
// Instead of maintaining a separate min stack,
// each node stores:
//     - current value
//     - minimum value up to that point in the stack
//     - reference to next node
// For every push operation:
//     - If stack is empty:
//           min = val
//     - Else:
//           min = Math.min(val, current head min)
//     - Create a new node and make it the new head
// For pop operation:
//     - Move head to head.next (removes top element)
//
// For top operation:
//     - Return head.val
//
// For getMin operation:
//     - Return head.min (already stored at top node)
//
// This works because each node remembers the minimum
// value from itself to the bottom of the stack.

// Time Complexity:
//     push  -> O(1)
//     pop   -> O(1)
//     top   -> O(1)
//     getMin-> O(1)
// Space Complexity: O(n)


class MinStack {
    private Node head;

    MinStack() {
    }
    
    public void push(int val) {
       if(head == null) {
            head = new Node(val, val, null);
        } else{
            head = new Node(val, Math.min(val, head.min), head);
        } 
    }
    
    public void pop() {
        head = head.next;  
    }
    
    public int top() {
        return head.val;
    }
    
    public int getMin() {
        return head.min;
    }
}

class Node{
    int val;
    int min;
    Node next;

    Node(int val, int min, Node next){
        this.val = val;
        this.min = min;
        this.next = next;
    }
}

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(val);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */
