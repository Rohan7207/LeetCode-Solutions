class Solution {
    public boolean isAlienSorted(String[] words, String order) {
        int n = words.length;

        for (int i = 0; i < n - 1; i++) {
            int a = 0;
            while (a < words[i].length() && a < words[i + 1].length()) {
                char c1 = words[i].charAt(a);
                char c2 = words[i + 1].charAt(a);

                if (order.indexOf(c1) > order.indexOf(c2))
                    return false;
                if (order.indexOf(c1) < order.indexOf(c2))
                    break;
                if (order.indexOf(c1) == order.indexOf(c2))
                    a++;
            }

            //If one word is a shorter version of the other (like "apple" and "app"), the shorter word must come first. If the longer word comes first, we return false.
            if (a == words[i + 1].length() && a < words[i].length()) {
                return false;
            }
        }

        return true;
    }
}