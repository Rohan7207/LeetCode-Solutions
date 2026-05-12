// Problem: Clone Graph
// Link: https://leetcode.com/problems/clone-graph/
// Difficulty: Medium

// Approach:
// Use DFS traversal with a HashMap to create
// a deep copy of the graph.
// Maintain a map:
//     original node -> cloned node
// For every node:
//     - If node is null return null.
//     - If node is already cloned,
//       return its cloned copy from map.
//     - Create a clone node.
//     - Store it in map before recursion
//       to avoid infinite cycles.
//     - Recursively clone all neighbors
//       and attach them to cloned node.
// Return the cloned graph node.

// Time Complexity: O(V + E)
// Space Complexity: O(V)
//
// V = number of vertices
// E = number of edges


/*
// Definition for a Node.
class Node {
    public int val;
    public List<Node> neighbors;
    public Node() {
        val = 0;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val) {
        val = _val;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val, ArrayList<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
}
*/

class Solution {
    HashMap<Node, Node> vis = new HashMap<>();

    public Node cloneGraph(Node node) {
        if (node == null) return node;

        if (vis.containsKey(node)) {
            return vis.get(node);
        }

        Node cloneNode = new Node(node.val, new ArrayList());
        vis.put(node, cloneNode);

        for (Node neigh : node.neighbors) {
            cloneNode.neighbors.add(cloneGraph(neigh));
        }

        return cloneNode;
    }
}
