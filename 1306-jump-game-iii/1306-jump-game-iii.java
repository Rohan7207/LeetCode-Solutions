class Solution {
    public boolean canReach(int[] arr, int start) {
        int n = arr.length;

        boolean[] vis = new boolean[n];
        Queue<Integer> q = new ArrayDeque<>();

        q.offer(start);

        while(!q.isEmpty()) {
            int i = q.poll();

            if(i < 0 || i >= n || vis[i]) continue;

            if(arr[i] == 0) return true;

            vis[i] = true;

            q.offer(i + arr[i]);
            q.offer(i - arr[i]);
        }

        return false;
    }
}

/*
We are given:arr, start
At every index:jump left OR jump right
Goal:reach any index having value 0
This becomes:Graph Reachability
Use: BFS / DFS + visited set

Key Insight
Each index behaves like a graph node:

i → i + arr[i]
i → i - arr[i]
Need to avoid cycles:

visited[i]
Otherwise:

infinite loop

Start = 5

Index 5 → value 1
5 - 1 = 4
5 + 1 = 6

        5
       / \
      4   6
     /
    1
     \
      3 ⭐
*/