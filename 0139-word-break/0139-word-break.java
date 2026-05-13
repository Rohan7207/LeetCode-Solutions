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

/*
    Consider s = espn and w = {e, sp ,n}
        now dp[0] = true and check for dp[1] = e which can be found like
        dp[0] && w.contains(e) and for dp[2] = es i.e dp[0] && w.con(es) which
        is false and another dp[1] && w.con(s) which is false so fill with false
        so on we find that dp[4] = espn is to be true so return dp[s.length()]
*/