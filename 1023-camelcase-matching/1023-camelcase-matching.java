// Problem: Camelcase Matching
// Link: https://leetcode.com/problems/camelcase-matching/
// Difficulty: Medium

// Approach:
// Use two pointers to match the pattern with each query.
//
// 1. `i` points to the current character in the query.
//    `j` points to the current character in the pattern.
//
// 2. If both characters match, move both pointers forward.
//
// 3. If the query character is lowercase but does not match,
//    skip it because lowercase characters can be inserted into
//    the query without affecting the camel-case pattern.
//
// 4. If the query character is uppercase and does not match,
//    the query cannot match the pattern, so stop processing it.
//
// 5. After processing the query, it is valid only when:
//       i == query.length()
//       j == pattern.length()
//
//    This ensures every query character was processed and every
//    pattern character was matched.
//
// 6. Repeat the same process for every query.

// Time Complexity: O(total number of characters in all queries)
// Space Complexity: O(1) auxiliary space


class Solution {
    public List<Boolean> camelMatch(String[] queries, String pattern) {
        List<Boolean> ans = new ArrayList<>();
        int n = pattern.length();

        for (String query : queries) {
            int i = 0;
            int j = 0;
            int m = query.length();

            while (i < m) {
                if (j < n && query.charAt(i) == pattern.charAt(j)) {
                    i++;
                    j++;
                } else if (Character.isLowerCase(query.charAt(i))) {
                    i++;
                } else {
                    break; // Reject unmatched uppercase letters
                }
            }

            ans.add(i == m && j == n);
        }

        return ans;
    }
}
