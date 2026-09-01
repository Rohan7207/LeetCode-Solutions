// Problem: All Elements in Two Binary Search Trees
// Link: https://leetcode.com/problems/all-elements-in-two-binary-search-trees/
// Difficulty: Medium

// Approach:
// Use Inorder Traversal + Two Pointer Merge.
//
// 1. Perform inorder traversal on both BSTs.
//
// 2. Inorder traversal of a BST gives its elements in sorted order:
//
//      root1 → list1 (sorted)
//      root2 → list2 (sorted)
//
// 3. Maintain two pointers:
//      i → list1
//      j → list2
//
// 4. Compare the current elements of both lists.
//
//      list1[i] <= list2[j]
//          → add list1[i]
//          → move i
//
//      otherwise
//          → add list2[j]
//          → move j
//
// 5. Once one list is exhausted, add all remaining elements
//    from the other list.
//
// 6. Return the merged sorted list.

// Time Complexity: O(n + m)
// Space Complexity: O(n + m)


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
    public List<Integer> getAllElements(TreeNode root1, TreeNode root2) {
        List<Integer> list1 = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();

        helper(root1, list1);
        helper(root2, list2);

        int i = 0, j = 0;
        List<Integer> ans = new ArrayList<>();

        while (i < list1.size() && j < list2.size()) {
            if (list1.get(i) <= list2.get(j)) {
                ans.add(list1.get(i));
                i++;
            } else {
                ans.add(list2.get(j));
                j++;
            }
        }

        while (i < list1.size()) {
            ans.add(list1.get(i));
            i++;
        }

        while (j < list2.size()) {
            ans.add(list2.get(j));
            j++;
        }


        return ans;
    }

    private void helper(TreeNode root, List<Integer> list) {
        if (root == null) {
            return;
        }

        helper(root.left, list);
        list.add(root.val);
        helper(root.right, list);
    }
}
