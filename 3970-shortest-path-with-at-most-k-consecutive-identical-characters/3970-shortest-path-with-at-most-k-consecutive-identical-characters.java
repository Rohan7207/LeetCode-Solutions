class Solution {
    public int shortestPath(int n, int[][] edges, String labels, int k) {
        List<List<int[]>> adjList = new ArrayList<>();

        // Initialize adjList with n nodes of list to store des, weight
        for (int i = 0; i < n; i++) {
            adjList.add(new ArrayList<>());
        }

        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            int wt = edge[2];

            adjList.get(u).add(new int[] { v, wt });
        }

        int[][] res = new int[n][k + 1];
        for (int[] row : res) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }

        // Min-Heap sorting by distance (index 0)
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> Integer.compare(a[0], b[0]));

        // Initialize starting node source -> source of count = 1, cost is 0
        res[0][1] = 0;

        // {cost, node, count}
        pq.add(new int[] { 0, 0, 1 });

        while (!pq.isEmpty()) {
            int[] current = pq.poll();
            int dist = current[0];
            int node = current[1];
            int count = current[2];

            if (dist > res[node][count]) {
                continue;
            }

            if (node == n - 1) {
                return dist;
            }

            for (int[] info : adjList.get(node)) {
                int adjNode = info[0];
                int wt = info[1];

                int newCount = (labels.charAt(adjNode) == labels.charAt(node) ? count + 1 : 1);

                // Continue or stop exploring path if count > k
                if (newCount > k) {
                    continue;
                }

                int newDist = dist + wt;
                if (newDist < res[adjNode][newCount]) {
                    res[adjNode][newCount] = newDist;
                    pq.add(new int[] { newDist, adjNode, newCount });
                }
            }
        }

        return -1;
    }
}

/*
    BY question only we can say that we must using Dijkstra algorithm bcz we need to find a shortest path given a directed weighted graph, return minimum total edge weight path from 0 to n - 1 and concatenation of labels of nodes along path must contain atmost <= k consecutive identical charcaters.

    Ex:   n = 3, edges = [[0,1,1],[1,2,1],[0,2,3]], labels = "aab", k = 1

             1
      a - 0  --  1 - a      - The shortest path from 0 to 2 is 0 -> 1 -> 2  with min weight is 2 but 
         3 \     / 2            string path is "aab" => but aa is not k times consecutive means we have 
             2 - b              to keep k consecutive character occurence for 1 it should be ab not aab 
                                bcz aa breaks it with coming two times in a row. So answer is 3 0 -> 2

    Ex: n = 3, edges = [[0,1,1],[1,2,1],[0,2,3]], labels = "aab", k = 2

             1
      a - 0  --  1 - a      - The shortest path from 0 to 2 is 0 -> 1 -> 2  with min weight is 2 and  
         3 \     / 2           now since we arr allowed to keep 3 identical consecutive characters we can 
             2 - b              keep "aab" so answer would be 2.

    Input: n = 3, edges = [[0,1,1],[1,2,1]], labels = "aaa", k = 2   it is not possible bcz we cannot take string with aaa and there is no other correct path
             1 
      a - 0  -  1 - a
                 \
                  2 - a     

    Approach:
        Path with minimum total edge weight or shortest path with min:
          1. BFS -> All weight equal or not weighted graph

          2. Dijkstra -> weights unequal, directed weighted graph with w > 0 and it is single source shortest path means it gives a shortest path from single source to all nodes efficiently and we will use this in problem.

          3. Bellman Ford Algo ->  -ve weights or at most k edges traversal, it is not single source shortest path it gives shortest from every node to every other node.

      ** Using Dijkstra Algo in Problem:

             1
      a - 0  --  1 - a     
         3 \     / 2          
             2 - b 

      - In Dijkstra, we would store the information of min edge weight from source to the given node in form source to node, (minwt, node).
        minHeap = {(minwt, node1), (minwt, node2), (minwt, node3)}
      - The distance or res array which stores the min edge weight required to traverse from source to that node at first all will be assigned to infinity.
               0    1     2 
        dist = 0  -9999  -9999

    **  - But we are also given another constraint to check string which should be done on the fly while doing dijkstra like when we go from 0 to 1 node it clearly break requirement problem so we would stop exploring that path only. 
    ***  - But ultimately we only need count of consecutive identical charcaters bcz we can check count with given k bcz building the string in process can become large due to constraint of labels 
      i.e 5 * 10 ^ 4.

      - Till now we would only store min weight and des node in min heap from source (minwt, node), since we need count at each point we can store this info in min heap only i.e.,
        (minwt, node, count) where, to reach from source to node what is minimum weight needed and consecutive identical characters count.
        ** Since now minheap store 3 info we can solve it very easily like at any point if count becomes > k then we would stop exploring that path.

        Dry run:
            At 0th node 0: s = "a" count = 1;
            At 1st node 0 -> 1: s = "aa" count = 1 + 1 = 2 bcz previous was same ch since count > k we could stop this path exploration.
            At 2nd node des 0 -> 2: s = "ab" count = 1, it will be one bcz we have got unique ch at 2nd node.

    Now we are sure that minheap keeps track of three info (wt, node, count)

    - But still there is one issue in above approach lets with generalized example:
        Ex: no.of nodes = n    k = 5

            />  1 ->  x .... n - 1
         0          />  
           \>   2

               0   1  2 3 ... n - 1
        dist = 0  -1 -1 -1      -1

    - Now lets assume we start at 0 for assumption lets take 1 in path to understand and through any possible path we reached x whose current count = 2 <= k, and we went through 0 -> 2 and other possible path we have reached to x node and count = 3. But lets assume that the mincost required to travel in both path is same, cost => 0 -> x = 3.
    - Which means there could possiblity of coming multiple counts with valid mincost path and both count are also less than k, and we cannot do this like since 2 is min take that path and move forward, but we could gurantee that taking this path of cost 2 can give us min weighted path it could be possiblity that we could min if had gone through cost 3 path.
    - To avoid above problem when we reach this situation where path is valid and is <= k we should keep track of exploration of every point from that point to des. So this make changes in usage of traditional distance array for dijkstra.

    - In dijkstra distance array we would store info like what is min edge weight to reach from source to that node. But due to above situation we would to store another information is this array i.e.,
    the cost of path from source to that node and also what was count at that particular node.

        ** Since count values can be 0 1 .... k, so distance array would become 2d array of size n * k
                                (not possible bcz gurantees n > 0)
                 count values = 0  1  2  3  .....   k
            node = 0
                   1
                   2
                   .
                   .
                   n - 1 

    - The above 2d array stores from given source to reach a node at some count value what was cost value.

    - The initial entry in minheap would be minheap.add({0, 0, 1}), where 0 is dist and another 0 is also source which represents reaching node bcz we travelling from source to source with count as 1. bcz at source there is one character so.

    while(!minHeap.isEmpty()) {
        [dist, node, count] = minHeap.poll();

        // If the dist required to reach a node at count is already < dist then, it is invalid
        if(res[node][count] < dist) {
            continue; //Bcz if the current cost is greater that dist present in res at node at that count
        }

        // Whether reached des
        if(node == n - 1) {
            return cost;
        }

        // adj list stores info like [end, weight] where node is start of this info
        for(int[] info : adj.get(node)) {
            int adjNode = info[0];
            int wt = info[1];

            // Check whether we can go from node to adjNode like whether it fails this count > k, skip this path
            int newCount = (labels.charAt(adjNode) == labels.charAt(node) ? count++ : 1);

            if(newCount > k) {
                continue;
            }

            int newDist = dist + wt;
            // If newDist is less than the present dist to reach from source to that node at newCount value.
            if(newDist < result[adjNode][newCount]) {
                result[adjNode][newCount] = newDist;
                minHeap.add({newDist, adjNode, newCount});
            }
        }
    }

    return -1;

    Dijkstra -> O(E * log V) and O(V + E)
        E = no.of edges and V is no.of vertices.

     - But now we are not only going from u to v only we are going from u -> v from different possible count values. count = 0 ... k, and we would store only n vertices states but in our modified states we are storing n * k states.
        E = E * k
        V = n * k

     So, T.C =  O(E * k * log(n * k))
         S.C = O(n * k + E * k)   
            = O(k * (n + E)) or O(k * (V + E))
*/