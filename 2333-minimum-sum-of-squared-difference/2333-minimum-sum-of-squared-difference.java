// Problem: Minimum Sum of Squared Difference
// Link: https://leetcode.com/problems/minimum-sum-of-squared-difference/?envType=daily-question&envId=2026-10-10
// Difficulty: Medium

// Approach:
// 1. Calculate the absolute difference between each pair of elements.
// 2. Find the maximum difference and the sum of all differences.
// 3. If the total difference <= k1 + k2, all differences can become zero; return 0.
// 4. Create a frequency array where freq[d] represents the number of differences equal to d.
// 5. Start from the maximum difference and process differences in descending order.
// 6. If k >= freq[i], reduce every difference at level i by 1:
//    - Move all freq[i] elements to freq[i - 1].
//    - Subtract freq[i] from k and clear freq[i].
// 7. Otherwise, reduce only k elements by 1:
//    - Move k elements from freq[i] to freq[i - 1].
//    - Stop because all operations are used.
// 8. Calculate the sum of squared differences using the frequency array.

// Time Complexity: O(n + D)
// Space Complexity: O(D)
//
// n = nums1.length, D = maximum absolute difference.


class Solution {
    public long minSumSquareDiff(int[] nums1, int[] nums2, int k1, int k2) {
        long ans = 0;
        int maxDiff = -1;
        long sumDiff = 0;
        int k = k1 + k2;

        for (int i = 0; i < nums1.length; i++) {
            int diff = Math.abs(nums1[i] - nums2[i]);
            sumDiff += diff;

            maxDiff = Math.max(maxDiff, diff);
        }

        if (sumDiff <= k) {
            return 0;
        }

        int[] freq = new int[maxDiff + 1];

        for (int i = 0; i < nums1.length; i++) {
            int diff = Math.abs(nums1[i] - nums2[i]);

            freq[diff]++;
        }

        for (int i = maxDiff; i > 0 && k > 0; i--) {
            if (freq[i] == 0) {
                continue;
            }

            if (k >= freq[i]) {
                k -= freq[i];
                freq[i - 1] += freq[i];
                freq[i] = 0;
            } else {
                freq[i - 1] += k;
                freq[i] -= k;
                k = 0;
            }
        }

        for (int diff = 0; diff < freq.length; diff++) {
            if (freq[diff] > 0) {
                ans += (long) diff * diff * freq[diff];
            }
        }

        return ans;
    }
}
