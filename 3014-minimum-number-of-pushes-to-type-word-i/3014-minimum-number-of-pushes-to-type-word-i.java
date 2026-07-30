class Solution {
    public int minimumPushes(String word) {
        int count = 0;

        for(int i = 0; i < word.length(); i++) {
            count += i / 8 + 1;
        }

        return count;
    }
}