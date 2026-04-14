// Problem: Longest Palindromic Substring
// Link: https://leetcode.com/problems/longest-palindromic-substring/
// Difficulty: Medium

// Approach:
// 1. Iterate through the each index and treat it as a possible center.
// 2. For each index, check two cases:
//        - Odd length palindrome (center at i)
//        - Even length palindrome (center at i and i+1)
// 3. Expand around center while the characters match.
// 4. Track the longest found palindrome using start and end.
// 5. Return the substring from start to end


// Time Complexity: O(n ^ 2)

// Space Complexity: O(1)

class Solution {
    public String longestPalindrome(String s) {

        if(s == null || s.length() == 0){
            return "";
        }

        int start = 0;
        int end = 0;

        for(int i = 0; i < s.length(); i++){
            int odd = expandAroundCenter(s, i, i);
            int even = expandAroundCenter(s, i, i + 1);
            int max_length = Math.max(odd, even);

            if(max_length > end - start){
                start = i - (max_length - 1) / 2;
                end = i + max_length / 2;
            }
        }

        return s.substring(start, end + 1);
    }

    private int expandAroundCenter(String s, int left, int right){
        while(left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)){
            left--;
            right++;
        }

        return right - left - 1;
    }
}
