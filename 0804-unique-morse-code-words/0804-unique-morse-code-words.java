// Problem: Unique Morse Code Words
// Link: https://leetcode.com/problems/unique-morse-code-words/
// Difficulty: Easy

// Approach:
// We need to find how many different Morse code transformations are possible
// from the given words.
// The Morse code for each lowercase English letter is fixed.
// So we store all 26 Morse codes in a String array.
// Index mapping:
//     'a' -> 0
//     'b' -> 1
//     'c' -> 2
//     ...
//     'z' -> 25
// For each word:
//     create a StringBuilder
//     convert every character into its Morse code using:
//         morse[ch - 'a']
//     append it to StringBuilder
// After converting the whole word,
// add the final Morse string into a HashSet.
// HashSet stores only unique transformations.
// So if two words produce the same Morse code,
// it will be stored only once.
// Finally, return set.size().

// Time Complexity: O(total characters in all words)
// Space Complexity: O(unique transformations)


class Solution {
    public int uniqueMorseRepresentations(String[] words) {
        String[] morse = {
                ".-", "-...", "-.-.", "-..", ".", "..-.", "--.", "....", "..", ".---", "-.-", ".-..", "--", "-.", "---",
                ".--.", "--.-", ".-.", "...", "-", "..-", "...-", ".--", "-..-", "-.--", "--.." };

        Set<String> set = new HashSet<>();

        for (String word : words) {
            StringBuilder sb = new StringBuilder();

            for (char ch : word.toCharArray()) {
                sb.append(morse[ch - 'a']);
            }

            set.add(sb.toString());
        }

        return set.size();
    }
}
