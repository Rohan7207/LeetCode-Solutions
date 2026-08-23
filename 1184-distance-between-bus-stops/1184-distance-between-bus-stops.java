// Problem: Distance Between Bus Stops
// Link: https://leetcode.com/problems/distance-between-bus-stops/
// Difficulty: Easy

// Approach:
// Treat the bus stops as a circular array.
//
// 1. Calculate the total distance of the complete circle.
//
// 2. Start from `start` and move clockwise.
//    Add each distance until reaching `destination`.
//
// 3. The distance calculated is one possible route:
//
//      start → destination (clockwise)
//
// 4. The other route is the remaining part of the circle:
//
//      counterclockwise = totalDistance - clockwiseDistance
//
// 5. Return the smaller of the two routes.

// Time Complexity: O(n)
// Space Complexity: O(1)


class Solution {
    public int distanceBetweenBusStops(int[] distance, int start, int destination) {
        int totalDistance = 0;

        for (int d : distance) {
            totalDistance += d;
        }

        int i = start;
        int clockwiseDistance = 0;

        while (i != destination) {
            clockwiseDistance += distance[i];
            i = (i + 1) % distance.length;
        }

        return Math.min(clockwiseDistance, totalDistance - clockwiseDistance);
    }
}
