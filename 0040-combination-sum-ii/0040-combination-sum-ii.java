// Problem: Combination Sum II
// Link: https://leetcode.com/problems/combination-sum-ii/
// Difficulty: Medium

// Approach:
// Sort the array to handle duplicates efficiently.
// Use backtracking to generate unique combinations.
// Iterate through the candidates starting from the current index:
//     - Skip duplicate elements to avoid repeated combinations.
//     - Add the current element to the combination.
//     - Recursively call the function for the remaining target
//       using the next index since each element can be used only once.
//     - Backtrack by removing the last added element.
// If the target becomes 0, add the current combination to the result.
// If the target becomes negative, stop exploring that path.

// Time Complexity: Exponential
// Space Complexity: O(n)


class Solution {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(candidates); //Sort array to handle duplicates
        backtrack(res, candidates, target, new ArrayList<>(), 0);
        return res;
    }

    private void backtrack(List<List<Integer>> res, int[] candidates, int target, List<Integer> currentCom, int start) {
        if (target == 0) {
            res.add(new ArrayList<>(currentCom));
        }

        for (int i = start; i < candidates.length; i++) {
            //Skip duplicates
            if (i > start && candidates[i] == candidates[i - 1]) {
                continue;
            }
            //Terminate if remaining sum becomes negative
            if (target - candidates[i] < 0) {
                break;
            }
            currentCom.add(candidates[i]);
            backtrack(res, candidates, target - candidates[i], currentCom, i + 1);
            currentCom.remove(currentCom.size() - 1);
        }
    }
}
