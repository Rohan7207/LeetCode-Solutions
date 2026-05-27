// Problem: Count the Number of Special Characters II
// Link: https://leetcode.com/problems/count-the-number-of-special-characters-ii/?envType=daily-question&envId=2026-05-27
// Difficulty: Medium

// Approach:
// Store the last occurrence index of every
// lowercase letter and the first occurrence
// index of every uppercase letter.
// Traverse the string once:
//     - For lowercase letters,
//       continuously update their last index.
//     - For uppercase letters,
//       record only their first occurrence.
// After traversal, check all 26 letters.
// A letter is special if:
//     - Lowercase version exists.
//     - Uppercase version exists.
//     - Last lowercase occurrence appears
//       before the first uppercase occurrence.
// Count all such letters and return the count.

// Time Complexity: O(n)
// Space Complexity: O(1)


class Solution {
    public int numberOfSpecialChars(String word) {
        int[] lastLower = new int[26];
        int[] firstUpper = new int[26];

        Arrays.fill(lastLower, -1);
        Arrays.fill(firstUpper, -1);

        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);

            if(ch >= 'a' && ch <= 'z') {
                lastLower[ch - 'a'] = i;
            } else {
                int idx = ch - 'A';
                if(firstUpper[idx] == -1) {
                    firstUpper[idx] = i;
                }
            }
        }

        int count = 0;

        for(int i = 0; i < 26; i++) {
            if(lastLower[i] != -1 && firstUpper[i] != -1 && lastLower[i] < firstUpper[i]) {
                count++;
            }
        }

        return count;
    }
}
