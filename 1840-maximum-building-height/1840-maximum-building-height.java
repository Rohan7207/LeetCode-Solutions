class Solution {
    public int maxBuilding(int n, int[][] restrictions) {
        int l = restrictions.length;

        int[][] arr = new int[l + 1][2];

        // add building 1 restriction
        arr[0] = new int[] { 1, 0 };

        for (int i = 0; i < l; i++) {
            arr[i + 1] = restrictions[i];
        }

        Arrays.sort(arr, (a, b) -> a[0] - b[0]);

        int m = arr.length;
        for (int i = 1; i < m; i++) {
            int prevId = arr[i - 1][0];
            int prevHeight = arr[i - 1][1];

            int currId = arr[i][0];
            int currHeight = arr[i][1];

            int distance = currId - prevId;

            arr[i][1] = Math.min(currHeight, prevHeight + distance);
        }

        for (int i = m - 2; i >= 0; i--) {
            int currId = arr[i][0];
            int currHeight = arr[i][1];

            int nextId = arr[i + 1][0];
            int nextHeight = arr[i + 1][1];

            int distance = nextId - currId;

            arr[i][1] = Math.min(currHeight, nextHeight + distance);
        }

        int max = 0;
        for (int i = 1; i < m; i++) {
            int id1 = arr[i - 1][0];
            int h1 = arr[i - 1][1];

            int id2 = arr[i][0];
            int h2 = arr[i][1];

            int distance = id2 - id1;

            int heightDiff = Math.abs(h1 - h2);

            int currMax = Math.max(h1, h2) + ((distance - heightDiff) / 2);

            max = Math.max(max, currMax);
        }

        // Check buildings after the last restriction
        int lastId = arr[m - 1][0];
        int lastHeight = arr[m - 1][1];

        max = Math.max(max, lastHeight + (n - lastId));

        return max;
    }
}