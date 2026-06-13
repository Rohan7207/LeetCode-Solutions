class Solution {
    public String mapWordWeights(String[] words, int[] weights) {
        StringBuilder ans = new StringBuilder();
        int rem = 0;

        for (String word : words) {
            int sum = 0;
            for (int i = 0; i < word.length(); i++) {
                char ch = word.charAt(i);

                sum += weights[ch - 'a'];
            }

            rem = sum % 26;

            ans.append((char) ('z' - rem));
        }

        return ans.toString();
    }
}