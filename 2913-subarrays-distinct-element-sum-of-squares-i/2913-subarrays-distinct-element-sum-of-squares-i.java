// Problem: Subarrays Distinct Element Sum of Squares I
// Link: https://leetcode.com/problems/subarrays-distinct-element-sum-of-squares-i/?envType=daily-question&envId=2026-10-15
// Difficulty: Easy

// Approach:
// 1. Initialize sum = 0 to accumulate the squared distinct counts.
// 2. Fix each index i as the starting point of a subarray.
// 3. Create a boolean array vis to track which values have appeared
//    in the current subarray.
// 4. Extend the subarray by moving j from i to n - 1.
// 5. If nums[j] has not been visited, mark it and increment count.
// 6. Here, count represents the number of distinct elements in the subarray.
// 7. Add count * count to sum for every subarray ending at j.
// 8. Return sum after considering all possible subarrays.

// Time Complexity: O(n²)
// Space Complexity: O(1) auxiliary space, assuming the value range is fixed.


class Solution {
    public int sumCounts(List<Integer> nums) {
        int sum = 0;
        int n = nums.size();

        for (int i = 0; i < n; i++) {
            int count = 0;
            boolean[] vis = new boolean[101];

            for (int j = i; j < n; j++) {
                int num = nums.get(j);
                if (!vis[num]) {
                    vis[num] = true;
                    count++;
                }

                sum += count * count;
            }
        }

        return sum;
    }
}
