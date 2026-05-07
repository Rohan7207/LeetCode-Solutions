// Problem: Pascals Triangle
// Link: https://leetcode.com/problems/pascals-triangle/
// Difficulty: Easy

// Approach:
// Create a list to store all rows of Pascal's Triangle.
// Add the first row containing only [1].
// Traverse from row 1 to numRows - 1:
//     - Create a current row list.
//     - Get the previous row from result.
//     - Every row starts with 1, so add 1 first.
//     - For middle elements:
//         - Add adjacent values from previous row:
//           prev[j - 1] + prev[j]
//     - Every row ends with 1, so add 1 at the end.
//     - Add current row to result.
// Return the final result.

// Time Complexity: O(n^2)
// Space Complexity: O(n^2)


class Solution {
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> res = new ArrayList<>();
        res.add(new ArrayList<>());
        res.get(0).add(1); 

        for (int row = 1; row < numRows; row++) {
            List<Integer> curr = new ArrayList<>();
            List<Integer> prevList = res.get(row - 1); 

            curr.add(1); 
            for (int j = 1; j < row; j++) {
                curr.add(prevList.get(j - 1) + prevList.get(j)); 
            }
            curr.add(1); 

            res.add(curr);
        }

        return res;
    }
}
