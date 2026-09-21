// Problem: Find X Value of Array I
// Link: https://leetcode.com/problems/find-x-value-of-array-i/?envType=daily-question&envId=2026-09-20
// Difficulty: Medium

// Approach:
//
// 1. We process the array from left to right.
//    At every index i, we maintain how many subarrays ending at i
//    have each possible product remainder from 0 to k - 1.
//
// 2. `prevCount[rem]` stores the number of subarrays ending at the
//    previous index whose product % k == rem.
//
// 3. For the current element nums[i], first create the new subarray
//    containing only nums[i].
//    Its remainder is nums[i] % k.
//
// 4. Then extend every previous subarray by nums[i].
//    If its old remainder is `oldRem`, the new remainder becomes:
//
//       (oldRem * nums[i]) % k
//
// 5. Store these counts in `currCount`.
//    After processing nums[i], `currCount` becomes `prevCount`.
//
// 6. Add all `prevCount` values into `res` because every subarray
//    ending at the current index is now a valid subarray to count.
//
// 7. Finally, `res[x]` contains the total number of subarrays whose
//    product % k == x.

// Time Complexity: O(n × k)
// Space Complexity: O(k)


class Solution {
    public long[] resultArray(int[] nums, int k) {
        int n = nums.length;
        long[] res = new long[k];
        long[] prevCount = new long[k];

        for (int i = 0; i < n; i++) {
            long[] currCount = new long[k];

            int currEleRem = nums[i] % k;
            currCount[currEleRem]++;

            for (int oldRem = 0; oldRem < k; oldRem++) {
                int newRem = (int) ((long) oldRem * nums[i] % k) % k;

                currCount[newRem] += prevCount[oldRem];
            }

            prevCount = currCount;

            for (int x = 0; x < k; x++) {
                res[x] += prevCount[x];
            }
        }

        return res;
    }
}
