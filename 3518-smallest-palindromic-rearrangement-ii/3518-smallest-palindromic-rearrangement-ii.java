// Problem: Smallest Palindromic Rearrangement II
// Link: https://leetcode.com/problems/smallest-palindromic-rearrangement-ii/?envType=daily-question&envId=2026-07-29
// Difficulty: Hard

// Approach:
// A palindrome is completely determined by its left half because
// the right half is always the reverse of the left half.
//
// Instead of generating every palindromic permutation, we construct
// the left half greedily, one character at a time.
//
// For every position:
//
// 1. Try every available character from 'a' to 'z' in lexicographical order.
// 2. Temporarily place that character.
// 3. Count how many distinct left halves can be formed using the
//    remaining characters.
// 4. If the k-th palindrome lies within this count, permanently
//    choose this character.
// 5. Otherwise, skip all those palindromes at once by increasing
//    the starting index and try the next character.
//
// To count the number of distinct arrangements of the remaining
// characters, use combinations:
//
//      n! / (f1! × f2! × ...)
//
// Instead of factorials, compute this efficiently by repeatedly
// choosing positions for each character using combinations (nCr),
// which avoids overflow and supports early stopping once the count
// exceeds k.
//
// After constructing the left half:
//
// - Append the middle character (if the palindrome length is odd).
// - Append the reverse of the left half to obtain the complete palindrome.

// Time Complexity:
// O(26 × n²)
// Space Complexity:
// O(26)


class Solution {
    public String smallestPalindrome(String s, int k) {
        // Length of the left half.
        int partition = s.length() / 2;

        // Frequency of characters in the left half.
        int[] bucket = new int[26];

        for (int i = 0; i < partition; i++) {
            bucket[s.charAt(i) - 'a']++;
        }

        StringBuilder left = new StringBuilder();

        // Current lexicographical starting index.
        long startIndex = 1;

        // Build the left half one character at a time.
        for (int pos = 0; pos < partition; pos++) {
            for (int i = 0; i < 26; i++) {
                if (bucket[i] == 0) {
                    continue;
                }

                // Temporarily use this character.
                bucket[i]--;

                // Count how many palindromes can be formed
                // if this character is fixed here.
                long ways = permutations(partition - pos - 1, bucket, k);

                // The k-th palindrome lies in this block.
                if (startIndex + ways > k) {
                    left.append((char) (i + 'a'));
                    break;
                }

                // Otherwise skip all these palindromes.
                bucket[i]++;
                startIndex += ways;
            }
        }

        // Not enough palindromes exist.
        if (left.length() < partition) {
            return "";
        }

        // Append middle character for odd-length palindrome.
        if (s.length() % 2 != 0) {
            left.append(s.charAt(partition));
        }

        // Append reverse of left half to complete palindrome.
        for (int i = partition - 1; i >= 0; i--) {
            left.append(left.charAt(i));
        }

        return left.toString();
    }

    // Calculates nCr.
    // If the value exceeds k, return k + 1 since we only need to know
    // whether the count is greater than k.
    private long comb(long n, long m, long k) {
        long res = 1;

        // Use smaller value for efficient combination calculation.
        m = Math.min(m, n - m);

        for (int i = 1; i <= m; i++) {
            res = (res * (n - i + 1)) / i;

            // Early stopping if combinations already exceed k.
            if (res > k) {
                return k + 1;
            }
        }

        return res;
    }

    // Returns the number of distinct permutations that can be formed
    // using the remaining character frequencies.
    private long permutations(int rem, int[] bucket, long k) {
        long ways = 1;

        for (int i = 0; i < 26; i++) {
            if (bucket[i] == 0) {
                continue;
            }

            // Choose positions for the current character.
            ways *= comb(rem, bucket[i], k);

            // No need to calculate further once ways exceed k.
            if (ways > k) {
                break;
            }

            rem -= bucket[i];
        }

        return ways;
    }
}
