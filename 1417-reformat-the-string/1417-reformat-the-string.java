// Problem: Reformat The String
// Link: https://leetcode.com/problems/reformat-the-string/
// Difficulty: Easy

// Approach:
// 1. Separate the input into two groups:
//      - letters
//      - digits
//
// 2. Count both groups.
//    If their count differs by more than 1, alternating them is impossible.
//
// 3. Decide which group should come first.
//    - If letters >= digits → start with a letter.
//    - If digits > letters → start with a digit.
//
// 4. Use two pointers:
//      i → current letter
//      j → current digit
//
// 5. Alternately append one character from each group.
//    Toggle `flag` after every insertion.
//
// 6. Since the two groups differ by at most 1, the alternation
//    consumes all characters exactly once.

// Time Complexity: O(n)
// Space Complexity: O(n)


class Solution {
    public String reformat(String s) {
        int n = s.length();
        StringBuilder sb = new StringBuilder();
        StringBuilder letters = new StringBuilder();
        StringBuilder digits = new StringBuilder();

        for (int i = 0; i < n; i++) {
            char ch = s.charAt(i);

            if (Character.isDigit(ch)) {
                digits.append(ch);
            } else {
                letters.append(ch);
            }
        }

        int len1 = letters.length();
        int len2 = digits.length();

        if (Math.abs(len1 - len2) > 1) {
            return "";
        }

        int i = 0, j = 0;

        // If digits are more, start with digit.;
        boolean flag = len1 >= len2;

        while (i < len1 || j < len2) {
            if (flag) {
                sb.append(letters.charAt(i++));
            } else {
                sb.append(digits.charAt(j++));
            }

            flag = !flag;
        }

        return sb.toString();
    }
}
