class Solution {
    public List<List<Integer>> largeGroupPositions(String s) {
        List<List<Integer>> res = new ArrayList<>();
        int n = s.length();

        int left = 0;

        for (int right = 1; right <= n; right++) {
            // Check if we reached the end or the character changed
            if (right == n || s.charAt(right) != s.charAt(left)) {
                // If group size is 3 or more, add to result
                if (right - left >= 3) {
                    res.add(new ArrayList<>(Arrays.asList(left, right - 1)));
                }

                // Reset start to current index
                left = right;
            }
        }

        return res;
    }
}

/*
     List<List<Integer>> res = new ArrayList<>();
        int n = s.length();

        for (int left = 0; left < n; left++) {
            int right = left + 1;
            while (right < n) {
                char first = s.charAt(right - 1);
                char sec = s.charAt(right);

                if (first != sec)
                    break;

                right++;
            }

            int len = right - left;
            if (len >= 3) {
                res.add(new ArrayList<>(Arrays.asList(left, right - 1)));
            }

            left = right - 1;
        }

        return res;
*/