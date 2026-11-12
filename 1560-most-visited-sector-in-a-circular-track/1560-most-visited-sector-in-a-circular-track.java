class Solution {
    public List<Integer> mostVisited(int n, int[] rounds) {
        int[] diff = new int[n + 2];
        int start = rounds[0];
        diff[start] += 1;
        diff[start + 1] -= 1;

        for(int i = 0; i < rounds.length - 1; i++) {
            int u = rounds[i];
            int v = rounds[i + 1];

            if(u < v) {
                // Normal forward range: (u, v] -> [u + 1, v]
                diff[u + 1] += 1;
                diff[v + 1] -= 1;
            } else if(u > v) {
                // Circular wrap: (u, n] and [1, v]
                diff[u + 1] += 1;
                diff[1] += 1;
                diff[v + 1] -= 1;
            }
        }

        // Compute prefix sums to get the final counts for indices 1 to n
        int[] count = new int[n];
        int currCount = 0;
        int maxVisSector = 0;
        for(int i = 1; i <= n; i++) {
            currCount += diff[i];
            count[i - 1] = currCount;

            maxVisSector = Math.max(maxVisSector, count[i - 1]);
        }

        List<Integer> res = new ArrayList<>();
        for(int i = 0; i < n; i++) {
            if(count[i] == maxVisSector) {
                res.add(i + 1);
            }
        }

        return res;
    }
}