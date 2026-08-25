// Problem: Smallest Missing Multiple of K
// Link: https://leetcode.com/problems/smallest-missing-multiple-of-k/?envType=daily-question&envId=2026-08-25
// Difficulty: Easy

// Approach:
// Use HashSet + Increment by k.
//
// 1. Store all elements of `nums` in a HashSet.
//    This gives O(1) average-time lookup.
//
// 2. Start `ans` from `k`, because the answer must be
//    a positive multiple of k.
//
// 3. Check whether `ans` exists in the set.
//
// 4. If it exists, move to the next multiple:
//
//      ans += k
//
// 5. Continue until we find a multiple of k that is not
//    present in the array.
//
// 6. Return that value.

// Time Complexity: O(n + m), where m is the number of
//                  multiples of k checked.
// Space Complexity: O(n)


class Solution {
    public int missingMultiple(int[] nums, int k) {
        Set<Integer> set = new HashSet<>();

        for (int num : nums) {
            set.add(num);
        }

        int ans = k;
        while (set.contains(ans)) {
            ans += k;
        }

        return ans;
    }
}
