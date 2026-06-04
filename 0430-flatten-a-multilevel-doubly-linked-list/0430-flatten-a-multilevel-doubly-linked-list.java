/*
// Definition for a Node.
class Node {
    public int val;
    public Node prev;
    public Node next;
    public Node child;
};
*/

class Solution {
    public Node flatten(Node head) {
        return solution_dfs(head);
    }

    private Node solution_dfs(Node head) {
        Node dummy = new Node(-1, null, null, null);
        dfs(dummy, head);
        Node res = dummy.next;
        if (res != null) {
            res.prev = null;
        }

        return res;
    }

    private Node dfs(Node pre, Node cur) {
        if (cur == null) {
            return pre;
        }

        pre.next = cur;
        cur.prev = pre;

        Node child = cur.child;
        cur.child = null;

        Node next = cur.next;

        Node tail = dfs(cur, child);

        return dfs(tail, next);
    }
}

// Another iterative dfs using stack for above code
/*
    // Time: O(N)
    // Space: O(N)
    private Node solution_stack(Node head) {
        Node dummy = new Node(-1, null, null, null);
        Node pre = dummy;
        Node cur = head;
        Stack<Node> stack = new Stack<>();
        while (cur != null || !stack.isEmpty()) {
            if (cur == null) {
                cur = stack.pop();
                continue;
            }

            pre.next = cur;
            cur.prev = pre;

            if (cur.child != null) {
                stack.push(cur.next);
                pre = cur;
                cur = cur.child;
                pre.child = null;
            } else {
                pre = cur;
                cur = cur.next;
            }
        }
        
        Node res = dummy.next;
        if (res != null) {
            res.prev = null;
        }
        
        return res;
    }
*/

/*
// Definition for a Node.
class Node {
    public int val;
    public Node prev;
    public Node next;
    public Node child;
};


class Solution {
    public Node flatten(Node head) {
        if (head == null) {
            return null;
        }

        Node curr = head;

        while (curr != null) {
            if (curr.child != null) {
                Node next = curr.next;
                curr.next = flatten(curr.child);

                curr.next.prev = curr;
                curr.child = null;

                while (curr.next != null) {
                    curr = curr.next;
                }

                if (next != null) {
                    curr.next = next;
                    next.prev = curr;
                }
            }
            curr = curr.next;
        }
        return head;
    }
}

*/