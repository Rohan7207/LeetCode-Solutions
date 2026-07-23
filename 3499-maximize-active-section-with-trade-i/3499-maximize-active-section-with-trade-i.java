class Solution {
    public int maxActiveSectionsAfterTrade(String s) {
        // Step 1: Add imaginary 1's at both ends
        String t = "1" + s + "1";

        // Step 2: Count initial active sections 
        int active = 0;
        for (char ch : s.toCharArray()) {
            if (ch == '1') {
                active++;
            }
        }

        int ans = active;

        // Step 3: Store and build consecutive blocks
        List<Character> blocks = new ArrayList<>();
        List<Integer> length = new ArrayList<>();

        int i = 0;
        while (i < t.length()) {
            char ch = t.charAt(i);
            int count = 0;

            while (i < t.length() && t.charAt(i) == ch) {
                i++;
                count++;
            }

            blocks.add(ch);
            length.add(count);
        }

        // Step 4: Traverse every blocks
        for (i = 1; i < blocks.size() - 1; i++) {
            if (blocks.get(i) == '1' &&
                    blocks.get(i - 1) == '0' &&
                    blocks.get(i + 1) == '0') {
                int leftZero = length.get(i - 1);
                int rightZero = length.get(i + 1);

                int newActive = active + leftZero + rightZero;

                ans = Math.max(ans, newActive);
            }
        }

        return ans;
    }
}