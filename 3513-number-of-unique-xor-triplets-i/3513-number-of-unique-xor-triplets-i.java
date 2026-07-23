// Problem: Number of Unique XOR Triplets I
// Link: https://leetcode.com/problems/number-of-unique-xor-triplets-i/?envType=daily-question&envId=2026-07-23
// Difficulty: Medium

// Approach:
// Observe that nums is a permutation of the integers from 1 to n,
// so the answer depends only on n and not on the arrangement.
// XOR of three elements has two important properties:
// • a ⊕ a ⊕ a = a
// • a ⊕ a ⊕ b = b
// Therefore, every original number is always obtainable.
// For n ≥ 3, XORing three distinct values generates all possible
// bit combinations that can be represented using the number of bits
// required for n.
// If the largest number n requires k bits, then every XOR result is
// also a k-bit number. Hence, the maximum number of distinct XOR
// values is:
//      2^k
// It can be proven that for n ≥ 3, all these k-bit values are
// achievable. Thus, the answer is simply the smallest power of 2
// strictly greater than n.
// Compute this by repeatedly doubling 1 until it becomes greater
// than n.
// Special Cases:
// • n = 1 → answer = 1
// • n = 2 → answer = 2

// Time Complexity: O(log n)
// Space Complexity: O(1)


class Solution {
    public int uniqueXorTriplets(int[] nums) {
        int n = nums.length;

        if (n <= 2)
            return n;

        int ans = 1;
        while (ans <= n) {
            ans <<= 1; 
        }

        return ans;
    }
}
