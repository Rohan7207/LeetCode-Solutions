class Solution {
    public String[] findOcurrences(String text, String first, String second) {
        String[] words = text.split(" ");
        List<String> list = new ArrayList<>();
        int n = words.length;

        for(int i = 0; i < n - 2; i++) {
            String firstWord = words[i];
            if(firstWord.equals(first)) {
                String SecondWord = words[i + 1];
                if(SecondWord.equals(second)) {
                    list.add(words[i + 2]);
                }
            }
        }

        int len = list.size();
        String[] ans = new String[len];

        for(int i = 0; i < len; i++) {
            ans[i] = list.get(i);
        }

        return ans;
    }
}