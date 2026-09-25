// Problem: Count Number of Teams
// Link: https://leetcode.com/problems/count-number-of-teams/
// Difficulty: Medium

// Approach:
//
// 1. A team consists of exactly 3 soldiers with indices i < j < k.
//
// 2. Use three nested loops to generate every possible triplet:
//      i → first soldier
//      j → second soldier
//      k → third soldier
//
// 3. For every triplet, there are only two valid patterns:
//
//      Increasing:
//          rating[i] < rating[j] < rating[k]
//
//      Decreasing:
//          rating[i] > rating[j] > rating[k]
//
// 4. If either condition is true, increment count.
//
// 5. Return the total number of valid teams.

// Time Complexity: O(n³)
// Space Complexity: O(1)


class Solution {
    public int numTeams(int[] rating) {
        int count = 0;
        int n = rating.length;

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                for (int k = j + 1; k < n; k++) {
                    if (((rating[i] < rating[j]) && (rating[j] < rating[k]))
                            || ((rating[i] > rating[j]) && (rating[j] > rating[k]))) {
                        count++;
                    }
                }
            }
        }

        return count;
    }
}
