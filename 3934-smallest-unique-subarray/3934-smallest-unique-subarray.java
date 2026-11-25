// Problem: Smallest Unique Subarray
// Link: https://leetcode.com/problems/smallest-unique-subarray/?envType=daily-question&envId=2026-11-25
// Difficulty: Hard

// Approach:
// 
// 1. We need to find the minimum subarray length such that
//    at least one subarray of that length occurs exactly once.
//
// 2. Instead of checking every possible subarray length independently,
//    use Binary Search on the answer.
//
// 3. For a candidate length 'mid', check whether any subarray
//    of exactly that length is unique.
//
// 4. Use a rolling hash to represent each fixed-size subarray.
//    This allows us to calculate the next window's hash in O(1).
//
// 5. Store the frequency of every hash in a HashMap.
//
// 6. If any hash has frequency 1, a unique subarray exists
//    for that length.
//
// 7. If check(mid) is true, try smaller lengths.
//    Otherwise, try larger lengths.

// Time Complexity: O(n log n * log MOD) approximately
// Space Complexity: O(n)

class Solution {

    private int n;
    private static final long MOD = 1_000_000_007L;

    public int smallestUniqueSubarray(int[] nums) {
        n = nums.length;
        int max = -1;

        for (int num : nums) {
            max = Math.max(max, num);
        }

        int base = max + 1;
        int res = n;

        int left = 1, right = n;

        while (left <= right) { 
            int mid = left + (right - left) / 2;

            if (check(nums, mid, base)) {
                res = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return res; 
    }

    private boolean check(int[] nums, int size, int base) {
        Map<Long, Integer> map = new HashMap<>();
        long hash = 0;

        long p = findPow(base, size - 1); 

        int i = 0, j = 0;
        while (j < n) { // O(n)
            hash = (hash * base + nums[j]) % MOD;

            if (j - i + 1 < size) {
                j++;
            } else {
                map.put(hash, map.getOrDefault(hash, 0) + 1);

                hash = (hash - (nums[i] * p) % MOD + MOD) % MOD;

                i++;
                j++;
            }
        }

        for (Integer count : map.values()) {
            if (count == 1) {
                return true;
            }
        }

        return false;
    }

    private long findPow(long a, long exp) {
        long result = 1;

        while (exp > 0) {
            if (exp % 2 == 1) {
                result = (result * a) % MOD;
            }

            a = (a * a) % MOD;
            exp /= 2;
        }

        return result;
    }
}
