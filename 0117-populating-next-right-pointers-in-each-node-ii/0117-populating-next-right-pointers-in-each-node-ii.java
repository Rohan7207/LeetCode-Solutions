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
        if(root == null) 
            return null;

        Node level = root;

        while(level != null) {
            Node dummy = new Node(0);
            Node chain = dummy;

            Node curr = level;
            while(curr != null) {
                if(curr.left != null) {
                    chain.next = curr.left;
                    chain = chain.next;
                } 

                if(curr.right != null) {
                    chain.next = curr.right;
                    chain = chain.next;
                }

                curr = curr.next;
            }
            level = dummy.next;
        }

        return root;
    }
}

/*
    Key Components Explained
Node dummy = new Node(0): This is a placeholder. It ensures we don't have to write "if-else" logic to find the very first child of the next level. The first child found will always be dummy.next.
Node chain = dummy: This is our "writing" pointer. It stays at the end of the new linked list we are building for the level below.
level = dummy.next: This effectively "drops" us down to the start of the level we just finished connecting.

*/

/*
The Strategy: The "Tail" Method
    Think of the dummy node as a "starting peg" and chain as your "tail" pointer. As you walk across the level above, you use chain to stitch together whatever children you find (left or right) into a new linked list.X 
    //O(N) and O(N) with 41.97%
        if(root == null) return null;

        Queue<Node> q = new LinkedList<>();
        q.offer(root);

        while(!q.isEmpty()) {
            int size = q.size();

            for(int i = 0; i < size; i++) {
                Node currNode = q.poll();
                if(i < size - 1) {
                    currNode.next = q.peek();
                } else {
                    currNode.next = null;
                }

                if(currNode.left != null) q.offer(currNode.left);
                if(currNode.right != null) q.offer(currNode.right);
            }
        }

        return root;
*/