class Solution {
    public int twoCitySchedCost(int[][] costs) {
        int n = costs.length / 2;

        // Sort by A - B, assign First n → B and Remaining n → A
        Arrays.sort(costs, (a, b) -> (a[0] - a[1]) - (b[0] - b[1]));

        int total = 0;
        for (int i = 0; i < costs.length; i++) {
            if (i < n) {
                total += costs[i][0]; // A
            } else {
                total += costs[i][1]; // B
            }
        }

        return total;
    }
}