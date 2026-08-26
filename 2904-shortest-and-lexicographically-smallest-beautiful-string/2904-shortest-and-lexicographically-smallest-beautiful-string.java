class Solution {
    public String shortestBeautifulSubstring(String s, int k) {
        int totalOne = 0;

        for(int i = 0; i < s.length(); i++) {
            totalOne += s.charAt(i) - '0';    // If '1' returns 1 or '0' returns 0 according to ASCII
        }

        if(totalOne < k) return "";

        String ans = s;
        int left = 0;
        int cnt = 0;
        for(int right = 0; right < s.length(); right++) {
            cnt += s.charAt(right) - '0';

            while(cnt > k || s.charAt(left) == '0') {
                cnt -= s.charAt(left++) - '0';
            }

            if(cnt == k) {
                String temp = s.substring(left, right + 1);

                if(temp.length() < ans.length() || ((temp.length() == ans.length()) && temp.compareTo(ans) < 0)) {
                    ans = temp;
                }
            }
        }

        return ans;
    }
}