// Problem: X of a kind in a Deck of Cards
// Link: https://leetcode.com/problems/x-of-a-kind-in-a-deck-of-cards/
// Difficulty: Easy

// Approach:
// First, count the frequency of every card using a HashMap.
// Let the frequencies be:
//     f1, f2, f3, ...
// We need to partition every card into groups of the same size x,
// where x > 1.
// Therefore, every frequency must be divisible by x.
// Instead of checking every possible value of x,
// compute the GCD of all frequencies.
// If:
//     gcd(f1, f2, f3, ...) > 1
// then every frequency is divisible by that GCD,
// so a valid group size exists.
// Otherwise, no integer greater than 1 can divide
// all frequencies, making the partition impossible.
// Compute the GCD by iteratively updating:
//     gcdValue = gcd(gcdValue, currentFrequency)
// Finally:
//     gcdValue > 1  → true
//     gcdValue = 1  → false

// Time Complexity:
//     O(n + k × log M)
//     n = number of cards
//     k = number of distinct card values
//     M = maximum frequency
//
// Space Complexity:
//     O(k)
//     (HashMap storing frequencies)


class Solution {
    public boolean hasGroupsSizeX(int[] deck) {
        int n = deck.length;
        Map<Integer, Integer> freqMap = new HashMap<>();

        for (int card : deck) {
            freqMap.put(card, freqMap.getOrDefault(card, 0) + 1);
        }

        int gcdValue = 0;
        for (int freq : freqMap.values()) {
            gcdValue = gcd(gcdValue, freq);
        }

        return gcdValue > 1;
    }

    private int gcd(int a, int b) {
        if (b == 0) {
            return a;
        }

        return gcd(b, a % b);
    }
}
