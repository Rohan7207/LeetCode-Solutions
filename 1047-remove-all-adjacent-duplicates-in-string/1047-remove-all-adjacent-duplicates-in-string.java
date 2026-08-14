// Problem : Remove All Adjacent Duplicates in String
// Link : https://leetcode.com/problems/remove-all-adjacent-duplicates-in-string/
// Difficulty : Easy

// Approach:
// Use a StringBuilder as a stack.
//
// 1. Traverse the string character by character.
//
// 2. Check the last character currently present in the StringBuilder.
//
// 3. If the last character is equal to the current character,
//    they form an adjacent duplicate, so remove the last character.
//
// 4. Otherwise, add the current character to the StringBuilder.
//
// 5. Continue this process for the entire string.
//
// The StringBuilder acts like a stack:
// - append()  → push
// - deleteCharAt(len - 1) → pop
//
// This also automatically handles chain reactions because after
// removing a duplicate, the previous character becomes the new
// top of the stack.

// Time Complexity: O(n)
// Space Complexity: O(n)


class Solution {
    public String removeDuplicates(String s) {
        StringBuilder str = new StringBuilder();

        for (char c : s.toCharArray()) {
            int len = str.length();
            if (len > 0 && str.charAt(len - 1) == c) {
                str.deleteCharAt(len - 1);
            } else {
                str.append(c);
            }
        }
        
        return str.toString();
    }
}
