/*
// Definition for a Node.
class Node {
    public int val;
    public Node left;
    public Node right;
    public Node next;

    public Node() {}
    
    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, Node _left, Node _right, Node _next) {
        val = _val;
        left = _left;
        right = _right;
        next = _next;
    }
};
*/

class Solution {
    public Node connect(Node root) {
        if(root == null) return null;

        Node level = root;

        while(level.left != null) {
            Node curr = level;   

            while(curr != null) {
                curr.left.next = curr.right;

                if(curr.next != null) {
                    curr.right.next = curr.next.left;
                }
                curr = curr.next;
            }

            level = level.left;
        }

        return root;
    }
}

/*
    // Optimal approach where time is O(n) and O(1)
    // Start with the root. The level below root will be connected first.
    // Outer loop: Move vertically down the left side of the tree
        // Start horizontal traversal for current level
        // Inner loop: Move horizontally across the current level
            // 1. Connect children of the same parent (Direct connection)
            // 2. Connect the right child to the neighbor's left child (Bridge)
            // Move to the next node in the same level
        // Move to the next level down
*/

/*
    O(n) and O(n)
        if(root == null) return null;

        Queue<Node> q = new LinkedList<>();
        q.offer(root);

        while(!q.isEmpty()) {
            int size = q.size();
            
            for(int i = 0; i < size; i++) {
                Node currNode = q.poll();
                if(i < size - 1) {
                    currNode.next = q.peek();
                } else{ 
                    currNode.next = null;
                }
            
                if(currNode.left != null) q.offer(currNode.left);
                if(currNode.right != null) q.offer(currNode.right);
            }
        }

        return root;
*/