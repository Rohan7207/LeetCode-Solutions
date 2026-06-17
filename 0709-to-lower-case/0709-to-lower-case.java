// Problem: To Lower Case
// Link: https://leetcode.com/problems/to-lower-case/
// Difficulty: Easy

// Approach:
// We need to convert all uppercase letters
// in the given string into lowercase letters.
// Use a StringBuilder to build the answer.
// Traverse every character in the string:
//     If character is between 'A' and 'Z':
//         It is uppercase.
//         Convert it to lowercase by adding 32.
//     Else:
//         Keep the character as it is.
// Finally return StringBuilder as string.
// Why +32?
//     In ASCII:
//         'A' = 65
//         'a' = 97
//     Difference:
//         97 - 65 = 32
// So:
//     'A' + 32 = 'a'
//     'B' + 32 = 'b'
//     ...
//     'Z' + 32 = 'z'

// Time Complexity: O(n)
// Space Complexity: O(n)


class Solution {
    public String toLowerCase(String s) {
        StringBuilder str = new StringBuilder();

        for (char ch : s.toCharArray()) {
            if (ch >= 'A' && ch <= 'Z') {
                str.append((char) (ch + 32));
            } else {
                str.append(ch);
            }
        }

        return str.toString();
    }
}

// or
// return s.toLowerCase();
