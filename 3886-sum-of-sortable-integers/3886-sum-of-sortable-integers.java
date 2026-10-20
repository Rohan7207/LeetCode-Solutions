// Problem: Sum of Sortable Integers
// Link: https://leetcode.com/problems/sum-of-sortable-integers/?envType=daily-question&envId=2026-10-20
// Difficulty: Hard

// Approach:
// 1. Copy nums into sortedArray and sort it in O(n log n) time.
//    We compare each original block with its corresponding sorted block.
//
// 2. Iterate through every possible block size k from 1 to n.
//    Only consider k values that divide n, because the array must
//    be partitioned into equal-sized blocks.
//
// 3. For each valid k, divide nums and sortedArray into blocks of size k.
//    Extract corresponding blocks into curr and target.
//
// 4. Check whether curr can be transformed into target using a cyclic
//    rotation. A rotation moves elements from the beginning to the end
//    while preserving their relative order.
//
// 5. To check a rotation efficiently, construct text = target + target.
//    Every cyclic rotation of target appears as a length-k substring
//    within this doubled array.
//
// 6. Use KMP (Knuth-Morris-Pratt) to search for curr inside text.
//    KMP uses an LPS array to avoid rechecking matched elements.
//
// 7. If every corresponding block passes the rotation check, k is valid.
//    Add k to sum.
//
// 8. Return sum, the sum of all valid block sizes.

// Time Complexity: O(n log n + n * d(n)), where d(n) is the number
//                  of divisors of n.
// Space Complexity: O(n).


class Solution {
    public int sortableIntegers(int[] nums) {
        int n = nums.length;

        int[] sortedArray = new int[n];
        for (int i = 0; i < n; i++) {
            sortedArray[i] = nums[i];
        }

        Arrays.sort(sortedArray); // O(nlogn)
        int sum = 0;

        // No.of divisors of n, let be m then this loop would run m times and inner loop runs n / k times bcz we skip k size blocks, overall O(n / k * k) => O(n), therefore overall O(m * n)
        for (int k = 1; k <= n; k++) {
            if (n % k != 0) {
                continue;
            }

            //  Runs n / k times bcz we skip k size blocks and inside loop KMP search takes linear time 
            // O(n) and it is working on k so KMP would take O(k) then overall O(n / k * k) => O(n)
            // Check all k block sizes
            boolean validK = true;
            for (int start = 0; start < n; start += k) {
                int[] curr = new int[k];
                int[] target = new int[k];

                for (int j = start; j < start + k; j++) {
                    curr[j - start] = nums[j];
                    target[j - start] = sortedArray[j];
                }

                if (!isRotationPossible(curr, target)) {
                    validK = false;
                    break;
                }
            }

            if (validK) {
                sum += k;
            }
        }

        return sum; // O(nlogn + m * n) and O(n + k)
    }

    private boolean isRotationPossible(int[] curr, int[] target) {
        int len = target.length;
        int[] text = new int[2 * len];

        for (int i = 0; i < len; i++) {
            text[i] = target[i];
        }

        for (int i = len; i < text.length; i++) {
            text[i] = target[i - len];
        }

        return KMPSearch(curr, text); // O(k)
    }

    // Function to compute LPS(Longest Proper Prefix which is also Suffix) array
    private void computeLPS(int[] pattern, int[] lps) {
        int m = pattern.length;
        int len = 0; // Length of previous longest prefix nad suffix

        lps[0] = 0; // Bcz there is no proper suffix and prefix of patten[0..0]

        int i = 1;
        while (i < m) {
            if (pattern[i] == pattern[len]) {
                len++;
                lps[i] = len;
                i++;
            } else {
                if (len != 0) {
                    len = lps[len - 1]; // You can also write, len = len - 1;
                } else {
                    lps[i] = 0;
                    i++;
                }
            }
        }
    }

    private boolean KMPSearch(int[] pattern, int[] text) {
        int n = text.length;
        int m = pattern.length;

        // Create an LPS array to store longest proper prefix which is also a suffix 
        // lps[i] = the longest proper prefix of curr[0..i] which is also a suffix of curr[0..i].
        int[] lps = new int[m];
        computeLPS(pattern, lps);

        int i = 0; // Index for text
        int j = 0; // Index for pattern

        while (i < n) {
            if (pattern[j] == text[i]) {
                i++;
                j++;
            }

            if (j == m) {
                // Patten found at index i - j + 1 (If you have to return 1 based on index that's why added + 1)
                return true; // Found a match
            } else if (i < n && pattern[j] != text[i]) {
                if (j != 0) {
                    j = lps[j - 1];
                } else {
                    i++;
                }
            }
        }

        return false;
    }
}
