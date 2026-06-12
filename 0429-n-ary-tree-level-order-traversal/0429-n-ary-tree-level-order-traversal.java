// Problem: N-ary Tree Level Order Traversal
// Link: https://leetcode.com/problems/n-ary-tree-level-order-traversal/
// Difficulty: Medium

// Approach:
// Perform Level Order Traversal of an N-ary tree.
// Level Order Traversal means:
//     Visit all nodes level by level
//     from top to bottom.
// Use a Queue for BFS traversal.
// First:
//     - Add root node into the queue
// While queue is not empty:
//     - Store queue size
//       This represents the number of nodes
//       present in the current level.
//     - Process exactly those nodes
//     - Store their values in a temporary
//       level list
//     - Push all their children into queue
// After processing all nodes of current level:
//     - Add level list to answer
// Repeat until queue becomes empty.

// Time Complexity: O(n)
// Space Complexity: O(n)


/*
// Definition for a Node.
class Node {
    public int val;
    public List<Node> children;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, List<Node> _children) {
        val = _val;
        children = _children;
    }
};
*/

class Solution {
    public List<List<Integer>> levelOrder(Node root) {
        List<List<Integer>> ans = new ArrayList<>();

        if (root == null) return ans;

        Queue<Node> q = new LinkedList<>();
        q.add(root);

        while (!q.isEmpty()) {
            int size = q.size();

            List<Integer> level = new ArrayList<>();

            for (int i = 0; i < size; i++) {
                Node curr = q.poll();

                level.add(curr.val);

                for (Node child : curr.children) {
                    q.add(child);
                }
            }

            ans.add(level);
        }

        return ans;
    }
}
