// Problem: The k-th Lexicographical String of All Happy Strings of Length n
// Link: https://leetcode.com/problems/the-k-th-lexicographical-string-of-all-happy-strings-of-length-n/
// Difficulty: Medium

// Approach:
// 1. Generate all possible strings of length n using only 'a', 'b', 'c'.
// 2. A happy string cannot contain two consecutive equal characters.
// 3. Use backtracking to build the string character by character.
// 4. At each position, try 'a', 'b', and 'c'.
// 5. Only choose a character if it differs from the previous character.
// 6. When length becomes n, add the string to the list.
// 7. Since we try characters in 'a' → 'b' → 'c' order, strings are
//    generated directly in lexicographical order.
// 8. Finally, return the (k - 1)th element because k is 1-indexed.

// Time Complexity: O(2^n * n)
// Space Complexity: O(2^n * n) for storing all generated strings,
//                   plus O(n) recursion/StringBuilder space.


class Solution {

    private List<String> list;

    public String getHappyString(int n, int k) {
        list = new ArrayList<>();

        happyStrings(n, new StringBuilder());

        return (list.size() >= k) ? list.get(k - 1) : "";
    }

    private void happyStrings(int n, StringBuilder curr) {
        if (curr.length() == n) {
            list.add(curr.toString());
            return;
        }

        for (int i = 0; i < 3; i++) {
            char ch = (char) (i + 'a');

            if (curr.length() == 0 || curr.charAt(curr.length() - 1) != ch) {
                curr.append(ch);
                happyStrings(n, curr);
                curr.deleteCharAt(curr.length() - 1); // backtrack
            }
        }
    }
}
