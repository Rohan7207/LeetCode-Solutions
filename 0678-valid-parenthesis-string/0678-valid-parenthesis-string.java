// Problem: Valid Parenthesis String
// Link: https://leetcode.com/problems/valid-parenthesis-string/
// Difficulty: Medium

// Approach:
// The '*' character can behave as:
//     '('
//     ')'
//     empty string ""
// Instead of trying all possibilities,
// maintain a range of possible open
// parenthesis counts.
// minOpen:
//     Minimum possible number of
//     unmatched '('
// maxOpen:
//     Maximum possible number of
//     unmatched '('
// Traverse the string:
// Case 1: '('
//     Definitely increases open count.
//     minOpen++
//     maxOpen++
// Case 2: ')'
//     Must close one open bracket.
//     minOpen--
//     maxOpen--
// Case 3: '*'
//     '*' can be:
//         ')'  -> decreases open count
//         '('  -> increases open count
//         ""   -> no effect
//     So:
//         minOpen--
//         maxOpen++
// If maxOpen becomes negative:
//     Too many ')' appeared.
//     No valid arrangement is possible.
// If minOpen becomes negative:
//     Convert it to 0 because
//     open brackets cannot be negative.
// After processing all characters:
//     If minOpen == 0
//     then at least one valid interpretation
//     exists.
// Otherwise:
//     Some '(' remain unmatched.

// Time Complexity: O(n)
// Space Complexity: O(1)


class Solution {
    public boolean checkValidString(String s) {
        int minOpen = 0; 
        int maxOpen = 0; 

        for (char c : s.toCharArray()) {
            if (c == '(') {
                minOpen++;
                maxOpen++;
            } else if (c == ')') {
                minOpen--;
                maxOpen--;
            } else {
                minOpen--;
                maxOpen++; 
            }

            if (maxOpen < 0) {
                return false;
            }

            minOpen = Math.max(minOpen, 0);
        }

        return minOpen == 0;
    }
}
