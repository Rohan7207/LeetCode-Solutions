// Problem: Find Missing Element
// Link: https://leetcode.com/problems/find-missing-elements/?envType=daily-question&envId=2026-08-04
// Difficulty: Easy

// Approach:
// The missing elements must lie between the minimum and maximum
// values present in the array.
//
// First, traverse the array once to:
// 1. Find the minimum element.
// 2. Find the maximum element.
// 3. Store every element in a HashSet for O(1) lookups.
//
// After obtaining the range [min, max], iterate from
// (min + 1) to (max - 1).
//
// For every integer in this range:
// - If it is not present in the HashSet, then it is a missing
//   element, so add it to the answer list.
//
// Finally, return the list of all missing elements.

// Time Complexity:
// O(n + (max - min))
//
// Space Complexity:
// O(n)


class Solution {
    public List<Integer> findMissingElements(int[] nums) {
        Set<Integer> set = new HashSet<>();
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        for (int num : nums) {
            if (num < min) {
                min = num;
            }

            if (num > max) {
                max = num;
            }

            set.add(num);
        }

        List<Integer> ans = new ArrayList<>();

        for (int i = min + 1; i < max; i++) {
            if (!set.contains(i)) {
                ans.add(i);
            }
        }

        return ans;
    }
}
