// Problem: Subsets II
// Link: https://leetcode.com/problems/subsets-ii/
// Difficulty: Medium

// Approach:
// Sort the array so duplicate elements
// become adjacent.
// Use backtracking to generate all subsets.
// Add the current subset to the result
// at every recursion call.
// Iterate from the current start index:
//     - Skip duplicate elements at the same
//       recursion level.
//     - Include the current element in subset.
//     - Recursively generate subsets
//       for remaining elements.
//     - Backtrack by removing the last element.
// Return all unique subsets.

// Time Complexity: O(n * 2^n)
// Space Complexity: O(n) // recursion stack


class Solution {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        // Sort array to handle duplicates
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums); 
        helper(0, nums, new ArrayList<>(), res);
        return res;
    }

    private void helper(int start, int[] nums, List<Integer> current, List<List<Integer>> res) {
        res.add(new ArrayList<>(current));
        for (int i = start; i < nums.length; i++) {
            //Skip the duplicate
            if (i > start && nums[i] == nums[i - 1]) {
                continue;
            }

            current.add(nums[i]);
            helper(i + 1, nums, current, res);
            current.remove(current.size() - 1);
        }
    }
}
