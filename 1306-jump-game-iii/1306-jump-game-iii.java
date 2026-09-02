// Problem: Jump Game III
// Link: https://leetcode.com/problems/jump-game-iii/
// Difficulty: Medium

// Approach:
// Use BFS + Visited Array.
//
// 1. Treat each index as a node.
// 2. From index i, we can move to:
//      i + arr[i]
//      i - arr[i]
// 3. Start BFS from the given start index.
// 4. If an index is outside the array or already visited, skip it.
// 5. If arr[i] == 0, we have reached the target → return true.
// 6. Mark the current index visited to avoid processing it again.
// 7. Add both possible next indices to the queue.
// 8. If BFS finishes without finding 0, return false.

// Time Complexity: O(n)
// Space Complexity: O(n)


class Solution {
    public boolean canReach(int[] arr, int start) {
        int n = arr.length;
        boolean[] vis = new boolean[n];
        Queue<Integer> q = new ArrayDeque<>();
        q.offer(start);

        while (!q.isEmpty()) {
            int i = q.poll();

            if (i < 0 || i >= n || vis[i])
                continue;

            if (arr[i] == 0)
                return true;

            vis[i] = true;

            q.offer(i + arr[i]);
            q.offer(i - arr[i]);
        }

        return false;
    }
}
