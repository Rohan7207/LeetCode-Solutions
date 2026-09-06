class Solution {
    public int[] kWeakestRows(int[][] mat, int k) {
        int m = mat.length;
        int n = mat[0].length;
        Integer[][] rows = new Integer[m][2];

        for (int i = 0; i < m; i++) {
            int count = 0;
            for (int j = 0; j < n; j++) {
                if (mat[i][j] == 1) {
                    count++;
                }
            }

            rows[i][0] = count; // count of soldiers
            rows[i][1] = i; // row index
        }

        Arrays.sort(rows, (a, b) -> {
            if (!a[0].equals(b[0])) {
                return a[0] - b[0]; // fewer soldiers first
            }

            return a[1] - b[1]; // smaller index first
        });

        int[] res = new int[k];

        for (int i = 0; i < k; i++) {
            res[i] = rows[i][1];
        }

        return res;
    }
}