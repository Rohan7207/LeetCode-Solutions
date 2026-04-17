// Problem: 3Sum
// Link: https://leetcode.com/problems/3sum/
// Difficulty: Medium

// Approach:
// 1. Sort the array to enable two-pointer technique and handle duplicates easily.
// 2. Iterate through the array, fixing one element at a time.
// 3. For each fixed element, use two pointers (left and right) to find pairs
//     such that the sum of three elements equals zero.
//         - If sum < 0 → move left pointer forward.
//         - If sum > 0 → move right pointer backward.
//         - If sum == 0 → store the triplet and move both pointers.
// 4. Skip duplicate elements for all three indices to avoid repeated triplets.
// 5. Return the list of unique triplets.

// Time Complexity: O(n^2)
// Space Complexity: O(1) (excluding output)

class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);
        
        for (int i = 0; i < nums.length; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }

            int j = i + 1;
            int k = nums.length - 1;

            while (j < k) {
                int total = nums[i] + nums[j] + nums[k];

                if (total > 0) {
                    k--;
                } else if (total < 0) {
                    j++;
                } else {
                    res.add(Arrays.asList(nums[i], nums[j], nums[k]));
                    j++;
                    k--;

                    while (j < k && nums[j] == nums[j - 1]) j++;
                    while (j < k && nums[k] == nums[k + 1]) k--;
                }
            }
        }

        return res;
    }
}
