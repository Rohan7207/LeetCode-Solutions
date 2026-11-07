// Problem: Process Tasks Using Servers
// Link: https://leetcode.com/problems/process-tasks-using-servers/?envType=daily-question&envId=2026-11-07
// Difficulty: Medium

// Approach:
// 1. Maintain two priority queues:
//      free -> servers available for task assignment.
//      busy -> servers currently processing tasks.
//
// 2. Sort free servers by:
//      a. Smaller weight.
//      b. Smaller server index.
//
// 3. Sort busy servers by:
//      a. Earlier finish time.
//      b. Smaller server weight.
//      c. Smaller server index.
//
// 4. Process tasks in their arrival order (task i arrives at time i).
//
// 5. Before assigning a task, move all servers whose finishTime <= i
//    from busy to free.
//
// 6. If free is not empty:
//      a. Select the best server from free.
//      b. Assign the task.
//      c. Move that server into busy with finishTime = i + tasks[i].
//
// 7. If free is empty:
//      a. Select the server that finishes earliest from busy.
//      b. Assign the current task to that server.
//      c. Update its finishTime = oldFinishTime + tasks[i].
//
// 8. Store the assigned server index in the result.

// Time Complexity: O(n log n + m log n)
// Space Complexity: O(n)


class Solution {
    public int[] assignTasks(int[] servers, int[] tasks) {
        int n = servers.length;
        int m = tasks.length;

        PriorityQueue<int[]> free = new PriorityQueue<>((a, b) -> {
            if (a[0] != b[0])
                return Integer.compare(a[0], b[0]); 
            return Integer.compare(a[1], b[1]); 
        });

        PriorityQueue<int[]> busy = new PriorityQueue<>((a, b) -> {
            if (a[0] != b[0])
                return Integer.compare(a[0], b[0]); 
            if (a[1] != b[1])
                return Integer.compare(a[1], b[1]);
            return Integer.compare(a[2], b[2]);
        });

        for (int i = 0; i < n; i++) {
            free.add(new int[] { servers[i], i });
        }

        int[] res = new int[m];

        for (int i = 0; i < m; i++) {
            while (!busy.isEmpty() && busy.peek()[0] <= i) {
                int[] curr = busy.poll();
                free.add(new int[] { curr[1], curr[2] });
            }

            if (free.isEmpty()) {
                int[] curr = busy.poll();
                res[i] = curr[2];

                busy.add(new int[] { curr[0] + tasks[i], curr[1], curr[2] });
            } else {
                int[] curr = free.poll();
                res[i] = curr[1];

                busy.add(new int[] { i + tasks[i], curr[0], curr[1] });
            }
        }

        return res;
    }
}
