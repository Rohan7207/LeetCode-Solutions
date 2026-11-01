// Problem: Make The String Great
// Link: https://leetcode.com/problems/make-the-string-great/
// Difficulty: Easy

// Approach:
// 1. Use a StringBuilder as a stack to construct the final good string.
//    - The last character in the StringBuilder represents the stack top.
//
// 2. Traverse the input string character by character.
//
// 3. For each character:
//    - If the StringBuilder is empty, append the character.
//    - Otherwise, compare the current character with the last character.
//
// 4. Check whether they are the same alphabet letter but have
//    opposite cases.
//    - Convert both characters to lowercase and compare.
//    - Ensure the original characters are different.
//
// 5. If they form a bad pair, remove the last character.
//    Otherwise, append the current character.
//
// 6. Return the StringBuilder as the final good string.

// Time Complexity: O(n)
// Space Complexity: O(n)


class Solution {
    public String makeGood(String s) {
        StringBuilder sb = new StringBuilder();

        sb.append(s.charAt(0));
        for (int i = 1; i < s.length(); i++) {
            char ch = s.charAt(i);

            if (!sb.isEmpty()) {
                char lastChar = sb.charAt(sb.length() - 1);

                if (Character.toLowerCase(ch) == Character.toLowerCase(lastChar) && ch != lastChar) {
                    sb.deleteCharAt(sb.length() - 1);
                } else {
                    sb.append(ch);
                }
            } else {
                sb.append(ch);
            }
        }

        return sb.toString();
    }
}
