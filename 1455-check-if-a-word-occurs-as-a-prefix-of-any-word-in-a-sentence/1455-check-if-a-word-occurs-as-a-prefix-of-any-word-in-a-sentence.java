class Solution {
    public int isPrefixOfWord(String sentence, String searchWord) {
        String[] words = sentence.split(" ");

        for(int i = 0; i < words.length; i++) {
            String word = words[i];

            if(searchWord.length() > word.length()) {
                continue;
            }

            int idx = 0;
            for(int j = 0; j < searchWord.length(); j++) {
                if(searchWord.charAt(j) == word.charAt(j)) {
                    idx++;
                }
            }

            if(idx == searchWord.length()) {
                return i + 1;
            }
        }

        return -1;
    }
}