class Solution {
    public int maximumLengthSubstring(String s) {
        int[] count = new int[26];
        int maxLength = 0;

        int left = 0;
        for (int right = 0; right < s.length(); right++) {
            char ch = s.charAt(right);
            count[ch - 'a']++;

            while (count[ch - 'a'] > 2) {
                char leftChar = s.charAt(left);
                count[leftChar - 'a']--;
                left++;
            }

            maxLength = Math.max(maxLength, right - left + 1);
        }

        return maxLength;
    }
}