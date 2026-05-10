// Problem: Palindrome Partitioning
// Link: https://leetcode.com/problems/palindrome-partitioning/
// Diificulty: Medium

// Approach:
// Use backtracking to generate all possible
// palindrome partitions of the string.
// Start partitioning from index 0.
// For every possible substring from start to end:
//     - Check whether substring is palindrome.
//     - If palindrome:
//         - Add substring to current partition.
//         - Recursively partition remaining string.
//         - Backtrack by removing last substring.
// When start reaches end of string:
//     - Add current partition to result.
// Return all valid palindrome partitions.

// Time Complexity: O(N * 2^N)
// Space Complexity: O(N)


class Solution {
    public List<List<String>> partition(String s) {
        List<List<String>> res = new ArrayList<>();
        backtrack(res, new ArrayList<>(), s, 0);
        return res;
    }

    private void backtrack(List<List<String>> res, List<String> current, String s, int start){
        if(start == s.length()){
            res.add(new ArrayList<>(current));
            return;
        }

        for(int end = start; end < s.length(); end++){
            if(isPalindrome(s, start, end)){
                current.add(s.substring(start, end + 1));
                backtrack(res, current, s, end + 1);
                current.remove(current.size() - 1);
            }
        }
    }

    private boolean isPalindrome(String s, int start, int end){
        while(start < end){
            if(s.charAt(start++) != s.charAt(end--)){
                return false;
            }
        }

        return true;
    }
}
