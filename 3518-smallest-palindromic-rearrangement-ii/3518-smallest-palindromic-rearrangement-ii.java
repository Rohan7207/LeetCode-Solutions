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