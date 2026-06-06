// Problem : Find All Numbers Disappeared in an Array
// Link : https://leetcode.com/problems/find-all-numbers-disappeared-in-an-array/
// Difficulty : Easy

// Approach:
// Since numbers are in the range [1, n],
// every number can be mapped to an index.
// For each number x:
//     Visit index (x - 1)
// and mark it as visited by making
// nums[x - 1] negative.
// Use Math.abs() because some values
// may already have been marked negative.
// After marking:
//     Negative value at index i
//     => number (i + 1) exists
//     Positive value at index i
//     => number (i + 1) is missing
// Traverse the array again and collect
// all indices whose values are positive.

// Time Complexity: O(n)
// Space Complexity: O(1)


class Solution {
    public List<Integer> findDisappearedNumbers(int[] nums) {
        List<Integer> ans = new ArrayList<>();

        for (int i = 0; i < nums.length; i++) {
            int index = Math.abs(nums[i]) - 1;
            if (nums[index] > 0) {
                nums[index] = -nums[index];
            }
        }
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0) {
                ans.add(i + 1);
            }
        }
        return ans;
    }
}
