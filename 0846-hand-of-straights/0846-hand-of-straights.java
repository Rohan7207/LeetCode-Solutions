// Problem: Hand of Straights
// Link: https://leetcode.com/problems/hand-of-straights/
// Difficulty: Medium

// Approach:
// A valid group must contain consecutive cards.
// Since every group must start from its smallest card,
// always choose the smallest available card first.
// Store the frequency of every card in a TreeMap.
// TreeMap keeps all keys sorted,
// allowing us to always access the smallest remaining card.
// While cards are still available:
//     1. Take the smallest card as the start of a group.
//     2. Try to form a consecutive sequence:
//            first
//            first + 1
//            first + 2
//            ...
//            first + groupSize - 1
//     3. For every required card:
//          • If it does not exist,
//            forming the group is impossible,
//            so return false.
//          • Otherwise,
//            decrease its frequency.
//          • Remove the card from the map
//            when its frequency becomes zero.
// If all cards are successfully used, return true.

// Time Complexity: O(n log n)
// Space Complexity: O(n)


class Solution {
    public boolean isNStraightHand(int[] hand, int groupSize) {
        if (hand.length % groupSize != 0)
            return false;

        TreeMap<Integer, Integer> cardCounts = new TreeMap<>();
        for (int card : hand) {
            cardCounts.put(card, cardCounts.getOrDefault(card, 0) + 1);
        }

        while (!cardCounts.isEmpty()) {
            int first = cardCounts.firstKey(); 
            for (int i = 0; i < groupSize; i++) {
                int currentCard = first + i; 

                if (!cardCounts.containsKey(currentCard)) {
                    return false; 
                }

                int count = cardCounts.get(currentCard);
                if (count == 1) {
                    cardCounts.remove(currentCard);
                } else {
                    cardCounts.put(currentCard, count - 1);
                }
            }
        }

        return true;
    }
}
