// Problem: Goat Latin
// Link: https://leetcode.com/problems/goat-latin/
// Difficulty: Easy

// Approach:
// The goal is to convert every word of the sentence into Goat Latin format.
// Rules:
// 1. If a word starts with a vowel:
//      - Keep the word as it is.
// 2. If a word starts with a consonant:
//      - Move the first character to the end of the word.
// 3. Add "ma" after every word.
// 4. Add 'a' characters based on the word position:
//      - 1st word -> "a"
//      - 2nd word -> "aa"
//      - 3rd word -> "aaa"
//      and so on.
// Steps:
// 1. Split the sentence into individual words using split(" ").
// 2. Traverse every word one by one.
// 3. For each word:
//      - Check if first character is present in vowel string.
//      - If vowel, append the word directly.
//      - If consonant, remove first character and place it at the end.
// 4. Append "ma" and the increasing suffix of 'a' characters.
// 5. Store transformed words and finally join them with spaces.

// Time Complexity: O(n)
// Where n = total number of characters in the sentence.
// We visit every character once.
//
// Space Complexity: O(n)
// Extra space is used for storing words, result array and StringBuilders.


class Solution {
    public String toGoatLatin(String sentence) {
        // Step 1: Split the sentence into an array of individual words
        String[] words = sentence.split(" ");
        String[] res = new String[words.length];

        String vowels = "aeiouAEIOU";
        StringBuilder suffixA = new StringBuilder("a");

        // Step 2: Loop through each word and transform it
        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            StringBuilder sb = new StringBuilder();
            char firstChar = word.charAt(0);

            // Rule 1 & 2: Check for vowel or consonant start
            if (vowels.indexOf(firstChar) != -1) {
                sb.append(word); // Vowel
            } else {
                sb.append(word.substring(1)).append(firstChar);  //consonant
            }

            // Rule 3: Append "ma" and the index-based "a" strings
            sb.append("ma").append(suffixA);

            // Store the result directly into the output array
            res[i] = sb.toString();

            // Add an extra 'a' for the next word's suffix
            suffixA.append("a");
        }

        
        //  Manually build the final single String from the new array
        StringBuilder finalResult = new StringBuilder();
        for (int i = 0; i < res.length; i++) {
            finalResult.append(res[i]);
            
            // Add a space between words, but NOT after the very last word
            if (i < res.length - 1) {
                finalResult.append(" ");
            }
        }

        return finalResult.toString();
    }
}
