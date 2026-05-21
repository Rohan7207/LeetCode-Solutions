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


// class Solution {
//     public boolean canFinish(int numCourses, int[][] prerequisites) {
//         // this is classocal graph problem asked many times
//         /* 
//             num = 2, pre = [1, 0], [0, 1]
//             0 -> 1 -> 0 it is not possible bcz it forms cycle and we cannot topological sort we return false

//             num = 4, pre =[[1, 0], [2, 0], [3, 1], [3, 2]]

//               /> 1 \>            
//             0         3   it is graph where we use topological sort where order is 0,1,2,3 or 0,2,1,3 it true
//               \> 2  />

//             So we need to find that graph contains cycle(false) or not(true) we need not to need to return order , we return it when we are solving  course schedule II

//         */
//         //We use hashmap with key as courses and value as prequisites
//         //and we use hashset for checking the node is visited or not
//         //if it is visited then there is cycle and return false

//         HashMap<Integer, List<Integer>> courseGraph = new HashMap<>();

//         // Building adjacency list
//         for(int[] pre : prerequisites){
//             // Checks if we have already seen this prerequisite (courseB) before.
//             // [[1, 0], [2, 0], [3, 1]]: 0 -> [1, 2] , 1 -> [3]

//             if(courseGraph.containsKey(pre[1])){
//                 // If we have, simply add courseA to the existing list of courses that need courseB.
//                 courseGraph.get(pre[1]).add(pre[0]);
//             }
//             else{
//                 // create a new list to store it and add
//                 List<Integer> nextCourses = new LinkedList<>();
//                 nextCourses.add(pre[0]);
//                 courseGraph.put(pre[1], nextCourses);
//             }
//         }

//         HashSet<Integer> vis = new HashSet<>();

//         for(int currentCourse = 0; currentCourse < numCourses; currentCourse++){
//            if(!courseSchedule(currentCourse, vis, courseGraph)){
//                 return false;
//             } 
//         }

//         return true;
//     }

//      // It checks whether graph contains a cycle or not
//     private boolean courseSchedule(int course, HashSet<Integer> vis, HashMap<Integer, List<Integer>> courseGraph) {
//         /* 
//             courseGraph.get(course) returns one of three things depending on the state of your graph:
// A List of Neighbors: If the course exists in the map and has dependencies, it returns a List<Integer> containing all courses that can be taken after completing the current one. For example, if Course 0 is a prerequisite for 1 and 2, it returns [1, 2].
// null (Course Not a Prerequisite): If the course was never added to the map (meaning it isn't a prerequisite for anything), get() returns null. Your code handles this with if(courseGraph.get(course) == null) return true;.
// null (Optimized/Memoized): Because you added the line courseGraph.put(course, null);, once a course is fully verified as "safe" (no cycles), you overwrite its list with null. In future calls, get(course) will return null, triggering an immediate true and saving you from re-calculating that entire path
//         */

//         if(vis.contains(course)) return false;

//         if(courseGraph.get(course) == null) return true;

//         vis.add(course);
//         for(int pre : courseGraph.get(course)) {
//             if(!courseSchedule(pre, vis, courseGraph)) {
//                 return false;
//             }
//         }

//         vis.remove(course);
//         courseGraph.put(course, null);
        
//         return true;
//     }
// }


// /* 
//     Let's dry run your code with 4 courses and the dependencies: 0 → 1, 0 → 2, 1 → 3, 2 → 3.
// (Note: [1, 0] means 0 is a prerequisite for 1).
// 1. Graph Construction
// Your HashMap will look like this:
// 0: [1, 2]
// 1: [3]
// 2: [3]
// 3: null (not a prerequisite for anything)
// 2. Main Loop: currentCourse = 0
// The loop starts at i = 0. It calls courseSchedule(0, vis, courseGraph).
// vis: {0}
// Neighbors of 0: [1, 2]
// Path A: Explore Course 1
// Call courseSchedule(1, ...)
// vis: {0, 1}
// Neighbor of 1: [3]
// Call courseSchedule(3, ...)
// courseGraph.get(3) is null. Returns true.
// Back in Course 1: Loop finishes.
// vis.remove(1) → vis is {0}.
// courseGraph.put(1, null) → (Course 1 is now marked "Safe").
// Returns true.
// Path B: Explore Course 2
// Back in Course 0, move to the next neighbor: 2.
// Call courseSchedule(2, ...)
// vis: {0, 2}
// Neighbor of 2: [3]
// Call courseSchedule(3, ...)
// courseGraph.get(3) is null. Returns true.
// Back in Course 2: Loop finishes.
// vis.remove(2) → vis is {0}.
// courseGraph.put(2, null) → (Course 2 is now marked "Safe").
// Returns true.
// Wrap up Course 0
// All neighbors of 0 processed.
// vis.remove(0) → vis is {}.
// courseGraph.put(0, null) → (Course 0 is now marked "Safe").
// Returns true.
// 3. Main Loop: currentCourse = 1, 2, 3
// Now the main loop moves to i = 1.
// It calls courseSchedule(1, ...).
// Immediate Check: courseGraph.get(1) is now null (because of our optimization).
// Returns true instantly.
// The same happens for i = 2 and i = 3.
// Final Result: true
// The code correctly identifies there are no cycles.
// Why your code is efficient:
// If there were a cycle (e.g., 3 → 0), when you were exploring Course 3, you would have called courseSchedule(0). Since 0 would still be in the vis set, it would have hit if(vis.contains(course)) return false; and the whole thing would have collapsed correctly.


// To see how your code handles a cycle, let's use the simplest case: Course 0 needs 1, and Course 1 needs 0 (prerequisites = [[0, 1], [1, 0]]). 
// Dry Run: Detecting a Cycle 
// Initial Setup:
// courseGraph: {1: [0], 0: [1]}
// vis: {} (Empty HashSet)
// The main loop starts at currentCourse = 0. 
// 1. Visiting Course 0
// vis.contains(0) is false.
// vis.add(0): vis is now {0}.
// Explore neighbors of 0: The only neighbor is 1.
// Call courseSchedule(1, ...). 
// 2. Visiting Course 1
// vis.contains(1) is false.
// vis.add(1): vis is now {0, 1}.
// Explore neighbors of 1: The only neighbor is 0.
// Call courseSchedule(0, ...).
// 3. The "Gotcha" Moment (Back at Course 0)
// if(vis.contains(0)): This is now TRUE!
// Returns false immediately. 

// 4. The Collapse
// Back in Course 1: It receives false and returns false.
// Back in Course 0: It receives false and returns false.
// The main loop receives false and returns false for the entire function.
// The "Safety Net" Explained
// Because you use Backtracking (vis.add before the loop and vis.remove after), vis only ever contains courses in the current path. 

// If you encounter a course that is already in vis, it means you've successfully walked in a circle and found a cycle.
// If you finish a path without hitting a duplicate, you remove the course from vis so that other separate paths don't mistakenly think they've found a cycle.
// */