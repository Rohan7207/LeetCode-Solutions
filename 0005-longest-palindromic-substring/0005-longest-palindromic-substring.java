class Solution {
    public String longestPalindrome(String s) {
        //Mancher's algorithm solves in O(n) but we come with O(n^2) and O(1)

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

  /*When i = 2 left = 0 and right = 4, we found 5 as a max length, but we don't know 5 is the max length in the current iteration, so we try to move to the next place to find longer palindrome, even if we don't find it in the end.

That's why, left and right pointer always overrun and stop at max length in current iteration + 1, so we need to subtract -1 from right - left. 


We check both odd and even centers because palindromes can have either a single center or two-character center.
*/