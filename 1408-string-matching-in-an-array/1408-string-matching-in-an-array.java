class Solution {
    public List<String> stringMatching(String[] words) {
        List<String> res = new ArrayList<>();

        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            for (int j = 0; j < words.length; j++) {
                if (i != j) {
                if (words[j].contains(word)) {
                    res.add(word);
                    break;
                }
            }
            }
        }

        return res;
    }
}