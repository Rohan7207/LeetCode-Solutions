// Problem: Remove Invalid Parentheses
// Link: https://leetcode.com/problems/remove-invalid-parentheses/?envType=daily-question&envId=2026-10-07
// Difficulty: Hard

// Approach:
// 1. The minimum removals means we want the longest valid string.
// 2. For every '(' or ')', make two choices:
//      - Keep it.
//      - Remove it.
// 3. `count` represents the current number of unmatched '('.
// 4. For '(' → count++.
// 5. For ')' → count--.
// 6. If count < 0, the current prefix is invalid, so prune this branch.
// 7. At the end:
//      - count == 0 → valid string.
//      - If its length is greater than maxLength, clear previous answers
//        and make this the new maximum.
//      - If its length equals maxLength, add it to the set.
// 8. Non-parenthesis characters have only one choice: keep them.
// 9. HashSet prevents duplicate answers.

// Time Complexity: O(2^n * n)
// Space Complexity: O(2^n * n)


class Solution {

    Set<String> set;
    int n;
    int maxLength;

    public List<String> removeInvalidParentheses(String s) {
        n = s.length();
        set = new HashSet<>();

        backtrack(s, 0, new StringBuilder(), 0);

        List<String> res = new ArrayList<>();
        for (String validString : set) {
            res.add(validString);
        }

        return res;
    }

    private void backtrack(String s, int i, StringBuilder curr, int count) {
        if (count < 0) {
            return;
        }

        if (i == n) {
            if (count == 0) {
                if (curr.length() > maxLength) { 
                    maxLength = curr.length();
                    set.clear();
                }

                if (curr.length() == maxLength) { 
                    set.add(curr.toString());
                }
            }

            return;
        }

        if (s.charAt(i) != '(' && s.charAt(i) != ')') {
            curr.append(s.charAt(i));
            backtrack(s, i + 1, curr, count);
            curr.deleteCharAt(curr.length() - 1);
            return;
        }

        curr.append(s.charAt(i));
        backtrack(s, i + 1, curr, count + (s.charAt(i) == '(' ? 1 : -1));
        curr.deleteCharAt(curr.length() - 1);
        backtrack(s, i + 1, curr, count);
    }
}
