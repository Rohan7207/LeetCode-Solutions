class Solution {
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        //We use Bellaman Ford Algorithm
        int[] costs = new int[n];
        Arrays.fill(costs, Integer.MAX_VALUE);
        costs[src] = 0;

        // costs = {0, 100, 200, 700}
        for (int i = 0; i <= k; i++) {
            int[] temp = costs.clone(); //Clones the costs arrray to use for this iteration

            for (int[] flight : flights) {
                int u = flight[0]; //Source
                int v = flight[1]; //Destination
                int cost = flight[2]; //Weight

                if (costs[u] == Integer.MAX_VALUE)
                    continue; //If source node is unreachable

                if (costs[u] + cost < temp[v]) {
                    temp[v] = costs[u] + cost;
                }
            }

            costs = temp;
        }

        return costs[dst] == Integer.MAX_VALUE ? -1 : costs[dst];
    }
}