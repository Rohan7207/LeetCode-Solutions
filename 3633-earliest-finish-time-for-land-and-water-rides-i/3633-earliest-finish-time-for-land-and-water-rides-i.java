// Problem: Earliest Finish Time for Land and Water Rides I
// Link: https://leetcode.com/problems/earliest-finish-time-for-land-and-water-rides-i/?envType=daily-question&envId=2026-06-02
// Difficulty: Easy

// Approach:
// Try every combination of one land ride
// and one water ride.
//
// For each pair, calculate two possibilities:
//
//     1. Land ride first, then water ride.
//     2. Water ride first, then land ride.
//
// When taking the second ride,
// if it has not started yet, wait until
// its start time using max().
//
// Update the answer with the minimum
// finish time among all possibilities.

// Time Complexity: O(L * W)
// Space Complexity: O(1)


class Solution {
    public int earliestFinishTime(int[] landStartTime, int[] landDuration, int[] waterStartTime, int[] waterDuration) {
        int ans = Integer.MAX_VALUE;

        for(int i = 0; i < landStartTime.length; i++) {
            for(int j = 0; j < waterStartTime.length; j++) {
                int landFinish = landStartTime[i] + landDuration[i];
                int waterAfterLand = Math.max(landFinish, waterStartTime[j]) + waterDuration[j];

                int waterFinish = waterStartTime[j] + waterDuration[j];
                int landAfterWater = Math.max(waterFinish, landStartTime[i]) + landDuration[i];

                ans = Math.min(ans, Math.min(waterAfterLand, landAfterWater));
            }
        }

        return ans;
    }
}
