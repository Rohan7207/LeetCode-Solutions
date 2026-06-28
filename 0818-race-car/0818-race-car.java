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