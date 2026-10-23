// Problem: Maximum K to Sort a Permutation
// Link: https://leetcode.com/problems/maximum-k-to-sort-a-permutation/?envType=daily-question&envId=2026-10-22
// Difficulty: Medium

// Approach:
//
// 1. Initialize k = -1. In 32-bit signed integer representation,
//    -1 has all bits set to 1: 111111...111.
//
// 2. Traverse the permutation from index 0 to n - 1.
//
// 3. If nums[i] == i, the element is already at its correct
//    position, so ignore it.
//
// 4. If nums[i] != i, the element is misplaced. Update k using:
//
//       k = k & nums[i]
//
//    Bitwise AND preserves only the bits that are set in both
//    k and nums[i].
//
// 5. After processing all misplaced elements, k contains the
//    bitwise AND of their values.
//
// 6. If no elements were misplaced, k remains -1. Return 0.
//    Otherwise, return k.

// Time Complexity: O(n)
// Space Complexity: O(1)


class Solution {
    public int sortPermutation(int[] nums) {
        int n = nums.length;
        int k = -1; 

        for (int i = 0; i < n; i++) {
            if (nums[i] != i) {
                k = (k & nums[i]);
            }
        }

        return k == -1 ? 0 : k;
    }
}
