// Problem: Maximum Number of Vowels in a Substring of Given Length
// Link: https://leetcode.com/problems/maximum-number-of-vowels-in-a-substring-of-given-length/
// Difficulty: Medium

// Approach:
// 1. Use a sliding window of size k.
// 2. `count` stores the number of vowels in the current window.
// 3. Expand the window by moving `right`.
// 4. If the new character is a vowel, increment count.
// 5. If window size becomes greater than k:
//      - Remove s[left]'s contribution.
//      - Move left forward.
// 6. Update the maximum vowel count.
// 7. Since each character enters and leaves the window at most once,
//    the solution is O(n).

// Time Complexity: O(n)
// Space Complexity: O(1)


class Solution {
    public int maxVowels(String s, int k) {
        int ans = 0;

        int left = 0;
        int count = 0;
        for (int right = 0; right < s.length(); right++) {
            char ch = s.charAt(right);
            if (ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u') {
                count++;
            }

            if (right - left + 1 > k) {
                char leftChar = s.charAt(left);
                if (leftChar == 'a' || leftChar == 'e' || leftChar == 'i' || leftChar == 'o' || leftChar == 'u') {
                    count--;
                }

                left++;
            }

            ans = Math.max(ans, count);
        }

        return ans;
    }
}
