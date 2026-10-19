// Problem: Find Target Indices After Sorting Array
// Link: https://leetcode.com/problems/find-target-indices-after-sorting-array/?envType=daily-question&envId=2026-10-18
// Difficulty: Easy

// Approach:
// 1. Initialize lessThanTargetCount to count elements smaller than target.
// 2. Initialize targetCount to count occurrences of target.
// 3. Traverse nums once and update these two counts.
// 4. After sorting, all elements smaller than target appear first.
//    Therefore, the first index of target is lessThanTargetCount.
// 5. Add indices from lessThanTargetCount to
//    lessThanTargetCount + targetCount - 1 to the answer.
// 6. Return the list of target indices.

// Time Complexity: O(n + k), where n is nums.length and
// k is the number of occurrences of target.
// Space Complexity: O(k) for the answer, excluding output space.


class Solution {
    public List<Integer> targetIndices(int[] nums, int target) {
        List<Integer> ans = new ArrayList<>();
        int lessThanTargetCount = 0;
        int targetCount = 0;

        for (int num : nums) {
            if (num < target) {
                lessThanTargetCount++;
            } else if (num == target) {
                targetCount++;
            }
        }

        for (int i = 0; i < targetCount; i++) {
            ans.add(lessThanTargetCount + i);
        }

        return ans;
    }
}
