// Problem: Decrypt String from Alphabet to Integer Mapping
// Link: https://leetcode.com/problems/decrypt-string-from-alphabet-to-integer-mapping/
// Difficulty: Easy

// Approach:
// Use Reverse Traversal + String Building.
//
// 1. Traverse the encoded string from right to left.
// 2. If the current character is '#':
//      - The previous two characters form a number from 10 to 26.
//      - Convert that number to its corresponding letter.
//      - Move 3 positions backward.
//
// 3. Otherwise:
//      - The current character is a single digit from 1 to 9.
//      - Convert it to its corresponding letter.
//      - Move 1 position backward.
//
// 4. Characters are decoded in reverse order, so reverse the
//    StringBuilder before returning.

// Time Complexity: O(n)
// Space Complexity: O(n)


class Solution {
    public String freqAlphabets(String s) {
        StringBuilder sb = new StringBuilder();
        int i = s.length() - 1;

        while (i >= 0) {
            char ch = s.charAt(i);
            if (ch == '#') {
                String numStr = s.substring(i - 2, i);
                int num = Integer.parseInt(numStr);

                char letter = (char) (num + 96);
                sb.append(letter);

                i -= 3;
            } else {
                int num = ch - '0';
                char letter = (char) (num + 96);
                sb.append(letter);

                i--;
            }
        }

        return sb.reverse().toString();
    }
}
