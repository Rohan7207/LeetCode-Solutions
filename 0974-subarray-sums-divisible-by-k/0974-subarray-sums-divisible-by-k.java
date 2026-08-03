// Problem: Subarray Sums Divisible by K
// Link: https://leetcode.com/problems/subarray-sums-divisible-by-k/
// Difficulty: Medium

// Approach:
// Let prefixSum be the sum of elements from index 0 to i.
//
// A subarray (l+1 ... r) is divisible by k if:
//
//      (prefix[r] - prefix[l]) % k == 0
//
// Rearranging:
//
//      prefix[r] % k == prefix[l] % k
//
// Thus, whenever the current prefix sum has the same remainder
// as a previously seen prefix sum, every previous occurrence
// forms one valid subarray ending at the current index.
//
// Maintain:
//
// - prefixSum : running prefix sum.
// - HashMap<remainder, frequency> : number of previous prefix
//   sums having each remainder.
//
// Initialize:
//
//      freqMap.put(0, 1);
//
// This handles subarrays starting from index 0 whose prefix sum
// is already divisible by k.
//
// For every element:
//
// 1. Update the prefix sum.
// 2. Compute the remainder modulo k.
// 3. If the remainder is negative, convert it into the positive
//    equivalent by adding k.
// 4. Add the frequency of the current remainder to the answer,
//    since each previous occurrence forms a valid subarray.
// 5. Increase the frequency of the current remainder.

// Time Complexity: O(n)
//
// Space Complexity:
// O(min(n, k))


class Solution {
    public int subarraysDivByK(int[] nums, int k) {
        Map<Integer, Integer> freqMap = new HashMap<>();
        int prefixSum = 0;
        int ans = 0;

        freqMap.put(0, 1);

        for (int i = 0; i < nums.length; i++) {
            prefixSum += nums[i];
            int rem = prefixSum % k;

            if (rem < 0) {
                rem += k;
            }

            ans += freqMap.getOrDefault(rem, 0);
            freqMap.put(rem, freqMap.getOrDefault(rem, 0) + 1);
        }

        return ans;
    }
}
