// Problem: Number of Good Pairs
// Link: https://leetcode.com/problems/number-of-good-pairs/
// Difficulty: Easy

// Approach:
//
// 1. We need to count good pairs (i, j) such that:
//      i < j and nums[i] == nums[j].
//
// 2. Create a frequency array `count` of size 101 because
//    the problem guarantees that nums[i] is between 1 and 100.
//
// 3. Traverse the array from left to right.
//
// 4. For each number `num`, count[num] tells us how many times
//    this number has already appeared before the current index.
//
// 5. Every previous occurrence forms exactly one new good pair
//    with the current occurrence.
//
//    Therefore, add count[num] to ans.
//
// 6. Increment count[num] to record the current occurrence.
//
// 7. After processing every element, return ans.

// Time Complexity: O(n)
// Space Complexity: O(1), because the frequency array has a fixed size of 101.


class Solution {
    public int numIdenticalPairs(int[] nums) {
        int[] count = new int[101];
        int ans = 0;

        for (int num : nums) {
            ans += count[num]++;
        }

        return ans;
    }
}
