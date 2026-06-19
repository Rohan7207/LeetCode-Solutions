// Problem: Find the Highest Altitude
// Link: https://leetcode.com/problems/find-the-highest-altitude/?envType=daily-question&envId=2026-06-19
// Difficulty: Easy

// Approach:
// We are given an array gain[].
// gain[i] represents the altitude change
// between two points.
// We start from altitude 0.
// Maintain:
//     altitude    -> current altitude
//     maxAltitude -> highest altitude seen so far
// Initially:
//     altitude = 0
//     maxAltitude = 0
// Traverse gain array:
//     Add gain[i] to altitude.
//     altitude += gain[i]
//     Now update maximum altitude:
//     maxAltitude = max(maxAltitude, altitude)
// Finally return maxAltitude.

// Time Complexity: O(n)
// Space Complexity: O(1)


class Solution {
    public int largestAltitude(int[] gain) {
        int altitude = 0;
        int maxAltitude = 0;

        for (int i = 0; i < gain.length; i++) {
            altitude += gain[i];
            maxAltitude = Math.max(maxAltitude, altitude);
        }
        
        return maxAltitude;
    }
}
