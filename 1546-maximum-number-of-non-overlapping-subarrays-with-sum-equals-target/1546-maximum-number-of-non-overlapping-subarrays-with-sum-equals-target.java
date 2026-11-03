// Problem: Maximum Number of Non-Overlapping Subarrays With Sum Equals Target
// Link: https://leetcode.com/problems/maximum-number-of-non-overlapping-subarrays-with-sum-equals-target/
// Difficulty: Medium

// Approach:
// 1. Use Prefix Sum to calculate the cumulative sum.
// 2. Use a HashSet to store previously seen prefix sums.
// 3. For every current prefixSum, check whether
//    (prefixSum - target) exists in the HashSet.
// 4. If it exists, we found a subarray whose sum equals target.
// 5. Increment count because we select this subarray.
// 6. Clear the HashSet and reset prefixSum to 0
//    to ensure the next subarray does not overlap.
// 7. Insert 0 because it represents an empty prefix
//    at the beginning of the new segment.
// 8. If no subarray is found, store the current prefixSum.

// Time Complexity: O(n)
// Space Complexity: O(n)


class Solution {
    public int maxNonOverlapping(int[] nums, int target) {
        Set<Integer> set = new HashSet<>();
        set.add(0);

        int prefixSum = 0;
        int count = 0;

        for (int num : nums) {
            prefixSum += num;

            if (set.contains(prefixSum - target)) {
                count++;

                set.clear();
                set.add(0);
                prefixSum = 0;
            } else {
                set.add(prefixSum);
            }
        }

        return count;
    }
}
