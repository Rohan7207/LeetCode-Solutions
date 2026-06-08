// Problem: Add Two Numbers
// Link: https://leetcode.com/problems/two-sum/
// Difficulty: Easy

// Approach:
// Find two numbers whose sum equals the target.
// Use a HashMap to store:
//     number -> index
// Traverse the array once.
// For every current number:
//     - Compute required complement:
//           target - current number
//     - Check if complement already exists
//       in the HashMap
//     - If found:
//           current number + complement = target
//           return their indices
//     - Otherwise:
//           store current number and its index
//           in the HashMap
// This allows finding the pair in a single traversal.

// Time Complexity: O(n)
// Space Complexity: O(n)


class Solution {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> pairIdx = new HashMap<>();

        for(int i = 0; i < nums.length; i++){
            int num = nums[i];
            if(pairIdx.containsKey(target - num)){
                return new int[]{i, pairIdx.get(target - num)};
            }
            pairIdx.put(num, i);
        }

        return new int[]{};
    }
}
