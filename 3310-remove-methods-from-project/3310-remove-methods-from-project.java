// Problem: Remove Methods From Project
// Link: https://leetcode.com/problems/remove-methods-from-project/?envType=daily-question&envId=2026-08-05
// Difficulty: Medium

// Approach:
// Represent the invocations as a directed graph where an edge
// u → v means method u invokes method v.
// Compute the indegree of every node while building the graph.
// Starting from the suspicious method k, perform a BFS to mark
// every method that is directly or indirectly reachable from k
// as suspicious.
//
// During BFS:
// - Mark each visited method as suspicious.
// - Decrease the indegree of every visited neighbor.
//
// After the traversal:
//
// 1. Check every suspicious method.
//    - If any suspicious method still has a positive indegree,
//      it means that at least one invocation is coming from a
//      non-suspicious method.
//    - In this case, the suspicious methods cannot be removed
//      independently, so return all methods.
//
// 2. Otherwise, all suspicious methods can be removed safely.
//    Collect and return only the methods that were never marked
//    suspicious.

// Time Complexity:
// O(n + m)
// where m is the number of invocations.
//
// Space Complexity:
// O(n + m)
// for the adjacency list, indegree array, queue, and visited array.


class Solution {
    public List<Integer> remainingMethods(int n, int k, int[][] invocations) {
        List<Integer>[] edges = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            edges[i] = new ArrayList<>();
        }

        int[] inDegree = new int[n];
        for (int[] inv : invocations) {
            edges[inv[0]].add(inv[1]);
            inDegree[inv[1]]++;
        }

        Queue<Integer> q = new ArrayDeque<>();
        q.offer(k);

        boolean[] suspicious = new boolean[n];
        suspicious[k] = true;

        while (!q.isEmpty()) {
            int u = q.poll();
            for (int v : edges[u]) {
                inDegree[v]--;

                if (!suspicious[v]) {
                    suspicious[v] = true;
                    q.offer(v);
                }
            }
        }

        boolean canRemoveAll = false;
        List<Integer> remainingNodes = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            if (suspicious[i] && inDegree[i] > 0) {
                canRemoveAll = true;
                break;
            } else if (!suspicious[i]) {
                remainingNodes.add(i);
            }
        }

        if (canRemoveAll) {
            List<Integer> allNodes = new ArrayList<>();
            
            for (int i = 0; i < n; i++) {
                allNodes.add(i);
            }

            return allNodes;
        }

        return remainingNodes;
    }
}
