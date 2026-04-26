// Problem: Permutations II
// Link: https://leetcode.com/problems/permutations-ii/
// Difficulty: Medium

// Approach:
// Use backtracking with swapping to generate permutations.
// At each recursion level, use a HashSet to track
// elements already used for the current position.
// Iterate from current index to end:
//     - Skip the element if it already exists in the HashSet
//       to avoid duplicate permutations.
//     - Add the element to the HashSet.
//     - Swap the current element with the current index.
//     - Recursively generate permutations for the next index.
//     - Backtrack by swapping again to restore the array.
// When all positions are fixed, add the permutation to result.

// Time Complexity: O(n * n!)
// Space Complexity: O(n)

class Solution {
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        permuteHelper(nums, 0, res);
        return res;
    }

    private void permuteHelper(int[] nums, int i, List<List<Integer>> res) {
        if (i == nums.length) {
            List<Integer> permutation = new ArrayList<>();
            for (int num : nums) {
                permutation.add(num);
            }
            res.add(permutation);
            return;
        }

        Set<Integer> seen = new HashSet<>();

        for (int j = i; j < nums.length; j++) {
            if (seen.contains(nums[j]))
                continue;

            seen.add(nums[j]);

            swap(nums, i, j);
            permuteHelper(nums, i + 1, res);
            swap(nums, i, j); // Helps to backtrack
        }
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
