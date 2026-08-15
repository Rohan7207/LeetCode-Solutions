// Problem : Occurrences After Bigram
// Link : https://leetcode.com/problems/occurrences-after-bigram/
// Difficulty : Easy

// Approach:
// Split the given text into individual words and examine every
// group of three consecutive words.
//
// 1. Split `text` using spaces to get an array of words.
//
// 2. Traverse the array while `i + 2` is within the array.
//
// 3. For every position, check whether:
//       words[i]     == first
//       words[i + 1] == second
//
// 4. If both match, then `words[i + 2]` is the word occurring
//    after the given bigram, so add it to the answer list.
//
// 5. Convert the list into a String array and return it.

// Time Complexity: O(n)
// Space Complexity: O(n)


class Solution {
    public String[] findOcurrences(String text, String first, String second) {
        String[] words = text.split(" ");
        List<String> ans = new ArrayList<>();
        int n = words.length;

        for (int i = 0; i < n - 2; i++) {
            if (first.equals(words[i]) && second.equals(words[i + 1])) {
                ans.add(words[i + 2]);
            }
        }
        
        return ans.toArray(new String[0]);
    }
}
