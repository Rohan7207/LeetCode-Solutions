// Problem: Number of Unique XOR Triplets II
// Link: https://leetcode.com/problems/number-of-unique-xor-triplets-ii/?envType=daily-question&envId=2026-07-24
// Difficulty: Medium

// Approach:
// The important observation is that nums[i] <= 1500, so every number fits
// within 11 bits. Therefore, the XOR of any three numbers can only lie in
// the range [0, 2047], giving only 2048 possible XOR states.
//
// Instead of generating all O(n³) triplets, build the answer layer by layer.
//
// Layer 1:
// Store every XOR value achievable using exactly one element.
// Since XOR of one element is the element itself, mark one[num] = true.
//
// Layer 2:
// For every XOR value already achievable in Layer 1, XOR it with every
// array element to generate all XOR values achievable using two elements.
//
// Layer 3:
// Similarly, for every XOR value in Layer 2, XOR it with every array
// element to generate all XOR values achievable using three elements.
//
// Finally, count how many XOR values are marked true in Layer 3.
//
// Since there are only 2048 possible XOR states, scanning all states is
// inexpensive. Each transition processes only the reachable XOR states.

// Time Complexity: O(2048 × n) ≈ O(n)
// Space Complexity: O(2048)


class Solution {
    public int uniqueXorTriplets(int[] nums) {
        boolean[] one = new boolean[2048];
        boolean[] two = new boolean[2048];
        boolean[] three = new boolean[2048];

        for (int num : nums) {
            one[num] = true;
        }

        for (int xor = 0; xor < 2048; xor++) {
            if (!one[xor]) {
                continue;
            }

            for (int num : nums) {
                two[xor ^ num] = true;
            }
        }

        for (int xor = 0; xor < 2048; xor++) {
            if (!two[xor]) {
                continue;
            }

            for (int num : nums) {
                three[xor ^ num] = true;
            }
        }

        int count = 0;
        for (int xor = 0; xor < 2048; xor++) {
            if (three[xor]) {
                count++;
            }
        }

        return count;
    }
}
