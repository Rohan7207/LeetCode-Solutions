// Problem: Maximum Depth of N-ary Tree
// Link: https://leetcode.com/problems/maximum-depth-of-n-ary-tree/
// Difficulty: Easy

// Approach:
// Find the maximum depth of an N-ary tree.
// Maximum depth is the number of nodes
// along the longest path from root to leaf.
// Use DFS recursion.
// For every node:
//     - Recursively calculate the depth
//       of each child
//     - Among all child depths,
//       keep the maximum depth
//     - Add 1 for the current node
// Base Case:
//     - If node is null:
//           return 0
// For a leaf node:
//     - No children exist
//     - Depth remains 1
// Finally return the maximum depth found.

// Time Complexity: O(n)
// Space Complexity: O(h)


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
    public int maxDepth(Node root) {
        if(root == null) return 0;

        int max = 1;

        for(Node child : root.children) {
            max = Math.max(max, 1 + maxDepth(child));
        }

        return max;
    }
}
