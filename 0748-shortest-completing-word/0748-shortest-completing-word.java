class Solution {
    public String shortestCompletingWord(String licensePlate, String[] words) {
        int[] requiredFreq = new int[26];

        for (char ch : licensePlate.toCharArray()) {
            if (Character.isLetter(ch)) {
                ch = Character.toLowerCase(ch);
                requiredFreq[ch - 'a']++;
            }
        }

        String ans = "";
        for (String word : words) {
            int[] currentFreq = new int[26];
            for (char ch : word.toCharArray()) {
                currentFreq[ch - 'a']++;
            }

            boolean valid = true;

            for (int i = 0; i < 26; i++) {
                if (currentFreq[i] < requiredFreq[i]) {
                    valid = false;
                    break;
                }
            }

            if (valid) {
                if (ans.equals("") || word.length() < ans.length()) {
                    ans = word;
                }
            }
        }

        return ans;
    }
}