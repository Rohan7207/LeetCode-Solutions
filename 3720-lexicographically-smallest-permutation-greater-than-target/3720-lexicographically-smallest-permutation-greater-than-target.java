// Problem: Lexicographically Smallest Permutation Greater Than Target
// Link: https://leetcode.com/problems/lexicographically-smallest-permutation-greater-than-target/?envType=daily-question&envId=2026-08-27
// Difficulty: Medium

// Approach:
// Use Greedy + Frequency Counting + Backtracking.
//
// 1. Count how many times each character occurs in `s`.
//    This lets us construct any permutation without actually
//    generating all permutations.
//
// 2. Build the answer from left to right, following `target`.
//
// 3. At every position, first TRY to place the same character
//    as target[i].
//
// 4. Temporarily remove that character from `count` and ask:
//       "Can the remaining characters be arranged to make
//        the remaining suffix GREATER than target's suffix?"
//
//    We answer this by creating the MAXIMUM possible string
//    from the remaining characters using getMaxString().
//
//       maxRemaining > targetSuffix
//
//    If true:
//       - keep target[i]
//       - continue to the next position.
//
// 5. If matching target[i] is not possible, BACKTRACK:
//       count[targetChar]++;
//
//    Then try every character greater than target[i].
//
// 6. Choose the SMALLEST available character greater than
//    target[i].
//
//    Once we choose a character greater than target[i], the
//    complete answer is guaranteed to be greater than target.
//    Therefore, we no longer need to compare suffixes.
//
// 7. Fill all remaining positions using getMinString(),
//    because we want the SMALLEST possible string greater
//    than target.
//
// 8. If we cannot match target[i] and cannot choose any
//    greater character, return "".

// Time Complexity: O(n * 26) approximately O(n)
// Space Complexity: O(n) for the constructed strings.


class Solution {
    public String lexGreaterPermutation(String s, String target) {
        int[] count = new int[26];
        for (char c : s.toCharArray()) {
            count[c - 'a']++;
        }

        StringBuilder res = new StringBuilder();
        int n = target.length();

        for (int i = 0; i < n; i++) {
            int targetChar = target.charAt(i) - 'a';

            // Case 1: First try to place the same character as target[i] at the current position
            if (count[targetChar] > 0) {
                count[targetChar]--;

                // Check if the remaining characters can form a string greater than target[i+1:]
                if (canFormGreaterString(count, target, i + 1)) {
                    res.append(target.charAt(i));
                    continue;
                }

                // Cannot form a larger string, backtrack                    
                count[targetChar]++;
            }

            // Case 2: Place a character greater than target[i] at the current position
            for (int j = targetChar + 1; j < 26; j++) {
                if (count[j] > 0) {
                    count[j]--;
                    res.append((char) ('a' + j));

                    // Fill remaining positions with the smallest lexicographical order and return final string
                    res.append(getMinString(count));

                    return res.toString();
                }
            }

            // No feasible solution found, return directly
            return "";
        }

        return "";
    }

    // Check if the remaining characters can form a string greater than the suffix.
    private boolean canFormGreaterString(int[] count, String target, int start) {
        String maxStr = getMaxString(count);
        String suffixStr = target.substring(start);

        return maxStr.compareTo(suffixStr) > 0;
    }

    // Get the maximum lexicographical string (in descending order)
    private String getMaxString(int[] count) {
        StringBuilder ans = new StringBuilder();

        for (int i = 25; i >= 0; i--) {
            if (count[i] > 0) {
                ans.append(String.valueOf((char) ('a' + i)).repeat(count[i]));
            }
        }

        return ans.toString();
    }

    // Get the lexicographically smallest string (in ascending order)
    private String getMinString(int[] count) {
        StringBuilder ans = new StringBuilder();

        for (int i = 0; i < 26; i++) {
            if (count[i] > 0) {
                ans.append(String.valueOf((char) ('a' + i)).repeat(count[i]));
            }
        }

        return ans.toString();
    }
}
