class Solution {
    public boolean detectCapitalUse(String word) {
        int capitals = 0;

        for(int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);

            if(ch >= 'A' && ch <= 'Z') capitals++;
        }

        if(capitals == word.length()) {
            return true;
        } else if(capitals == 1 && Character.isUpperCase(word.charAt(0))) {
            return true;
        } else if(capitals == 0) {
            return true;
        }

        return false;
    }
}
