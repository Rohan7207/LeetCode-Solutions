class Solution {
    public int maxActiveSectionsAfterTrade(String s) {
        char[] arr = s.toCharArray();
        int n = arr.length;
        // count1 → length of the left zero block, count2 → length of the right zero block.
        // max -> Stores the largest merged zero block found so far, t -> Counts the total number of '1's already present.
        int count1 = 0, count2 = 0, i = 0;
        int max = 0, t = 0;

        while(i < n) {
            char ch = arr[i];

            if(ch == '0') {
                count1++;
                i++;
            } else {
                while(i < n && ch == '1') {
                    i++;
                    t++;
                    
                    if(i != n) {
                        ch = arr[i];
                    }
                }

                while(i < n && ch == '0') {
                    count2++;
                    i++;

                    if(i != n) {
                        ch = arr[i];
                    }
                }

                if(count1 != 0 && count2 != 0) {
                    max = Math.max(max, count1 + count2);
                }

                count1 = count2;
                count2 = 0;
            }
        }

        return t + max;
    }
}

/*
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
*/