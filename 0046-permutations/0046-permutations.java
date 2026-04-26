// Problem: Permutations
// Link: https://leetcode.com/problems/permutations/
// Difficulty: Medium

// Approach:
// Use backtracking with swapping to generate all permutations.
// Fix one position at a time starting from index i.
// For every index from i to end:
//     - Swap the current element with index i
//       to place a new number at the current position.
//     - Recursively generate permutations for the next index.
//     - Backtrack by swapping again to restore the original array.
// When all positions are fixed, add the current permutation to the result.

// Time Complexity: O(n * n!)
// Space Complexity: O(n)

class Solution {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        permuteHelper(nums, 0, res);
        return res;
    }

    private void permuteHelper(int[] nums, int i, List<List<Integer>> res) {
        if(i == nums.length) {
            List<Integer> permutation = new ArrayList<>();
            for(int num : nums) {
                permutation.add(num);
            }
            res.add(permutation);
            return;
        }

        for(int j = i; j < nums.length; j++) {
            swap(nums, i, j);
            permuteHelper(nums, i + 1, res);
            swap(nums, i, j);
        }
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
