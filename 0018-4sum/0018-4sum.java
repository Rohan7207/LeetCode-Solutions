// Problem: 4Sum
// Link: https://leetcode.com/problems/4sum/
// Difficulty: Medium

// Approach:
// Sort the array to apply two-pointer technique and handle duplicates.
// Use two nested loops to fix the first two elements.
// For each pair, use two pointers (left and right) to find the remaining two elements
// such that the sum equals the target.
//      - If sum < target → move left pointer forward.
//      - If sum > target → move right pointer backward.
//      - If sum == target → store the quadruplets and move both pointers.
// 4. Skip duplicate elements for all four indices to ensure unique quadruplets.
// 5. Return the list of unique quadruplets.

// Time Complexity: O(n ^ 3)
// Space Complexity: O(1) (excluding output)

class Solution {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        if(nums == null || nums.length < 4) return new ArrayList<>();

        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        int n = nums.length;

        for(int i = 0; i < n; i++) {
            if(i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            
            for(int j = i + 1; j < n; j++) {
                if(j > i + 1 && nums[j] == nums[j - 1]) {
                    continue;
                }

                int k = j + 1;
                int l = n - 1;

                while(k < l) {
                    long sum = 0L + nums[i] + nums[j] + nums[k] + nums[l];

                    if(sum > target) {
                        l--;
                    }
                    else if(sum < target) {
                        k++;
                    }
                    else {
                        res.add(Arrays.asList(nums[i], nums[j], nums[k], nums[l]));

                        while(k < l && nums[k] == nums[k + 1]) k++;
                        while(k < l && nums[l] == nums[l - 1]) l--;            

                        k++;
                        l--;
                    }
                } 
            }
        }

        return res;
    }
}
