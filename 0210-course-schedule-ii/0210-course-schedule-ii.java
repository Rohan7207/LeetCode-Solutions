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

/*
Build a graph and an array to store the in-degree (number of prerequisites) of each course.
Start with all courses having in-degree = 0, i.e., no prerequisites.
Process each course, reduce the in-degree of its neighbors, and if any neighbor’s in-degree becomes zero, add it to the processing queue.
If we can process all nodes, return the order; otherwise, return an empty array.
*/