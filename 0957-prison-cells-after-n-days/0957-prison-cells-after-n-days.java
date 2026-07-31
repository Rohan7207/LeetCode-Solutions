class Solution {
    public int[] prisonAfterNDays(int[] cells, int n) {
        String str = Arrays.toString(cells);

        // Key   -> Prison state (e.g. "01011010") , Value -> Remaining days (or current day index)
        Map<String, Integer> seen = new HashMap<>();
        seen.put(str, n);

        while(n > 0) {
            cells = helper(cells);
            n--;

            String currStr = Arrays.toString(cells);

            if(!seen.containsKey(currStr)) {
                seen.put(currStr, n); 
            } else {
                int cycleLength = seen.get(currStr) - n;
                n %= cycleLength;
            }
        }

        return cells;
    }

    private int[] helper(int[] cells) {
        int[] next = new int[8];
        next[0] = next[7] = 0;

        for(int i = 1; i < 7; i++) {
            if(cells[i - 1] == cells[i + 1]) {
                next[i] = 1;
            }
        }

        return next;
    }
}