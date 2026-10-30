// Problem: Shortest Path With At Most K Consecutive Identical Characters
// Link: https://leetcode.com/problems/shortest-path-with-at-most-k-consecutive-identical-characters/?envType=daily-question&envId=2026-10-29
// Difficulty: Medium

// Approach:
// 1. Build an adjacency list to represent the directed weighted graph.
//    - Each edge stores the destination node and its weight.
//
// 2. Use an expanded Dijkstra state:
//    - Instead of tracking only the current node, track:
//      {distance, node, count}
//    - count represents the number of consecutive nodes
//      having the same label along the current path.
//
// 3. Maintain a 2D distance array:
//    - res[node][count] stores the minimum distance to reach
//      a node with the given consecutive-label count.
//    - Initialize all distances to infinity.
//
// 4. Initialize the Min-Heap.
//    - Start from node 0 with distance 0 and count 1.
//    - Always process the state with the smallest distance.
//
// 5. Explore adjacent nodes.
//    - If the adjacent node has the same label as the current node,
//      increment count.
//    - Otherwise, reset count to 1.
//    - Skip the transition if count exceeds k.
//
// 6. Relax the distance.
//    - Calculate newDist = dist + weight.
//    - If it improves res[adjNode][newCount], update it
//      and push the new state into the Min-Heap.
//
// 7. Return the answer.
//    - When the destination node (n - 1) is removed from the Min-Heap,
//      its distance is the minimum valid distance.
//    - If the heap becomes empty, return -1.

// Time Complexity: O((n*k + m*k) log(n*k))
// Space Complexity: O(n*k + m)


class Solution {
    public int shortestPath(int n, int[][] edges, String labels, int k) {
        List<List<int[]>> adjList = new ArrayList<>();

        // Initialize adjList with n nodes of list to store des, weight
        for (int i = 0; i < n; i++) {
            adjList.add(new ArrayList<>());
        }

        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            int wt = edge[2];

            adjList.get(u).add(new int[] { v, wt });
        }

        int[][] res = new int[n][k + 1];
        for (int[] row : res) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }

        // Min-Heap sorting by distance (index 0)
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> Integer.compare(a[0], b[0]));

        // Initialize starting node source -> source of count = 1, cost is 0
        res[0][1] = 0;

        // {cost, node, count}
        pq.add(new int[] { 0, 0, 1 });

        while (!pq.isEmpty()) {
            int[] current = pq.poll();
            int dist = current[0];
            int node = current[1];
            int count = current[2];

            if (dist > res[node][count]) {
                continue;
            }

            if (node == n - 1) {
                return dist;
            }

            for (int[] info : adjList.get(node)) {
                int adjNode = info[0];
                int wt = info[1];

                int newCount = (labels.charAt(adjNode) == labels.charAt(node) ? count + 1 : 1);

                // Continue or stop exploring path if count > k
                if (newCount > k) {
                    continue;
                }

                int newDist = dist + wt;
                if (newDist < res[adjNode][newCount]) {
                    res[adjNode][newCount] = newDist;
                    pq.add(new int[] { newDist, adjNode, newCount });
                }
            }
        }

        return -1;
    }
}
