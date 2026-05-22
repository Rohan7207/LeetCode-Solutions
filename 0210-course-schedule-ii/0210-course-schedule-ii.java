// Problem : Course Schedule II
// Link : https://leetcode.com/problems/course-schedule-ii/
// Difficulty : Medium

// Approach:
// Use Kahn's Algorithm (BFS Topological Sort)
// to find a valid order of completing courses.
// Build a directed graph where:
//     prerequisite -> dependent course
// Calculate indegree of every course.
// Indegree represents the number of
// prerequisites required for that course.
// Add all courses having indegree 0
// into a queue since they can be taken first.
// Perform BFS:
//     - Remove a course from queue.
//     - Add it to the answer array.
//     - Visit all dependent courses.
//     - Reduce their indegree.
//     - If indegree becomes 0,
//       add that course to queue.
// After processing:
//     - If all courses are included in answer,
//       return the topological ordering.
//     - Otherwise a cycle exists,
//       so return an empty array.

// Time Complexity: O(V + E)
// Space Complexity: O(V + E)


class Solution {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        List<List<Integer>> graph = new ArrayList<>();
        int inDegree[] = new int[numCourses];

        for (int i = 0; i < numCourses; i++) {
            graph.add(new ArrayList<>());
        }

        for (int[] edge : prerequisites) {
            int course = edge[0];
            int prereq = edge[1];
            graph.get(prereq).add(course);
            inDegree[course]++;
        }

        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (inDegree[i] == 0)
                q.offer(i);
        }

        int[] order = new int[numCourses];
        int index = 0;

        while (!q.isEmpty()) {
            int curr = q.poll();
            order[index++] = curr;

            for (int neigh : graph.get(curr)) {
                inDegree[neigh]--;
                if (inDegree[neigh] == 0) {
                    q.offer(neigh);
                }
            }
        }

        return index == numCourses ? order : new int[0];
    }
}
