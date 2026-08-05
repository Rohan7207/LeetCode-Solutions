class Solution {
    public List<Integer> remainingMethods(int n, int k, int[][] invocations) {
        List<Integer>[] edges = new ArrayList[n];

        for(int i = 0; i < n; i++) {
            edges[i] = new ArrayList<>();
        }

        int[] inDegree = new int[n];
        for(int[] inv : invocations) {
            edges[inv[0]].add(inv[1]);
            inDegree[inv[1]]++;
        }

        Queue<Integer> q = new ArrayDeque<>();
        q.offer(k);

        boolean[] suspicious = new boolean[n];
        suspicious[k] = true;

        while(!q.isEmpty()) {
            int u = q.poll();
            for(int v : edges[u]) {
                inDegree[v]--;

                if(!suspicious[v]) {
                    suspicious[v] = true;
                    q.offer(v);
                }
            }
        }

        boolean canRemoveAll = false;
        List<Integer> remainingNodes = new ArrayList<>();

        for(int i = 0; i < n; i++) {
            if(suspicious[i] && inDegree[i] > 0) {
                canRemoveAll = true;
                break;
            } else if(!suspicious[i]){
                remainingNodes.add(i);
            }
        }

        if(canRemoveAll) {
            List<Integer> allNodes = new ArrayList<>();
            for(int i = 0; i < n; i++) {
                allNodes.add(i);
            }

            return allNodes;
        }

        return remainingNodes;
    }
}

/*
    Approach: Searching
Intuition
The given invocations array defines a directed graph. Starting from node k, the node k itself and all nodes reachable from it are called suspicious methods. According to the problem statement, we need to determine whether there exists a normal method that calls a suspicious method. In graph terms, there must be no edge from a normal node to a suspicious node. Only when this condition is satisfied can all suspicious methods be removed.

First, we identify all suspicious methods. Starting from node k, we perform either a depth-first search (DFS) or a breadth-first search (BFS) to traverse all reachable nodes without revisiting any node.

Next, we need to determine whether any normal method can reach a suspicious method. There are two possible approaches:

Maintain the in-degree of every node. During the traversal from node k, decrement the in-degree of each visited neighbor, which is equivalent to removing the traversed edge. After the traversal is complete, the remaining in-degree of each suspicious node represents the number of incoming edges from normal nodes. If any suspicious node has a non-zero in-degree, then there exists a normal method that calls a suspicious method.

Traverse the invocations array again. If there is an edge from a normal node to a suspicious node, then a normal method can reach a suspicious method. A hash set (or any constant-time lookup structure) can be used to quickly determine whether a node is suspicious.

Finally, there are two possible cases:

If no normal method calls any suspicious method, return all remaining methods after removing the suspicious ones.

Otherwise, no suspicious methods can be removed, so return all methods.
*/