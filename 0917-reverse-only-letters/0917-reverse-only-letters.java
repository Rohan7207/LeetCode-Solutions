// Problem: Reverse Only Letters
// Link: https://leetcode.com/problems/reverse-only-letters/
// Difficulty: Easy

// Approach:
// Convert the input string into a character array so that
// characters can be swapped in-place.
// Use two pointers:
//     left  -> starts from the beginning
//     right -> starts from the end
// Traverse until the two pointers meet.
// • If the left pointer is on a non-letter,
//   simply move it forward.
// • Else if the right pointer is on a non-letter,
//   move it backward.
// • Otherwise, both pointers are on letters.
//   Swap the two letters and move both pointers inward.
// Since non-letter characters are never swapped,
// they remain at their original positions while
// only the letters get reversed.
// Finally, convert the character array back into a string.

// Time Complexity:
//     O(n)
// Each pointer moves at most n times.
//
// Space Complexity:
//     O(n)
// Character array used for in-place swapping.


class Solution {
    public String reverseOnlyLetters(String s) {
        char[] chars = s.toCharArray();
        int left = 0;
        int right = s.length() - 1;

        while (left < right) {
            if (!Character.isLetter(chars[left])) {
                left++;
            } else if (!Character.isLetter(chars[right])) {
                right--;
            } else {
                char temp = chars[left];
                chars[left] = chars[right];
                chars[right] = temp;

                left++;
                right--;
            }
        }

        return new String(chars);
    }
}
