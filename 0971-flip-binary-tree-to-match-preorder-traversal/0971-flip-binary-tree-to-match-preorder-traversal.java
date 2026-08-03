// Problem: Flip Binary Tree To Match Preorder Traversal
// Link: https://leetcode.com/problems/flip-binary-tree-to-match-preorder-traversal/
// Difficulty: Medium

// Approach:
// Simulate the preorder traversal of the binary tree while
// simultaneously matching it with the given voyage array.
//
// Maintain a global index that always points to the next
// expected value in the voyage.
//
// During DFS:
//
// 1. If the current node is null, simply return.
//
// 2. If the current node's value does not match
//    voyage[index], then it is impossible to make the
//    preorder match even after flips.
//    Mark the traversal as impossible.
//
// 3. Otherwise, increment index since this node has been
//    successfully matched.
//
// 4. Normally, preorder visits:
//          Root → Left → Right
//
//    Before visiting the left child, check whether its value
//    matches the next expected value in voyage.
//
//    - If it matches, continue with the normal preorder.
//
//    - Otherwise, the left and right subtrees must be flipped.
//      Record the current node's value in the answer list and
//      traverse:
//          Root → Right → Left
//
// Since every node is visited only once, this greedy decision
// always produces the minimum number of flips.
//
// After DFS:
//
// - If a mismatch occurred at any point, return [-1].
// - Otherwise, return the recorded flipped node values.

// Time Complexity:
// O(n)
//
// Space Complexity:
// O(h)
// where h is the height of the tree (recursion stack),
// excluding the output list.


/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {

    private List<Integer> ans = new ArrayList<>();
    private int index = 0;
    private boolean possible = true;

    public List<Integer> flipMatchVoyage(TreeNode root, int[] voyage) {
        dfs(root, voyage);

        if (!possible) {
            return Arrays.asList(-1);
        }

        return ans;
    }

    private void dfs(TreeNode root, int[] voyage) {
        if (root == null) {
            return;
        }

        // Current node doesn't match preorder
        if (root.val != voyage[index]) {
            possible = false;
            return;
        }

        index++;

        // Need to flip if the next expected node is not the left child
        if (root.left != null && index < voyage.length && root.left.val != voyage[index]) {
            ans.add(root.val);
            dfs(root.right, voyage);
            dfs(root.left, voyage);
        } else {
            dfs(root.left, voyage);
            dfs(root.right, voyage);
        }
    }
}
