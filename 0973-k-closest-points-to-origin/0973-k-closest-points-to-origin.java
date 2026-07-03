// Problem: K Closest Points to Origin
// Link: https://leetcode.com/problems/k-closest-points-to-origin/
// Difficulty: Medium

// Approach:
// We need to return only the k closest points to the origin.
// Create a Max Heap where the point having the
// largest distance from the origin stays at the top.
// Distance from origin:
//      x² + y²
// Square root is not required because it preserves
// the ordering of distances.
// Traverse every point in the input.
// Insert the current point into the Max Heap.
// If the heap size becomes greater than k,
// remove the top element.
// Since it is a Max Heap, the farthest point
// among the current k+1 points is removed.
// Thus, after every iteration, the heap always
// contains the k closest points seen so far.
// After processing all points,
// the heap contains exactly the k closest points.
// Remove every point from the heap and place
// them into the answer array.

// Time Complexity: O(N log k)
// Space Complexity: O(k)


class Solution {
    public int[][] kClosest(int[][] points, int k) {
        PriorityQueue<int[]> maxHeap = new PriorityQueue<>(
                (a, b) -> Integer.compare(b[0] * b[0] + b[1] * b[1], a[0] * a[0] + a[1] * a[1]));

        for (int[] point : points) {
            maxHeap.add(point);
            if (maxHeap.size() > k) {
                maxHeap.poll();
            }
        }

        int[][] res = new int[k][2];
        for (int i = 0; i < k; i++) {
            res[i] = maxHeap.poll();
        }

        return res;
    }
}
