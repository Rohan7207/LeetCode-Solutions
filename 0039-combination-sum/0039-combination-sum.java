// Problem: Combination Sum
// Link: https://leetcode.com/problems/combination-sum/
// Diffoculty: Medium

// Approach:
// Use backtracking to generate all possible combinations.
// Start from a given index to avoid duplicate combinations.
// For each candidate:
//     - Add the candidate to the current combination.
//     - Recursively call the function with
//       target - candidate value.
//     - Reuse the same index since elements can be chosen multiple times.
//     - Backtrack by removing the last added element.
// If target becomes 0, add the current combination to the result.
// If target becomes negative, stop exploring that path.

// Time Complexity: Exponential (depends on number of combinations)
// Space Complexity: O(target)   // recursion stack

class Solution {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        backtrack(res, candidates, target, new ArrayList<>(), 0);
        return res;
    }

    private void backtrack(List<List<Integer>> res, int[] candidates, int target, List<Integer> current, int start) {
        if (target == 0) {
            res.add(new ArrayList<>(current));
        } else if (target < 0) {
            return;
        }

        for (int i = start; i < candidates.length; i++) {
            current.add(candidates[i]);
            backtrack(res, candidates, target - candidates[i], current, i);
            current.remove(current.size() - 1);
        }
    }
}
