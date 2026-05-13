// Problem: Word Break
// Link: https://leetcode.com/problems/word-break/
// Difficulty: Medium

// Approach:
// Use Dynamic Programming to determine whether the string
// can be segmented into dictionary words.
// Maintain a dp array where dp[i] represents whether
// substring s[0...i-1] can be formed.
// Convert word dictionary into HashSet for fast lookup.
// For every index i, check all previous partitions j.
// If dp[j] is true and substring s[j...i-1] exists
// in dictionary, mark dp[i] as true.
// Return dp[s.length()].

// Time Complexity: O(n^2)
// Space Complexity: O(n)


class Solution {
    public boolean wordBreak(String s, List<String> wordDict) {
        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true;  // dp[0] means string which is empty and null string is always present

        Set<String> wordSet = new HashSet<>(wordDict);

        for(int i = 1; i <= s.length(); i++){
            for(int j = i - 1; j >= 0; j--){
                if(dp[j] && wordSet.contains(s.substring(j,i))){  
                    dp[i] = true;
                    break;
                }
            }
        }

        return dp[s.length()];
    }
}
