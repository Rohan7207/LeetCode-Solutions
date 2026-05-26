class Solution {
    public int numberOfSpecialChars(String word) {
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
    }
}