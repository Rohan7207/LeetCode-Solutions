// Problem: Pascals Triangle II
// Link: https://leetcode.com/problems/pascals-triangle-ii/
// Difficulty: Easy

// Approach:
// Use the mathematical relation of Pascal's Triangle
// to directly compute elements of a row.
// Start with first element as 1.
// For each next element:
//     - Use previous element to calculate current element
//       using combination formula.
//     - Store current value in result list.
// Continue until all elements of the row are generated.
// Return the result list.

// Time Complexity: O(n)
// Space Complexity: O(n)


class Solution {
    public List<Integer> getRow(int rowIndex) {
        List<Integer> res = new ArrayList<>();
        res.add(1);
        long prev = 1;

        for(int i = 1; i <= rowIndex; i++) {
            long next_val = prev * (rowIndex - i + 1) / i;
            res.add((int)next_val);
            prev = next_val;
        }

        return res;
    }
}
