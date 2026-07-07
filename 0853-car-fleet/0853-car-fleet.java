// Problem: Car Fleet
// Link: https://leetcode.com/problems/car-fleet/
// Difficulty: Medium

// Approach:
// Every car has a time required to reach the target:
//      time = (target - position) / speed
// Instead of sorting cars,
// create an array where:
//      time[position] = time taken by the car
// Since positions are unique,
// every position stores at most one car.
// Traverse from the position nearest to the target
// towards the beginning.
// Maintain:
//      prevTime = Time taken by the fleet ahead.
// For every existing car:
//     • If its time is greater than prevTime,
//       it cannot catch the fleet ahead.
//       Therefore,
//       it forms a new fleet.
//       Increase fleet count and update prevTime.
//     • Otherwise,
//       it catches the fleet ahead before
//       reaching the target.
//       Hence it becomes part of that fleet.
// After scanning all positions,
// return the total number of fleets.

// Time Complexity: O(target + n)
// Space Complexity: O(target)


class Solution {
    public int carFleet(int target, int[] position, int[] speed) {
        float[] time = new float[target + 1];
        for (int i = 0; i < speed.length; i++) {
            time[position[i]] = (float) (target - position[i]) / speed[i];
        }

        int count = 0;
        float prevtime = 0;
        for (int i = time.length - 1; i >= 0; i--) {
            if (time[i] > prevtime) {
                count++;
                prevtime = time[i];
            }
        }
        
        return count;
    }
}
