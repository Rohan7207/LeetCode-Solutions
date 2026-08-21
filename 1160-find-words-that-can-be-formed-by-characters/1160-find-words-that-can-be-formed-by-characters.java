class Solution {
    public int countCharacters(String[] words, String chars) {
        int[] freq = new int[26];

        for(char c : chars.toCharArray()) {
            freq[c - 'a']++;
        }

        int ans = 0;
        for(String word : words) {
            int[] need = new int[26];

            for(char c : word.toCharArray()) {
                need[c - 'a']++;
            }

            boolean flag = true;
            for(int i = 0; i < 26; i++) {
                if(need[i] > freq[i]) {
                    flag = false;
                    break;
                }
            }

            if(flag) {
                ans += word.length();
            }
        }

        return ans;
    }
}

/*
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
*/