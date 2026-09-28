class Solution {
    public List<String> stringMatching(String[] words) {
        List<String> res = new ArrayList<>();

        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            for (int j = 0; j < words.length; j++) {
                String pattern = words[j];
                if (i == j || word.length() > pattern.length()) {
                    continue;
                }

                if (pattern.contains(word)) {
                    res.add(word);
                    break;
                }
            }
        }

        return res;
    }
}