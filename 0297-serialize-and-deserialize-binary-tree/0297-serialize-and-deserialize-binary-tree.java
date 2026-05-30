// Problem: Serialize and Deserialize Binary Tree
// Link: https://leetcode.com/problems/serialize-and-deserialize-binary-tree/
// Difficulty: Hard

// Approach:
// Use preorder traversal to serialize the tree.
// Store node values in Root → Left → Right order.
// Whenever a null node is found,
// store a special marker like "n".
//
// During deserialization:
//     - Split the string into tokens.
//     - Use a shared index to process tokens one by one.
//     - If current token is "n",
//       return null.
//     - Otherwise create a TreeNode.
//     - Recursively build its left subtree.
//     - Recursively build its right subtree.
//
// Null markers are important because they preserve
// the exact tree structure.

// Time Complexity: O(n)
// Space Complexity: O(n)


/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Codec {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder data = new StringBuilder();
        traversal(root, data);

        return data.toString();
    }

    public void traversal(TreeNode root, StringBuilder data) {
        if (root == null) {
            data.append("n ");
            return;
        }

        data.append(root.val + " ");
        traversal(root.left, data);
        traversal(root.right, data);
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data == null || data.isEmpty() || data.equals("n"))
            return null;

        String[] arr = data.split(" ");
        return build(arr, new int[] { 0 });
    }

    public TreeNode build(String[] arr, int[] idx) {
        if (idx[0] >= arr.length)
            return null;
        String val = arr[idx[0]++];

        if (val.equals("n"))
            return null;
        TreeNode root = new TreeNode(Integer.parseInt(val));
        root.left = build(arr, idx);
        root.right = build(arr, idx);

        return root;
    }
}

// Your Codec object will be instantiated and called as such:
// Codec ser = new Codec();
// Codec deser = new Codec();
// TreeNode ans = deser.deserialize(ser.serialize(root));
