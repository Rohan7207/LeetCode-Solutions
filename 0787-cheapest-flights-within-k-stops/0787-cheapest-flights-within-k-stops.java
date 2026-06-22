// Problem: Cheapest Flights Within K Stops
// Link: https://leetcode.com/problems/cheapest-flights-within-k-stops/
// Difficulty: Medium

// Approach:
// We need to find the cheapest price from src to dst with at most k stops.
// Important:
// If there are at most k stops, then the path can have at most k + 1 edges.
// Example:
// src -> A -> dst
// stops = 1
// edges = 2
// So we relax all flights k + 1 times.
// We use a Bellman-Ford style approach.
// costs[i] stores the minimum cost to reach city i using the flights processed
// up to the previous iteration.
// Initially:
//     cost[src] = 0
//     all other cities = infinity
// For each iteration from 0 to k:
//     create a copy of costs called temp
// Why clone?
//     Because in one iteration, we should only use paths from the previous step.
//     This prevents using more than the allowed number of edges in the same round.
// Then for every flight:
//     u -> v with price cost
// If city u is reachable:
//     try to update temp[v] using:
//         costs[u] + price
// After processing all flights:
//     costs = temp
// After k + 1 rounds, costs[dst] contains the cheapest cost
// using at most k stops.
// If dst is still infinity, return -1.
// Otherwise return costs[dst].

// Time Complexity: O((k + 1) * flights.length)
// Space Complexity: O(n)


class Solution {
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        int[] costs = new int[n];
        Arrays.fill(costs, Integer.MAX_VALUE);
        costs[src] = 0;

        for (int i = 0; i <= k; i++) {
            int[] temp = costs.clone(); 

            for (int[] flight : flights) {
                int u = flight[0];
                int v = flight[1]; 
                int cost = flight[2]; 

                if (costs[u] == Integer.MAX_VALUE)
                    continue; 

                if (costs[u] + cost < temp[v]) {
                    temp[v] = costs[u] + cost;
                }
            }

            costs = temp;
        }

        return costs[dst] == Integer.MAX_VALUE ? -1 : costs[dst];
    }
}
