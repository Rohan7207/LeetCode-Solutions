// Problem: Race Car
// Link: https://leetcode.com/problems/race-car/
// Difficulty: Hard

// Approach:
// Solve the problem using BFS because we need the minimum number
// of operations to reach the target.
// State representation:
//     [position, speed, steps]
// Position and speed together define the complete state of the car.
// Start state:
//     position = 0
//     speed = 1
//     steps = 0
// Use Queue for BFS traversal.
// For every state, there are two possible operations:
// 1. Accelerate (A):
//     newPosition = position + speed
//     newSpeed = speed * 2
//     Add this state if it is not visited.
// 2. Reverse (R):
//     Position remains same.
//     If speed is positive:
//          newSpeed = -1
//     Else:
//          newSpeed = 1
//     Add this state if it is not visited.
// Use HashSet:
//     Store (position, speed)
//     because the same state reached again will not give a better answer.
// Add boundary limit:
//     Only explore useful positions within target * 2
//     to avoid infinite unnecessary states.
// BFS guarantees the first time we reach target,
// we get the minimum number of steps.

// Time Complexity: O(target * log(target))
// Space Complexity: O(target * log(target))


class Solution {
    public int racecar(int target) {
        Queue<int[]> q = new LinkedList<>();
        Set<String> set = new HashSet<>();
        q.offer(new int[] { 0, 1, 0 }); // {[position, speed, steps]} 
        set.add("0,1");

        int limit = target * 2;

        while (!q.isEmpty()) {
            int[] curr = q.poll();

            int pos = curr[0];
            int speed = curr[1];
            int steps = curr[2];

            if (pos == target) {
                return steps;
            }

            // Accelerate state
            int newPos = pos + speed;
            int newSpeed = speed * 2;

            String state1 = newPos + "," + newSpeed;

            if (Math.abs(newPos) <= limit && !set.contains(state1)) {
                set.add(state1);
                q.offer(new int[] { newPos, newSpeed, steps + 1 });
            }

            // Reverse state;
            newPos = pos;
            newSpeed = (speed > 0) ? -1 : 1;

            String state2 = newPos + "," + newSpeed;

            if (!set.contains(state2)) {
                set.add(state2);
                q.offer(new int[] { newPos, newSpeed, steps + 1 });
            }
        }

        return -1;
    }
}

/*
    public int racecar(int target) {
        int[] dp = new int[target + 1];
        Arrays.fill(dp, 1, dp.length, -1);
        return raceCar(target, dp);
    }

    private int raceCar(int i, int[] dp) {
        if (dp[i] >= 0) {
            return dp[i];
        }

        dp[i] = Integer.MAX_VALUE;

        int m = 1, j = 1;

        for (; j < i; j = (1 << ++m) - 1) {
            for (int q = 0, p = 0; p < j; p = (1 << ++q) - 1) {
                dp[i] = Math.min(dp[i], m + 1 + q + 1 + raceCar(i - (j - p), dp));
            }
        }

        dp[i] = Math.min(dp[i], m + (i == j ? 0 : 1 + raceCar(j - i, dp)));

        return dp[i];
    }
*/
