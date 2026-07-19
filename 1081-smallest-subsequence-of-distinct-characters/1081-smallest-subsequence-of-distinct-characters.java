// Problem: Smallest Subsequence of Distinct Characters
// Link: https://leetcode.com/problems/smallest-subsequence-of-distinct-characters/?envType=daily-question&envId=2026-07-19
// Difficulty: Medium

// Approach:
// The goal is to build the lexicographically smallest subsequence
// containing every distinct character exactly once.
// First, store the last occurrence index of every character.
// This helps determine whether a character removed now can be
// added again later.
// Instead of using a Stack, use a StringBuilder as the stack.
// Also maintain a boolean array 'seen' to know whether a
// character is already present in the current subsequence.
// 
// Traverse the string character by character:
// • If the current character is already included,
//   skip it since every distinct character should appear only once.
// • Otherwise, while:
//      - the current subsequence is not empty,
//      - the last character in the subsequence is lexicographically
//        greater than the current character,
//      - and the last character appears again later,
//   remove the last character from the subsequence and mark it
//   as not seen.
// • Append the current character to the subsequence
//   and mark it as seen.
//
// After processing all characters, the StringBuilder itself
// represents the required smallest subsequence.

// Time Complexity:
// O(n)
// (Each character is appended and removed at most once.)
//
// Space Complexity:
// O(1)
// (Auxiliary space is constant since only arrays of size 26 are used.
// The output StringBuilder stores the answer.)


class Solution {
    public String smallestSubsequence(String s) {
        int[] lastIndex = new int[26];

        for (int i = 0; i < s.length(); i++) {
            lastIndex[s.charAt(i) - 'a'] = i;
        }

        boolean[] seen = new boolean[26];
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            int curr = ch - 'a';

            if (seen[curr]) {
                continue;
            }

            while (sb.length() > 0 && sb.charAt(sb.length() - 1) > ch
                    && lastIndex[sb.charAt(sb.length() - 1) - 'a'] > i) {
                seen[sb.charAt(sb.length() - 1) - 'a'] = false;
                sb.deleteCharAt(sb.length() - 1);
            }

            sb.append(ch);
            seen[curr] = true;
        }

        return sb.toString();
    }
}

/*
    // Step 1: Store the last occurrence of every character
        int[] lastIndex = new int[26];

        // Fill lastIndex array
        for(int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            lastIndex[ch - 'a'] = i;
        }

        // Step 2: Stack to build the answer
        Stack<Character> stack = new Stack<>();

        // Step 3: Keep track of characters already in the stack
        Set<Character> set = new HashSet<>();

        // Step 4: Traverse the string
        for(int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);

            if(set.contains(ch)) {
                continue;
            }

            while(!stack.isEmpty() && stack.peek() > ch && lastIndex[stack.peek() - 'a'] > i) {
                char removed = stack.pop();
                set.remove(removed);
            }

            stack.push(ch);
            set.add(ch);
        }

        // Step 5: Build answer from stack
        StringBuilder ans = new StringBuilder();

        // Pop every character from stack
        while(!stack.isEmpty()) {
            ans.append(stack.pop());
        }

        // Since stack gives reverse order, reverse the StringBuilder
        return ans.reverse().toString();
*/
