// Problem: Long Pressed Name
// Link: https://leetcode.com/problems/long-pressed-name/
// Difficulty: Easy

// Approach:
// Use two pointers to compare the original name and the typed string.
// Maintain:
// • i -> points to the current character in 'name'.
// • j -> points to the current character in 'typed'.
// Traverse both strings simultaneously:
// • If name[i] and typed[j] are equal,
//   the current character is correctly typed.
//   Move both pointers.
// • Otherwise, if typed[j] is the same as the previous character
//   in 'typed', it represents a long press of the previous key.
//   Move only the typed pointer.
// • If neither condition is satisfied,
//   the current character in 'typed' is invalid,
//   so return false.
// After the main traversal:
// • If there are remaining characters in 'typed',
//   they must all be repetitions of the previous character.
//   Otherwise, return false.
// Finally,
// • Return true only if every character of 'name'
//   has been successfully matched.

// Time Complexity: O(m + n)
// Space Complexity: O(1)


class Solution {
    public boolean isLongPressedName(String name, String typed) {
        int m = name.length();
        int n = typed.length();
        int i = 0;
        int j = 0;

        while (i < m && j < n) {
            if (name.charAt(i) == typed.charAt(j)) {
                i++;
                j++;
            } else if (j > 0 && typed.charAt(j) == typed.charAt(j - 1)) {
                j++;
            } else {
                return false;
            }
        }

        while (j < n) {
            if (j > 0 && typed.charAt(j) != typed.charAt(j - 1)) {
                return false;
            }

            j++;
        }

        return i == m;
    }
}
