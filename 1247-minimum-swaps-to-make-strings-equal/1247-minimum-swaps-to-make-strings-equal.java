// Problem: Minimum Swaps to Make Strings Equal
// Link: https://leetcode.com/problems/minimum-swaps-to-make-strings-equal/
// Difficulty: Medium

// Approach:
// Use Greedy + Counting.
//
// 1. Compare s1 and s2 character by character.
//
// 2. Ignore positions where both characters are equal,
//    because they are already correct.
//
// 3. For every mismatch, there are only two possibilities:
//
//       s1 = x, s2 = y  → xy mismatch
//       s1 = y, s2 = x  → yx mismatch
//
// 4. Pair two `xy` mismatches.
//    One cross-string swap fixes both.
//
//       xy + xy → 1 swap
//
//    Therefore, xy / 2 swaps.
//
// 5. Pair two `yx` mismatches similarly.
//
//       yx + yx → 1 swap
//
//    Therefore, yx / 2 swaps.
//
// 6. If the total number of mismatches is odd,
//    it is impossible to fix them:
//
//       if ((xy + yx) % 2 == 1)
//           return -1;
//
// 7. There can be one leftover `xy` and one leftover `yx`.
//    These two leftovers require 2 swaps.
//
//    Therefore:
//
//       (xy % 2) * 2
//
//    adds 2 when xy is odd and 0 when xy is even.
//
// 8. Final answer:
//
//       xy / 2 + yx / 2 + (xy % 2) * 2

// Time Complexity: O(n)
// Space Complexity: O(1)


class Solution {
    public int minimumSwap(String s1, String s2) {
        int xy = 0;
        int yx = 0;

        for (int i = 0; i < s1.length(); i++) {
            if (s1.charAt(i) == s2.charAt(i)) {
                continue;
            }

            if (s1.charAt(i) == 'x') {
                xy++;
            } else {
                yx++;
            }
        }

        if ((xy + yx) % 2 == 1) {
            return -1;
        }

        return xy / 2 + yx / 2 + (xy % 2) * 2;
    }
}
