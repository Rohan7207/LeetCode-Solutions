// Problem: Sum Game
// Link: https://leetcode.com/problems/sum-game/?envType=daily-question&envId=2026-08-23
// Difficulty: Medium

// Approach:
// Use game theory + counting instead of trying every possible digit.
//
// 1. Split the string into two equal halves.
//
// 2. For each half, calculate:
//      - sum of known digits
//      - number of '?' characters
//
// 3. If the total number of '?' is odd:
//      Alice gets one extra turn.
//      Therefore, Alice can always force the final sums to be different.
//
// 4. If the total number of '?' is even:
//      Alice and Bob get the same number of turns.
//
//      The current difference is:
//          sumLeft - sumRight
//
//      The difference that the '?' characters can compensate for is:
//          (questionsRight - questionsLeft) * 9 / 2
//
//      Why 9?
//          Each '?' can contain a digit from 0 to 9,
//          so the maximum difference between two choices is 9.
//
//      If the current difference exactly equals this value,
//      Bob can force both halves to have the same sum.
//
//      Therefore:
//          equal → Bob wins
//          different → Alice wins
//
// 5. Return true if Alice can force different sums.

// Time Complexity: O(n)
// Space Complexity: O(n) because of the substring/temporary arrays.


class Solution {
    public boolean sumGame(String num) {
        int n = num.length();

        int[] left = get(num.substring(0, n / 2));
        int[] right = get(num.substring(n / 2, n));

        int sumLeft = left[0], questionsLeft = left[1];
        int sumRight = right[0], questionsRight = right[1];

        return (questionsLeft + questionsRight) % 2 == 1
                || sumLeft - sumRight != ((questionsRight - questionsLeft) * 9) / 2;
    }

    private int[] get(String s) {
        int digitsSum = 0;
        int questions = 0;

        for (char ch : s.toCharArray()) {
            if (ch == '?') {
                questions++;
            } else {
                digitsSum += ch - '0';
            }
        }

        return new int[] { digitsSum, questions };
    }
}
