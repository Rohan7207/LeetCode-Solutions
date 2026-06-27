// Problem: Set Mismatch
// Link: https://leetcode.com/problems/set-mismatch/
// Difficulty: Easy

// Approach:
// Find the duplicate and missing number using frequency counting.
// Since nums contains numbers from 1 to n:
//     Every number should appear exactly once.
// Step 1:
// Create a count array of size n+1.
//     count[i] stores how many times number i appears.
// Step 2:
// Traverse nums and update frequency:
//     count[num]++;
// Step 3:
// Traverse count array from 1 to n:
//     If count[i] == 2:
//          i is the duplicate number.
//     If count[i] == 0:
//          i is the missing number.
// Step 4:
// Store:
//     ans[0] = duplicate
//     ans[1] = missing
// Return ans.

// Time Complexity: O(n)
// Space Complexity: O(n)


class Solution {
    public int[] findErrorNums(int[] nums) {
        int[] ans = new int[2];
        int[] count = new int[nums.length + 1];

        for (int num : nums) {
            count[num]++;
        }

        for (int i = 1; i <= nums.length; i++) {
            if (count[i] == 2)
                ans[0] = i;

            if (count[i] == 0)
                ans[1] = i;
        }

        return ans;
    }
}
