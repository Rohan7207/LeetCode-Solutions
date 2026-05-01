// Problem: Subsets
// Link: https://leetcode.com/problems/subsets/
// Difficulty: Medium

// Approach:
// Use backtracking to generate all possible subsets.
// Add the current subset to the result at every recursion call.
// Iterate through the array starting from the current index:
//     - Include the current element in the subset.
//     - Recursively generate subsets for remaining elements.
//     - Backtrack by removing the last added element.
// Continue until all combinations are explored.

// Time Complexity: O(n * 2^n)
// Space Complexity: O(n) // recursion stack


class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        helper(nums, 0, new ArrayList<>(), res);
        return res;
    }

    private void helper(int[] nums, int idx, List<Integer> current, List<List<Integer>> res) {
        res.add(new ArrayList<>(current)); 

        for (int i = idx; i < nums.length; i++) {
            current.add(nums[i]);
            helper(nums, i + 1, current, res);
            current.remove(current.size() - 1); 
        }
    }
}
