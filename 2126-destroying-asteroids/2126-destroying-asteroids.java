// Problem: Destroying Asteroids
// Link: https://leetcode.com/problems/destroying-asteroids/?envType=daily-question&envId=2026-05-31
// Difficulty: Medium

// Approach:
// Sort all asteroids in ascending order.
// Start with the initial planet mass.
// Traverse the sorted asteroids:
//     - If current mass is smaller than
//       the asteroid, we cannot destroy it,
//       so return false.
//     - Otherwise destroy the asteroid
//       and add its mass to the planet.
// If all asteroids are processed,
// return true.

// Time Complexity: O(n log n)
// Space Complexity: O(1)


class Solution {
    public boolean asteroidsDestroyed(int mass, int[] asteroids) {
        Arrays.sort(asteroids);
        if (mass < asteroids[0]) return false;

        long currMass = mass;

        for (int i = 0; i < asteroids.length; i++) {
            if (currMass < asteroids[i]) {
                return false;
            }

            currMass += asteroids[i];
        }

        return true;
    }
}
