class Solution {
    public int[] shortestToChar(String s, char c) {
        int n = s.length();
        int[] ans = new int[n];

        int prev = Integer.MIN_VALUE / 2;

        // left closest c
        for (int i = 0; i < n; i++) {

            if (s.charAt(i) == c) {
                prev = i;
            }

            ans[i] = i - prev;
        }

        int next = Integer.MAX_VALUE / 2;

        // right closest c
        for (int i = n - 1; i >= 0; i--) {

            if (s.charAt(i) == c) {
                next = i;
            }

            ans[i] = Math.min(ans[i], next - i);
        }

        return ans;
    }
}

/*
    List<Integer> list = new ArrayList<>();

        for(int i = 0; i < s.length(); i++) {
            if(s.charAt(i) == c) {
                list.add(i);
            }
        }

        int[] ans = new int[s.length()];
        for(int i = 0; i < s.length(); i++) {
            int min = Integer.MAX_VALUE;
            for(int idx : list) {
                min = Math.min(min, Math.abs(idx - i));
            }

            ans[i] = min;
        }

        return ans;
*/