// Problem: String Matching in an Array
// Link: https://leetcode.com/problems/string-matching-in-an-array/
// Difficulty: Easy

// Approach:
//
// 1. Treat every word as a candidate substring.
//
// 2. For each word[i], compare it with every other word[j].
//
// 3. Skip when i == j because a word should not be matched with itself.
//
// 4. If words[j].contains(words[i]):
//      → words[i] is a substring of another word.
//      → add it to the result.
//
// 5. Once a match is found, break because we only need to know
//    whether the current word appears anywhere else.

// Time Complexity: O(n² * L)
// Space Complexity: O(1) auxiliary space
//
// n = number of words
// L = maximum word length


class Solution {
    public List<String> stringMatching(String[] words) {
        List<String> res = new ArrayList<>();

        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            for (int j = 0; j < words.length; j++) {
                if (i != j) {
                    if (words[j].contains(word)) {
                        res.add(word);
                        break;
                    }
                }
            }
        }

        return res;
    }
}
