// Problem: Sorted GCD Pair Queries
// Link: https://leetcode.com/problems/sorted-gcd-pair-queries/?envType=daily-question&envId=2026-07-17
// Difficulty: Hard

// Approach:
// The brute-force solution generates all possible pairs,
// computes their GCDs, sorts them, and answers the queries.
// However, this requires O(n²) pairs, which is impossible
// for the given constraints.
// Instead, reverse the thinking:
// Rather than finding the GCD of every pair,
// count how many pairs have each possible GCD.
//
// Step 1:
// Find the maximum element in the array.
//
// Step 2:
// Build a frequency array where:
//
//     freq[x] = number of occurrences of x.
//
// Step 3:
// For every possible GCD value g (from max down to 1):
//
//     • Count how many numbers are divisible by g.
//     • If count numbers are divisible by g, then
//
//           countC2 = count * (count - 1) / 2
//
//       gives the number of pairs whose GCD is
//       g or one of its multiples.
//
// Step 4:
// Remove pairs already counted for larger multiples:
//     gcdCount[g] = totalPairs
//                   - gcdCount[2g]
//                   - gcdCount[3g]
//                   - ...
//
// After subtraction:
//
//     gcdCount[g]
//
// stores the number of pairs whose GCD is exactly g.
//
// Step 5:
// Build a prefix count array:
//     prefix[g] = number of pairs having GCD <= g.
//
// This represents the positions occupied by every GCD
// in the sorted gcdPairs array without explicitly
// constructing that array.
//
// Step 6:
// For every query q,
// perform binary search on the prefix array to find
// the first GCD whose cumulative count is greater than q.
//
// That GCD is exactly the value located at index q
// in the sorted gcdPairs array.

// Time Complexity:
//     O(M log M + Q log M)
//
//     where:
//
//         M = maximum value in nums
//         Q = number of queries
//
// Space Complexity:
//     O(M)


class Solution {
    public int[] gcdValues(int[] nums, long[] queries) {
        int max = 0;

        for (int num : nums) {
            if (num > max) {
                max = num;
            }
        }

        int[] freq = new int[max + 1];

        for (int i = 0; i < nums.length; i++) {
            freq[nums[i]]++;
        }

        long[] gcdCount = new long[max + 1];

        for (int g = max; g >= 1; g--) {
            int count = 0;

            for (int multiple = g; multiple <= max; multiple += g) {
                count += freq[multiple];
            }

            long pairs = (long) count * (count - 1) / 2;

            for (int multiple = 2 * g; multiple <= max; multiple += g) {
                pairs -= gcdCount[multiple];
            }

            gcdCount[g] = pairs;
        }

        long[] prefixCount = new long[max + 1];
        long total = 0;

        for (int g = 1; g <= max; g++) {
            total += gcdCount[g];
            prefixCount[g] = total;
        }

        int[] ans = new int[queries.length];

        for (int i = 0; i < queries.length; i++) {
            int left = 1;
            int right = max;

            while (left < right) {
                int mid = left + (right - left) / 2;

                if (prefixCount[mid] > queries[i]) {
                    right = mid;
                } else {
                    left = mid + 1;
                }
            }

            ans[i] = left;
        }

        return ans;
    }
}
