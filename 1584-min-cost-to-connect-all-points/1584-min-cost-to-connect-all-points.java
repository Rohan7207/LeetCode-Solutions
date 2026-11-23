// Problem: Min Cost to Connect All Points
// Link: https://leetcode.com/problems/min-cost-to-connect-all-points/
// Difficulty: Medium

// Approach:
// 1. Treat every point as a vertex in a complete, weighted, undirected graph.
// 2. The edge weight between two points is their Manhattan distance:
//    |x1 - x2| + |y1 - y2|.
// 3. Use Prim's Algorithm to construct the Minimum Spanning Tree (MST).
// 4. Start from point 0 with distance 0 and insert it into a min heap.
// 5. While all points are not connected:
//    a. Extract the point with the minimum connection cost.
//    b. If it is already in the MST, skip it.
//    c. Otherwise, include it in the MST and add its distance to minCost.
//    d. Calculate its distance to every unvisited point.
//    e. Add these possible connections to the min heap.
// 6. Once all points are included, return minCost.

// Time Complexity: O(n² log n) for this implementation.
// Space Complexity: O(n²) in the worst case for the priority queue.


class Solution {

    private static class Point {
        int index;
        int distance;

        Point(int index, int distance) {
            this.index = index;
            this.distance = distance;
        }
    }

    public int minCostConnectPoints(int[][] points) {
        int n = points.length;
        PriorityQueue<Point> pq = new PriorityQueue<>((a, b) -> a.distance - b.distance); //min heap
        boolean[] inMST = new boolean[n];

        pq.offer(new Point(0, 0));

        int minCost = 0;
        int pointsConnected = 0;

        while (pointsConnected < n) {
            Point current = pq.poll();
            if (inMST[current.index]) {
                continue; 
            }

            inMST[current.index] = true;
            minCost += current.distance;
            pointsConnected++;

            for (int i = 0; i < n; i++) {
                if (!inMST[i]) {
                    int distance = Math.abs(points[current.index][0] - points[i][0])
                            + Math.abs(points[current.index][1] - points[i][1]);
                    pq.offer(new Point(i, distance));
                }
            }
        }

        return minCost;
    }
}
