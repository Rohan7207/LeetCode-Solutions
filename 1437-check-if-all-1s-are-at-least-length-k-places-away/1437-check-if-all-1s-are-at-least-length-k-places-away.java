// Problem: Check If All 1's Are at Least Length K Places Away
// Link: https://leetcode.com/problems/check-if-all-1s-are-at-least-length-k-places-away/
// Difficulty: Easy

// Approach:
// 1. We only care about the positions of the 1s.
//
// 2. Keep track of the index of the previous 1 using prevOne.
//
// 3. Whenever we find another 1, calculate the number of zeros
//    between the current 1 and previous 1:
//
//        i - prevOne - 1
//
// 4. If the number of zeros is less than k, the required spacing
//    is violated, so return false.
//
// 5. Otherwise, update prevOne to the current 1.
//
// 6. If we finish the array without finding a violation, return true.

// Time Complexity: O(n)
// Space Complexity: O(1)


class Solution {
    public boolean kLengthApart(int[] nums, int k) {
        int prevOne = -1;

        for(int i = 0; i < nums.length; i++) {
            if(nums[i] == 1) {
                if(prevOne != -1 && i - prevOne - 1 < k) {
                    return false;
                }

                prevOne = i;
            }
        }

        return true;
    }
}
