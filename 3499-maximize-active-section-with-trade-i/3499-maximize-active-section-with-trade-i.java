// Problem: Maximize Active Section With Trade I
// Link: https://leetcode.com/problems/maximize-active-section-with-trade-i/?envType=daily-question&envId=2026-07-23
// Difficulty: Medium

// Approach:
// Traverse the string block by block instead of character by character.
// Maintain:
// • count1 -> Length of the zero block immediately before the current one block.
// • count2 -> Length of the zero block immediately after the current one block.
// • t      -> Total number of active sections ('1's) already present.
// • max    -> Maximum merged zero block that can be obtained after removing
//             one valid block of '1's.
// During traversal:
// • If the current character is '0',
//   simply extend the current left zero block.
// • When a block of '1's is encountered,
//   count all consecutive '1's and add them to the total active count (t).
// • Next, count the consecutive '0's immediately following this block.
//   These become the right zero block (count2).
// • If both left and right zero blocks exist,
//   removing the current '1' block merges them into one larger zero block.
//   Its size is:
//      count1 + count2
//   Update the maximum merged zero block found so far.
// • Before processing the next block,
//   the current right zero block becomes the left zero block,
//   so assign:
//      count1 = count2
//   and reset count2 to 0.
// After scanning the entire string,
// the answer is the original number of active sections plus the
// largest merged zero block obtainable after one valid trade.

// Time Complexity: O(n)
// Space Complexity: O(1)


class Solution {
    public int maxActiveSectionsAfterTrade(String s) {
        char[] arr = s.toCharArray();
        int n = arr.length;
        // count1 → length of the left zero block, count2 → length of the right zero block.
        // max -> Stores the largest merged zero block found so far, t -> Counts the total number of '1's already present.
        int count1 = 0, count2 = 0, i = 0;
        int max = 0, t = 0;

        while (i < n) {
            char ch = arr[i];

            if (ch == '0') {
                count1++;
                i++;
            } else {
                while (i < n && ch == '1') {
                    i++;
                    t++;

                    if (i != n) {
                        ch = arr[i];
                    }
                }

                while (i < n && ch == '0') {
                    count2++;
                    i++;

                    if (i != n) {
                        ch = arr[i];
                    }
                }

                if (count1 != 0 && count2 != 0) {
                    max = Math.max(max, count1 + count2);
                }

                count1 = count2;
                count2 = 0;
            }
        }

        return t + max;
    }
}
