// Problem: Diagonal Traverse II
// Link: https://leetcode.com/problems/diagonal-traverse-ii/
// Difficulty: Medium

// Approach:
// 1. Each element at position (i, j) belongs to a diagonal identified by i + j.
//    Example: (0,2), (1,1), (2,0) all belong to diagonal 2.
//
// 2. Use a HashMap<Integer, List<Integer>> to group elements having the same i + j.
//
// 3. Traverse the rows from top to bottom.
//    Therefore, inside each diagonal, elements are stored in top-to-bottom order.
//
// 4. The required diagonal traversal needs each diagonal from bottom to top,
//    so traverse every stored diagonal list backwards.
//
// 5. Process diagonals in increasing order of i + j: 0, 1, 2, ...
//    This gives the required overall diagonal order.
//
// 6. Count total elements beforehand so the result array can be created directly.

// Time Complexity: O(N), where N = total number of elements
// Space Complexity: O(N)


class Solution {
    public int[] findDiagonalOrder(List<List<Integer>> nums) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        int totalElements = 0;
        int maxSum = 0;

        // Group elements by their diagonal sum (i + j)
        for (int i = 0; i < nums.size(); i++) {
            List<Integer> row = nums.get(i);
            totalElements += row.size();

            for (int j = 0; j < row.size(); j++) {
                int sum = i + j;

                // If the sum list doesn't exist, create it; then add the value
                map.computeIfAbsent(sum, k -> new ArrayList<>()).add(row.get(j));

                maxSum = Math.max(maxSum, sum);
            }
        }

        // Build the final flat 1D array
        int[] res = new int[totalElements];
        int idx = 0;

        // Diagonals must be traversed from sum 0 up to maxSum
        for (int sum = 0; sum <= maxSum; sum++) {
            List<Integer> diagonal = map.get(sum);

            if (diagonal != null) {
                // Since we iterated rows from top to bottom (i = 0 to N),
                // the list contains elements from top to bottom.
                // We need bottom-to-top order, so we traverse the list backwards.
                for (int i = diagonal.size() - 1; i >= 0; i--) {
                    res[idx++] = diagonal.get(i);
                }
            }
        }

        return res;
    }
}
