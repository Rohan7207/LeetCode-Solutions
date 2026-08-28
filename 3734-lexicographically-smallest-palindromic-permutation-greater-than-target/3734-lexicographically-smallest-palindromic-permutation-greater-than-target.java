// Problem: Lexicographically Smallest Palindromic Permutation Greater Than Target
// Link: https://leetcode.com/problems/lexicographically-smallest-palindromic-permutation-greater-than-target/?envType=daily-question&envId=2026-08-28
// Difficulty: Hard

// Approach:
// Use Greedy + Counting + Backtracking.
//
// 1. Count the frequency of every character in `s`.
//
// 2. Check whether `s` can form a palindrome.
//    At most one character can have an odd frequency.
//    The odd-frequency character becomes the middle character.
//
// 3. Divide every character frequency by 2.
//    We only need to construct the left half of the palindrome.
//    The right half will be its reverse.
//
// 4. Build the left half greedily from left to right.
//
// 5. At every position:
//      - First try to place the same character as target[i].
//      - Temporarily decrease its count.
//      - Use `check()` to determine whether the remaining
//        characters can produce a palindrome greater than target.
//
// 6. If the choice cannot produce a greater palindrome,
//    backtrack by restoring the character count.
//
// 7. Then try characters greater than target[i].
//    Choose the smallest available character greater than target[i].
//
// 8. Once a greater character is chosen, the palindrome is already
//    guaranteed to be greater than target.
//    Fill all remaining characters in ascending order to get
//    the smallest possible palindrome.
//
// 9. Construct the final palindrome:
//
//      left + oddChar + reverse(left)
//
// 10. If no valid choice can make a palindrome greater than target,
//     return "".

// Time Complexity: O(n²) in this implementation,
// because `check()` repeatedly constructs and compares strings.
//
// Space Complexity: O(n)
// for the constructed strings and StringBuilder.


class Solution {
    public String lexPalindromicPermutation(String s, String target) {
        int n = s.length();

        // Special case: length of 1
        if (n == 1) {
            return s.compareTo(target) > 0 ? s : "";
        }

        // Count the frequency of each character
        int[] count = new int[26];
        for (char c : s.toCharArray()) {
            count[c - 'a']++;
        }

        // Check if it can form a palindrome and record the characters with odd occurrence
        String oddChar = "";
        for (int i = 0; i < 26; i++) {
            if (count[i] % 2 == 1) {
                // More than one character appears an odd number of times, cannot form a palindrome
                if (oddChar != "") {
                    return "";
                }

                oddChar = String.valueOf((char) ('a' + i));
            }

            count[i] /= 2; // It takes only half the characters to construct the left half
        }

        StringBuilder prefix = new StringBuilder();

        // Construct the left part of each digit greedily
        for (int i = 0; i < n / 2; i++) {
            boolean found = false;

            // Try to place the smallest character in lexicographical order
            for (int j = 0; j < 26; j++) {
                if (count[j] == 0) {
                    continue;
                }

                count[j]--;
                if (check(prefix.toString(), (char) ('a' + j), count, oddChar, target)) {
                    // If the constructed palindrome is greater than target, choose the character
                    prefix.append((char) ('a' + j));
                    found = true;
                    break;
                } else {
                    count[j]++; // Not meeting the conditions, reset the counter
                }
            }

            // Cannot construct a palindrome larger than target
            if (!found) {
                return "";
            }

            // prefix is already greater than target
            if (prefix.charAt(i) > target.charAt(i)) {
                StringBuilder left = new StringBuilder(prefix);

                for (int j = 0; j < 26; j++) {
                    for (int k = 0; k < count[j]; k++) {
                        left.append((char) ('a' + j));
                    }
                }

                String palindrome = left.toString() + oddChar + new StringBuilder(left).reverse().toString();

                return palindrome;
            }
        }

        // Construct the final palindrome string
        String ans = prefix.toString() + oddChar + new StringBuilder(prefix).reverse().toString();

        return ans;
    }

    // Checks whether constructed palindrome is lexicographically greater then target or not
    private boolean check(String prefix, char c, int[] count, String oddChar, String target) {
        StringBuilder left = new StringBuilder(prefix);
        left.append(c);

        for (int i = 25; i >= 0; i--) {
            for (int k = 0; k < count[i]; k++) {
                left.append((char) ('a' + i));
            }
        }

        String palindrome = left.toString() + oddChar + new StringBuilder(left).reverse().toString();

        return palindrome.compareTo(target) > 0;
    }
}
