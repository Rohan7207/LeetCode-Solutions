class Solution {
    public boolean isAnagram(String s, String t) {
        int[] freq = new int[26];

        if(s.length() != t.length()) {
            return false;
        }

        for(char c : s.toCharArray()) {
            freq[c - 'a']++;
        }

        for(char c : t.toCharArray()) {
            freq[c - 'a']--;
        }

        for(int i = 0; i < s.length(); i++) {
            int idx = s.charAt(i) - 'a';

            if(freq[idx] != 0) {
                return false;
            }
        }

        return true;
    }
}