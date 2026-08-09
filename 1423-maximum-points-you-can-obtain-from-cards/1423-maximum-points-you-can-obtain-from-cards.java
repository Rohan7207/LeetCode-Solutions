// Problem: Maximum Points You Can Obtain From Cards
// Link: https://leetcode.com/problems/maximum-points-you-can-obtain-from-cards/
// Difficulty: Medium

// Approach:
// We need to take exactly k cards from either end.
//
// Instead of directly choosing the cards to take, consider the
// cards that are NOT taken.
//
// 1. If n cards exist and we take k cards, then:
//
//       window = n - k
//
//    cards will remain.
//
// 2. Since cards can only be taken from the beginning or end,
//    the cards left behind must form one contiguous window.
//
// 3. Calculate the total sum of all cards.
//
// 4. Find the minimum sum of any window of size n - k using
//    a sliding window.
//
// 5. When the window moves one position:
//    - Add the new element.
//    - Remove the element leaving the window.
//
//       windowSum += cardPoints[i] - cardPoints[i - window]
//
// 6. The maximum score is obtained by leaving the minimum-sum
//    window:
//
//       maximum score = totalSum - minimumWindowSum
//
// 7. If k == n, all cards are taken, so return the total sum.

// Time Complexity: O(n)
// Space Complexity: O(1)


class Solution {
    public int maxScore(int[] cardPoints, int k) {
        int n = cardPoints.length;
        int window = n - k;
        int windowSum = 0;
        int minWindowSum = 0;
        int totalSum = 0;

        for (int num : cardPoints) {
            totalSum += num;
        }

        if (k == n)
            return totalSum;

        for (int i = 0; i < window; i++) {
            windowSum += cardPoints[i];
        }

        minWindowSum = windowSum;

        for (int i = window; i < n; i++) {
            windowSum += cardPoints[i] - cardPoints[i - window];

            minWindowSum = Math.min(minWindowSum, windowSum);
        }

        return totalSum - minWindowSum;
    }
}
