// Problem: Process String With Special Operations II
// Link: https://leetcode.com/problems/process-string-with-special-operations-ii/?envType=daily-question&envId=2026-06-17
// Difficulty: Hard

// Approach:
// We need to return the kth character
// after processing the string operations.
// But the final string can become very large
// because '#' duplicates the current string.
// So we should not build the final string.
// Instead, use two steps:
// Step 1:
//     Calculate only the final length.
// Step 2:
//     Traverse the original string from right to left
//     and reverse each operation to trace where kth character came from.
// Operations:
// Normal character:
//     Forward:
//         character is added at the end
//     Backward:
//         If k == len - 1,
//         current character is the answer.
//         Otherwise reduce len by 1.
// '#':
//     Forward:
//         string becomes string + string
//     Backward:
//         oldLen = len / 2
//         Since both halves are same,
//         kth index maps to:
//             k = k % oldLen
//         Then len = oldLen.
// '*':
//     Forward:
//         deletes last character if string is not empty
//     Backward:
//         length before deletion was one more,
//         so len++.
// '%':
//     Forward:
//         reverses the string
//     Backward:
//         reverse index also changes:
//             k = len - 1 - k
// Before backward traversal:
//     If k >= len,
//     kth character does not exist,
//     return '.'

// Time Complexity: O(n)
// Space Complexity: O(1)


class Solution {
    public char processStr(String s, long k) {
        long len = 0;

        for (char ch : s.toCharArray()) {
            if (ch == '#') {
                len *= 2;
            } else if (ch == '*') {
                if (len > 0) len--;
            } else if (ch == '%') {

            } else {
                len++;
            }
        }

        if (k >= len)
            return '.';

        for (int i = s.length() - 1; i >= 0; i--) {
            char ch = s.charAt(i);

            if (ch == '#') {
                long oldLen = len / 2;
                if (oldLen == 0) {
                    len = 0;
                    continue;
                }
                k = k % oldLen;
                len = oldLen;
            } else if (ch == '*') {
                len++;
            } else if (ch == '%') {
                k = len - 1 - k;
            } else {
                if (k == len - 1) {
                    return ch;
                }

                len--;
            }
        }

        return '.';
    }
}
