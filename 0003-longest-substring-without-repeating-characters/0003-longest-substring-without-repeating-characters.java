// Problem: Longest Substring Without Repeating Characters
// Link: https://leetcode.com/problems/longest-substring-without-repeating-characters/
// Difficulty: Medium

// Approach:
// 1. Use sliding window technique with two pointers(left and right)
// 2. Expand the window and remove duplicates by shifting left pointer
// 3. Track the maximum length without repeating the characters

// Time Complexity: O(n)

// Space Complexity: O(1) — assuming ASCII character set


class Solution {
    public int lengthOfLongestSubstring(String s) {
        //O(n) and O(1)
        // int left = 0;
        // int n = s.length();
        // Set<Character> charset = new HashSet<>();
        // int maxlength = 0;

        // for(int right = 0; right < n; right++){
        //     if(!charset.contains(s.charAt(right))){
        //         charset.add(s.charAt(right));
        //         maxlength = Math.max(maxlength, right - left + 1);
        //     }else{
        //         while(charset.contains(s.charAt(right))){
        //             charset.remove(s.charAt(left));
        //             left++;
        //         }
        //         charset.add(s.charAt(right));
        //     }
        // }

        // return maxlength;

        //O(n) and O(1)
        //Sliding window and hashing 

        int maxLength = 0;
        int left = 0;
        Map<Character, Integer> count = new HashMap<>();

        for(int right = 0; right < s.length(); right++){
            char c = s.charAt(right);
            count.put(c, count.getOrDefault(c, 0) + 1);

            /*This while loop adjusts the left pointer as long as there are repeating characters in the current substring.
It decreases the count of the character at index left and increments left by 1 until there are no repeating character */
            while(count.get(c) > 1){
                char leftChar = s.charAt(left);
                count.put(leftChar, count.get(leftChar) - 1);
                left++;
            }

            maxLength = Math.max(maxLength, right - left + 1);
        }

        return maxLength;
    }
}

