class Solution {
    public int largestOverlap(int[][] img1, int[][] img2) {
        List<int[]> ones1 = new ArrayList<>();
        List<int[]> ones2 = new ArrayList<>();
        int n = img1.length;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (img1[i][j] == 1) {
                    ones1.add(new int[] { i, j });
                }
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (img2[i][j] == 1) {
                    ones2.add(new int[] { i, j });
                }
            }
        }

        Map<String, Integer> map = new HashMap<>();
        int ans = 0;
        for (int[] a : ones1) {
            for (int[] b : ones2) {
                int dr = a[0] - b[0];
                int dc = a[1] - b[1];

                String key = dr + "," + dc;
                map.put(key, map.getOrDefault(key, 0) + 1);

                ans = Math.max(ans, map.get(key));
            }
        }

        return ans;
    }
}

/*
    (-1, -1) => 3   (-1, 0) => 1    (0, -1) => 2
    (-1, -2) => 1   (-2, -1) => 1   (1, 0) => 1
    (-2, -2) => 1   (0, 0) => 1     (1, -1) => 1
*/
