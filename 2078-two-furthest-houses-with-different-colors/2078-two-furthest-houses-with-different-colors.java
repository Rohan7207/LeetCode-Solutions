// Problem: Two Furthest Houses With Different Colors
// Link: https://leetcode.com/problems/two-furthest-houses-with-different-colors/?envType=daily-question&envId=2026-04-20
// Difficulty: Easy

// Approach:
// 1. Compare the first element with all elements from right side
//    and find the farthest index where values are different.
// 2. Compare the last element with all elements from left side
//    and find the farthest index where values are different.
// 3. Take the maximum of both distances.
// 4. Return the maximum distance.

// Time Complexity: O(n)

// Space Complexity: O(1)

class Solution {
    public int maxDistance(int[] colors) {
        int maxDist = 0;
        int n = colors.length;

        // Compare with first element
        for(int i = n - 1; i >= 0; i--) {
            if(colors[i] != colors[0]) {
                maxDist = Math.max(maxDist, i);
            }
        }

        // Compare with last element
        for(int i = 0; i < n; i++) {
            if(colors[i] != colors[n - 1]) {
                maxDist = Math.max(maxDist, n - 1 - i);
            }
        }
       

        return maxDist;
    }
}
