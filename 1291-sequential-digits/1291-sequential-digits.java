// Problem: Sequential Digits
// Link: https://leetcode.com/problems/sequential-digits/?envType=daily-question&envId=2026-07-13
// Difficulty: Medium

// Approach:
// A sequential digit number is formed by consecutive digits,
// such as 12, 123, 4567, etc.
// Observe that every sequential number is simply a continuous
// substring of the fixed string:
//     "123456789"
// Algorithm:
//     1. Find the number of digits in low and high.
//     2. For every possible digit length from minLen to maxLen:
//            • Generate every substring of that length from
//              "123456789".
//            • Convert the substring into an integer.
//            • If the number lies within [low, high],
//              add it to the answer.
//     3. Since substrings are generated from left to right and
//        lengths are processed in increasing order,
//        the resulting list is already sorted.

// Time Complexity:
//     There are at most 36 sequential numbers to generate
//     (8 of length 2, 7 of length 3, ..., 1 of length 9).
//
//     Total : O(1)
//
// Space Complexity:
//     Answer list : O(1)
//     (Maximum possible sequential numbers is constant.)


class Solution {
    public List<Integer> sequentialDigits(int low, int high) {
        int minLen = String.valueOf(low).length();
        int maxLen = String.valueOf(high).length();

        String s = "123456789";
        List<Integer> ans = new ArrayList<>();

        for (int len = minLen; len <= maxLen; len++) {
            // Generate all sequential numbers of this length
            for (int i = 0; i <= 9 - len; i++) {
                String num = s.substring(i, i + len);
                int value = Integer.parseInt(num);
                
                if (value >= low && value <= high) {
                    ans.add(value);
                }
            }
        }

        return ans;
    }
}
