// Problem: Network Delay Time
// Link: https://leetcode.com/problems/network-delay-time/
// Difficulty: Medium

// Approach:
// We need to find how long it takes for a signal
// starting from node k to reach every node.
// This is a shortest path problem because:
//     times[i] = [u, v, w]
// means:
//     signal can travel from u to v
//     in w time.
// Since all edge weights are positive,
// use Dijkstra's Algorithm.
// Step 1: Build adjacency list
//     adj.get(u).add(new int[]{v, w});
// Here:
//     [v, w]
//     v = neighbor node
//     w = weight from u to v
// Step 2: Create distances array
//     distances[i] stores shortest time
//     from source node k to node i.
// Initially:
//     distances[i] = infinity
// except:
//     distances[k] = 0
// because source reaches itself in 0 time.
// Step 3: Use Min Priority Queue
// Priority Queue stores:
//     [distanceFromSource, node]
// The node having smallest current distance
// is processed first.
// Step 4: Relax all neighbors
// For every edge:
//     currentNode -> child with weight
// calculate:
//     newDist = currentDist + weight
// If newDist is smaller than current known distance:
//     update distances[child]
//     push [newDist, child] into priority queue
// Step 5: Find maximum shortest distance
// After Dijkstra:
//     distances[i] = minimum time for signal
//     to reach node i.
// Signal reaches all nodes only when the farthest
// reachable node receives it.
// So return maximum value in distances[].
// If any node still has infinity distance:
//     it is unreachable
//     return -1.

// Time Complexity: O((V + E) log V)
// Space Complexity: O(V + E)


class Solution {
    public int networkDelayTime(int[][] times, int n, int k) {
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

        int[] distances = new int[n + 1];
        Arrays.fill(distances, Integer.MAX_VALUE);
        distances[k] = 0; 

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> Integer.compare(a[0], b[0]));
        pq.offer(new int[] { 0, k });
        
        while (!pq.isEmpty()) {
            int[] current = pq.poll();
            int currentDist = current[0];
            int currentNode = current[1]; 

            if (currentDist > distances[currentNode]) {
                continue;
            }

            for (int[] neigh : adj.get(currentNode)) {
                int child = neigh[0];  
                int weight = neigh[1];
                if (distances[currentNode] + weight < distances[child]) {
                    distances[child] = distances[currentNode] + weight;
                    pq.offer(new int[] { distances[child], child });
                }
            }
        }

        int max = 0;
        for (int i = 1; i <= n; i++) {
            if (distances[i] == Integer.MAX_VALUE)
                return -1;
            max = Math.max(max, distances[i]);
        }

        return max;
    }
}
