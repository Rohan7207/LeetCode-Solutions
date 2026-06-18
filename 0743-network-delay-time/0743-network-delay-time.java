class Solution {
    public int networkDelayTime(int[][] times, int n, int k) {
        //We use dijkstra's algorithm   
        //Create a graph represented as an adjacency list

        List<List<int[]>> adj = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            adj.add(new ArrayList<>());
        }

        for (int[] edge : times) {
            int u = edge[0];
            int v = edge[1];
            int w = edge[2];
            adj.get(u).add(new int[] { v, w });
        }

        //Initialise distances with infinity
        int[] distances = new int[n + 1];
        Arrays.fill(distances, Integer.MAX_VALUE);
        distances[k] = 0; //Distance of itself is 0

        //Use min priority queue to select node with min distance
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> Integer.compare(a[0], b[0]));
        pq.offer(new int[] { 0, k }); //Start from node k with distance of 0

        while (!pq.isEmpty()) {
            int[] current = pq.poll();
            int currentDist = current[0]; // shortest distance from source k to currentNode
            int currentNode = current[1]; // current node

            //if we have already found a shorter path to that node then continue
            if (currentDist > distances[currentNode]) {
                continue;
            }

            //Update the distance of neighbouring  nodes
            for (int[] neigh : adj.get(currentNode)) {
                int child = neigh[0];  // destination node
                int weight = neigh[1]; // edge currentNode -> child
                if (distances[currentNode] + weight < distances[child]) {
                    distances[child] = distances[currentNode] + weight;
                    pq.offer(new int[] { distances[child], child });
                }
            }
        }

        //Find the maxmium distance from source to all other nodes
        int max = 0;
        for (int i = 1; i <= n; i++) {
            if (distances[i] == Integer.MAX_VALUE)
                return -1;
            max = Math.max(max, distances[i]);
        }

        return max;
    }
}