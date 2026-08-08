// Problem: Find the Lexicographically Smallest Valid Sequence
// Link: https://leetcode.com/problems/find-the-lexicographically-smallest-valid-sequence/?envType=daily-question&envId=2026-08-08
// Difficulty: Medium

// Approach:
// We need to choose m indices from word1 such that the selected
// characters form a string that differs from word2 in at most
// one position. Among all valid index arrays, we need the
// lexicographically smallest one.
//
// Step 1: Build the `last` array
// ------------------------------
// Traverse word1 from right to left while matching word2 from
// right to left.
//
// last[j] stores the earliest index in word1 from which the
// character word2[j] can be matched while still allowing the
// remaining suffix of word2 to be matched.
//
// This gives us information about whether the remaining part
// of word2 can be completed after choosing a particular index.
//
// Step 2: Greedily build the answer
// ----------------------------------
// Traverse word1 from left to right.
//
// For the current word1[i] and word2[j], there are two cases:
//
// 1. Characters match:
//    word1[i] == word2[j]
//
//    We can safely choose i because it does not use our one
//    allowed mismatch.
//
// 2. Characters do not match:
//
//    We can still choose i as the one mismatch, but only if:
//    - `skip == 0`, meaning the mismatch has not been used yet.
//    - There is enough room to match the rest of word2.
//
//    The condition `i < last[j + 1]` guarantees that the next
//    required part of word2 can still be matched after index i.
//
//    If j is already the last character of word2, there is no
//    suffix left, so the mismatch is automatically safe.
//
// Step 3: Why greedy gives the smallest answer
// ---------------------------------------------
// We scan word1 from left to right and take the first index that
// can lead to a valid sequence.
//
// Since the first index is minimized whenever possible, then the
// second index is minimized, and so on, the resulting index array
// is lexicographically smallest.
//
// Step 4:
// If we successfully choose m indices, return `res`.
// Otherwise, no valid sequence exists, so return an empty array.

// Time Complexity:
// O(n + m)
// Space Complexity:
// O(m)


class Solution {
    public int[] validSequence(String word1, String word2) {
        int n = word1.length();
        int m = word2.length();

        int[] last = new int[m];
        Arrays.fill(last, -1);

        int j = m - 1;

        for (int i = n - 1; i >= 0; i--) {
            if (j >= 0 && word1.charAt(i) == word2.charAt(j)) {
                last[j--] = i;
            }
        }

        int[] res = new int[m];
        int skip = 0;
        j = 0;

        for (int i = 0; i < n; i++) {
            if (j == m) {
                break;
            }

            if (word1.charAt(i) == word2.charAt(j) || (skip == 0 && (j == m - 1 || i < last[j + 1]))) {
                skip += word1.charAt(i) != word2.charAt(j) ? 1 : 0;

                res[j++] = i;
            }
        }

        return j == m ? res : new int[0];
    }
}
