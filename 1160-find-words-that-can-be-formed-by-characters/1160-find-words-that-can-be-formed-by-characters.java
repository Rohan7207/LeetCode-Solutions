class Solution {
    public int countCharacters(String[] words, String chars) {
        int[] countOfChars = new int[26];

        for(char c : chars.toCharArray()) {
            countOfChars[c - 'a']++;
        }

        int ans = 0;
        for(String word : words) {
            int[] tempCount = countOfChars.clone();
            int len = 0;

            for(int i = 0; i < word.length(); i++) {
                char ch = word.charAt(i);
                if(tempCount[ch - 'a'] > 0) {
                    tempCount[ch - 'a']--;
                    len++;
                } else {
                    break;
                }
            }

            if(len == word.length()) {
                ans += word.length();
            }
        }

        return ans;
    }
}