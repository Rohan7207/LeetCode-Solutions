// Problem: Course Schedule
// Link: https://leetcode.com/problems/course-schedule/
// Difficulty: Medium

// Approach:
// Build a directed graph using adjacency list
// and calculate indegree of every course.
// Indegree represents the number of prerequisites
// required before taking that course.
// Add all courses having indegree 0 into a queue
// since they can be taken immediately.
// Perform BFS:
//     - Remove a course from queue.
//     - Count it as completed.
//     - Visit all dependent courses.
//     - Reduce their indegree by 1.
//     - If indegree becomes 0,
//       add that course to queue.
// After BFS completes:
//     - If all courses are processed,
//       no cycle exists and all courses
//       can be completed.
//     - Otherwise a cycle exists.

// Time Complexity: O(V + E)
// Space Complexity: O(V + E)


class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        List<List<Integer>> graph = new ArrayList<>();
        int[] indegree = new int[numCourses];

        for (int i = 0; i < numCourses; i++) {
            graph.add(new ArrayList<>());
        }

        for (int[] pre : prerequisites) {
            int course = pre[0];
            int prereq = pre[1];

            graph.get(prereq).add(course);
            indegree[course]++;
        }

        Queue<Integer> q = new LinkedList<>();

        for (int i = 0; i < numCourses; i++) {
            if (indegree[i] == 0) {
                q.offer(i);
            }
        }

        int completedCourses = 0;

        while (!q.isEmpty()) {
            int curr = q.poll();
            completedCourses++;

            for (int next : graph.get(curr)) {
                indegree[next]--;

                if (indegree[next] == 0) {
                    q.offer(next);
                }
            }
        }

        return completedCourses == numCourses;
    }
}
