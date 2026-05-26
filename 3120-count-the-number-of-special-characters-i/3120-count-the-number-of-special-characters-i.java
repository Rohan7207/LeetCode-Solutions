class Solution {
    public int numberOfSpecialChars(String word) {
        boolean[] isLower = new boolean[26];
        boolean[] isUpper = new boolean[26];

        for (char ch : word.toCharArray()) {
            if (Character.isLowerCase(ch)) {
                isLower[ch - 'a'] = true;
            } else {
                isUpper[ch - 'A'] = true;
            }
        }

        int count = 0;

        for (int i = 0; i < 26; i++) {
            if (isLower[i] && isUpper[i]) {
                count++;
            }
        }

        return count;
    }
}

/*
     Set<Character> s = new HashSet<>();
        int count = 0;

        for(char c : word.toCharArray()) {
            s.add(c);
        }

        for(char c = 'a'; c <= 'z'; c++) {
            if(s.contains(c) && s.contains((char)(c - 'a' + 'A'))) {
                count++;
            }
        }

        return count;
*/