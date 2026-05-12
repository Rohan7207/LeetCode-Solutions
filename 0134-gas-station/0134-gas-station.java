// Problem: Gas Station
// Link: https://leetcode.com/problems/gas-station/
// Difficulty: Medium

// Approach:
// Traverse all gas stations while tracking:
//     - totalGas  -> total available gas
//     - totalCost -> total travel cost
//     - tank      -> current remaining fuel
//
// At each station:
//     - Add current gas and subtract travel cost.
//     - If tank becomes negative:
//           - Current start index is invalid.
//           - Reset start index to next station.
//           - Reset tank to 0.
//
// After traversal:
//     - If totalGas < totalCost,
//       completing circuit is impossible.
//     - Otherwise return the valid start index.

// Time Complexity: O(n)
// Space Complexity: O(1)


class Solution {
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int totalGas = 0;
        int totalCost = 0;
        int startIndex = 0;
        int tank = 0;

        for(int i = 0; i < gas.length; i++){
            totalGas += gas[i];
            totalCost += cost[i];
            tank += gas[i] - cost[i];

            if(tank < 0){
                startIndex = i + 1;
                tank = 0;
            }
        }

        if(totalGas < totalCost){
            return -1;
        }

        return startIndex;
    }
}
