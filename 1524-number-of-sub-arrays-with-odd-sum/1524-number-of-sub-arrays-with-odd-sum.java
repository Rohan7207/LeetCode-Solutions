// Problem: Number of Sub-arrays With Odd Sum
// Link: https://leetcode.com/problems/number-of-sub-arrays-with-odd-sum/?envType=daily-question&envId=2026-11-14
// Difficulty: Medium

// Approach:
// 1. Maintain a running prefix sum.
//
// 2. Track how many previous prefix sums are even and odd.
//
// 3. Initially:
//    evenCount = 1, because the empty prefix has sum 0 (even).
//    oddCount = 0.
//
// 4. For every element, update the running sum.
//
// 5. If the current prefix sum is EVEN:
//    We need a previous ODD prefix sum.
//    Because ODD - EVEN = ODD.
//
//    Add oddCount to the answer.
//
// 6. If the current prefix sum is ODD:
//    We need a previous EVEN prefix sum.
//    Because ODD - EVEN = ODD.
//
//    Add evenCount to the answer.
//
// 7. Update the corresponding parity count.
//
// 8. Return the answer modulo 10^9 + 7.

// Time Complexity: O(n)
// Space Complexity: O(1)


class Solution {

    private int MOD = 1000000007;

    public int numOfSubarrays(int[] arr) {
        int n = arr.length;
        int sum = 0;
        int count = 0;
        int evenCount = 1, oddCount = 0;

        for (int i = 0; i < n; i++) {
            sum += arr[i];

            if (sum % 2 == 0) {
                count = (count + oddCount) % MOD;
                evenCount++;
            } else {
                count = (count + evenCount) % MOD;
                oddCount++;
            }
        }

        return count;
    }
}
