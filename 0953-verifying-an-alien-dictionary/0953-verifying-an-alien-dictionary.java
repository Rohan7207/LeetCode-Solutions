// Problem: Verifying an Alien Dictionary
// Link: https://leetcode.com/problems/verifying-an-alien-dictionary/
// Difficulty: Easy

// Approach:
// We need to check whether the words are sorted according to the given alien order.
// In normal dictionary order, we compare two adjacent words character by character.
// Here also we do the same, but instead of normal English order,
// we use the given order string.
// For every pair of adjacent words:
//     word1 = words[i]
//     word2 = words[i + 1]
// Compare characters from left to right.
// If both characters are same in alien order:
//     move to next character.
// If character of word1 comes after character of word2:
//     order is wrong, return false.
// If character of word1 comes before character of word2:
//     this pair is valid, stop comparing this pair.
// After comparison, handle prefix case.
// Example:
//     word1 = "apple"
//     word2 = "app"
// Here "app" is prefix of "apple".
// In dictionary order, shorter word should come first.
// So "apple" before "app" is invalid.
// If all adjacent word pairs are valid,
// return true.

// Time Complexity: O(total characters compared * 26)
// Because order.indexOf(ch) takes O(26), which is constant.
// So practically: O(total characters)
//
// Space Complexity: O(1)


class Solution {
    public boolean isAlienSorted(String[] words, String order) {
        int n = words.length;

        for (int i = 0; i < n - 1; i++) {
            int a = 0;
            while (a < words[i].length() && a < words[i + 1].length()) {
                char c1 = words[i].charAt(a);
                char c2 = words[i + 1].charAt(a);

                if (order.indexOf(c1) > order.indexOf(c2))
                    return false;
                if (order.indexOf(c1) < order.indexOf(c2))
                    break;
                if (order.indexOf(c1) == order.indexOf(c2))
                    a++;
            }

            if (a == words[i + 1].length() && a < words[i].length()) {
                return false;
            }
        }

        return true;
    }
}
