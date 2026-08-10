// Problem: Pairs of Songs With Total Durations Divisible by 60
// Link: https://leetcode.com/problems/pairs-of-songs-with-total-durations-divisible-by-60/
// Difficulty: Medium

// Approach:
// Use the remainder of each song duration when divided by 60.
//
// 1. For every song, calculate:
//       r = time[i] % 60
//
// 2. For two durations to have a sum divisible by 60,
//    their remainders must add up to 60.
//
//    For remainder r, the required remainder is:
//       needed = (60 - r) % 60
//
// 3. Maintain a frequency array `rem` where:
//       rem[r] = number of previous songs having remainder r
//
// 4. Before storing the current remainder, add:
//       rem[needed]
//
//    This counts all previous songs that can form a valid pair
//    with the current song.
//
// 5. Then store the current remainder:
//       rem[r]++
//
// 6. Since every song is processed once, each pair is counted
//    exactly once.

// Time Complexity: O(n)
// Space Complexity: O(60) = O(1)


class Solution {
    public int numPairsDivisibleBy60(int[] time) {
        int[] rem = new int[60];
        int count = 0;

        for (int i = 0; i < time.length; i++) {
            int r = time[i] % 60;
            int needed = (60 - r) % 60;

            count += rem[needed];

            rem[r]++;
        }

        return count;
    }
}
