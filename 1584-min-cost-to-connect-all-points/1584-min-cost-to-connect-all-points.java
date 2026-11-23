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
        //Use Prim's Algorithm and travel in bfs, MST using prim since the graph is as dense as it could get
        int n = points.length;
        PriorityQueue<Point> pq = new PriorityQueue<>((a, b) -> a.distance - b.distance); //min heap
        boolean[] inMST = new boolean[n];

        //Start with first point
        pq.offer(new Point(0, 0));

        int minCost = 0;
        int pointsConnected = 0;

        while (pointsConnected < n) {
            Point current = pq.poll();
            if (inMST[current.index]) {
                continue; //Skip if point is present in boolean array
            }

            inMST[current.index] = true;
            minCost += current.distance;
            pointsConnected++;

            //Update the heap with distances to new point in MST
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