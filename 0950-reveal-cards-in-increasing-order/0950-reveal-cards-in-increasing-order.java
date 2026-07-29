// Problem: Reveal Cards in Increasing Order
// Link: https://leetcode.com/problems/reveal-cards-in-increasing-order/
// Difficulty: Medium

// Approach:
// The reveal process is:
// 1. Reveal the top card.
// 2. Move the next top card to the bottom.
// Instead of simulating this process forward, reverse it.
// Reverse operations:
// 1. Move the bottom card to the top.
// 2. Place the next largest card on the top.
// Since the cards must be revealed in increasing order, first sort the deck.
// Then process the sorted cards from largest to smallest.
// For every card:
// - If the deque is not empty, move its last element to the front
//   (reverse of moving the top card to the bottom).
// - Insert the current card at the front.
// After processing every card, the deque represents the required
// initial ordering of the deck.
// Finally, copy the deque into an array and return it.

// Time Complexity:
// O(n log n)
// (Sorting takes O(n log n), deque operations take O(n))
// Space Complexity:
// O(n)


class Solution {
    public int[] deckRevealedIncreasing(int[] deck) {
        Arrays.sort(deck);
        int n = deck.length;

        Deque<Integer> dq = new ArrayDeque<>();

        for (int i = n - 1; i >= 0; i--) {
            if (!dq.isEmpty()) {
                dq.addFirst(dq.removeLast());
            }

            dq.addFirst(deck[i]);
        }

        int[] ans = new int[n];
        int idx = 0;

        for (int num : dq) {
            ans[idx] = num;
            idx++;
        }

        return ans;
    }
}
