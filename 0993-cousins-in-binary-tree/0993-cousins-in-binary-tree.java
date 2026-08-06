// Problem: Cousins in Binary Tree
// Link: https://leetcode.com/problems/cousins-in-binary-tree/
// Difficulty: Easy

// Approach:
// Perform a level-order traversal (BFS) because cousins must
// appear at the same depth.
//
// For each level:
//
// 1. Initialize two flags:
//      foundX = false
//      foundY = false
//
// 2. Traverse every node in the current level.
//
// 3. For each node:
//    - If the current node's value is x or y, mark the
//      corresponding flag.
//
//    - Check whether its left and right children are x and y.
//      If both children are x and y, they share the same parent
//      (siblings), so immediately return false.
//
//    - Push the left and right children (if they exist) into
//      the queue for the next level.
//
// 4. After processing the entire level:
//
//    - If both x and y were found, they are at the same depth
//      and were already verified not to be siblings, so return
//      true.
//
//    - If only one of them was found, they are at different
//      depths, so return false.
//
// Continue until all levels are processed.
//
// If neither condition is met during the traversal,
// return false.

// Time Complexity: O(n)
// Space Complexity: O(n)


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
    public boolean isCousins(TreeNode root, int x, int y) {
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);

        while (!q.isEmpty()) {
            int size = q.size();

            boolean foundX = false;
            boolean foundY = false;

            for (int i = 0; i < size; i++) {
                TreeNode curr = q.poll();

                if (curr.val == x) {
                    foundX = true;
                }

                if (curr.val == y) {
                    foundY = true;
                }

                // Check if Children are Siblings
                if (curr.left != null && curr.right != null) {
                    if ((curr.left.val == x && curr.right.val == y) || (curr.right.val == x && curr.left.val == y)) {
                        return false;
                    }
                }

                // Check Whether x or y Exists in This Level
                if (curr.left != null) {
                    q.offer(curr.left);
                }

                if (curr.right != null) {
                    q.offer(curr.right);
                }
            }

            if (foundX && foundY) {
                return true;
            }

            if (foundX || foundY) {
                return false;
            }
        }

        return false;
    }
}
