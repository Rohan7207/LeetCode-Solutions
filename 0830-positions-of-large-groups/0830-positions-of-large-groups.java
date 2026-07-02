// Problem: Position of Large Groups
// Link: https://leetcode.com/problems/positions-of-large-groups/
// Difficulty: Easy

// Approach:
// A large group consists of consecutive identical
// characters having length >= 3.
//
// Step 1:
// Maintain two pointers:
//     left  -> starting index of the current group
//     right -> scans the string
// Initially:
//     left = 0
//
// Step 2:
// Move 'right' from index 1 to n.
// The current group continues as long as:
//     s[right] == s[left]
//
// Step 3:
// A group ends when:
//     - right reaches the end of the string, or
//     - the current character differs from
//       the group's starting character.
//
// Step 4:
// Compute the group length:
//     length = right - left
// If:
//     length >= 3
// store:
//     [left, right - 1]
// because right points to the first character
// outside the current group.
//
// Step 5:
// Start a new group by updating:
//     left = right
// Continue until the entire string is processed.

// Time Complexity: O(n)
// Space Complexity: O(1)
// (excluding the output list)


class Solution {
    public List<List<Integer>> largeGroupPositions(String s) {
        List<List<Integer>> res = new ArrayList<>();
        int n = s.length();
        int left = 0;

        for (int right = 1; right <= n; right++) {
            // Check if we reached the end or the character changed
            if (right == n || s.charAt(right) != s.charAt(left)) {
                // If group size is 3 or more, add to result
                if (right - left >= 3) {
                    res.add(new ArrayList<>(Arrays.asList(left, right - 1)));
                }

                // Reset start to current index
                left = right;
            }
        }

        return res;
    }
}

/*
     List<List<Integer>> res = new ArrayList<>();
        int n = s.length();

        for (int left = 0; left < n; left++) {
            int right = left + 1;
            while (right < n) {
                char first = s.charAt(right - 1);
                char sec = s.charAt(right);

                if (first != sec)
                    break;

                right++;
            }

            int len = right - left;
            if (len >= 3) {
                res.add(new ArrayList<>(Arrays.asList(left, right - 1)));
            }

            left = right - 1;
        }

        return res;
*/
