class Solution {

    private List<List<Integer>> adjList;
    private int[] res;
    private int[] last;

    public int[] findSubtreeSizes(int[] parent, String s) {
        int n = parent.length;
        adjList = new ArrayList<>();

        for(int i = 0; i < n; i++) {
            adjList.add(new ArrayList<>());
        }

        // parent[i] = node, node is parent of ith node
        for(int i = 1; i < n; i++) {
            int u = parent[i];
            int v = i;

            adjList.get(u).add(v);
        }

        res = new int[n];
        last = new int[26];

        Arrays.fill(res, 1);
        Arrays.fill(last, -1);

        dfs(0, s, parent);

        return res;
    }

    private void dfs(int currNode, String s, int[] parent) {
        int c = s.charAt(currNode) - 'a';
        int nearestAncestor = last[c];

        last[c] = currNode;  // For child of currNode is currNode is nearestAncestor for childs
        for(int child : adjList.get(currNode)) {
            dfs(child, s, parent);
        }

        // Back to currNode update currNode ancestor as its correct ancestor which nearestAncestor
        last[c] = nearestAncestor;


        // If currNode is root, it has no ancestor
        if(currNode != 0) {
            int newParent = parent[currNode];  // oldParent

            if(nearestAncestor != -1) {
                newParent = nearestAncestor;
            }

            parent[currNode] = newParent;
            res[newParent] += res[currNode];
        }
    }
}

/*
    The problem is:
     - parent[i] us parent of node i, parent[0] = -1
     - s[i] is character assigned to node i
     - The changes should be done on tree must be one time simultaneously for all nodes x from 1 to n -1.
        * Find closest node y to node x, such that y is an ancestor of x and s[x] == s[y]
        * If node y does not exist, do nothing
        * Otherwise, remove the edge between x and its current parent and make node y the new parent of x by adding an edge between them.
              * 'a'     - The closest node y is 1 not 0 bcz, it is nearer and s[4(x)] == s[1(y)] , 'a'='a'
             /          - If y is present then remove edge between x(4) and its current parent 3 and make 
          1 * 'a'           edge btw x and y where y is parent of x
           / \   
          *   *
         /
        * 'a'  

        - Return the array of ans where ans[i] is size of subtree rooted at node i in final tree of change

        Ex:      parent = [-1,0,0,1,1,1], s = "abaabc"

                    0 'a'
                  /   \
                1 'b'  2 'a'
            /   |   \
         3 'a' 4 'b' 5 'c'     

         Node 3 is ancestor of node 0 and s[3] == s[0], 'a' = 'a' so remove edge 1-3 and add edge 0-3

                    0 'a'            ans = {6, 3, 1, 1, 1, 1}
                 /  |    \
             3 'a'  1 'b'  2 'a'
                  /   \
                4 'b' 5 'c'   


        Thought Process:
            For Tree type problem -> Use Recursion as first approach to solve

        
        Ex:  Use recursion using dfs

        parent = [-1,0,0,1,1,1], s = "abaabc"

                    0 'a'
                  /   \
                1 'b'  2 'a'
            /   |   \
         3 'a' 4 'b' 5 'c'  

        - At any point of node, we need to know what was recent last time we have seen with same value(like for 'a' 3 we need to know 0 'a' is nearest ancestor) to find closest ancestor node.
         lastSeen = new int[26];  // since values can be lowercase english characters so 26.

         At dfs node 0:  we will store node info in last,  lastSeen[0='a']  = 'a' and store it in nearestAncestor = lastSeen['a'];
         int currPar = parent[3]
         parent[3] = nearestAncestor; parent = {-1, 0, 0, 0, 1, 1}

         - We need to keep res = {1, 1, 1, 1, 1, 1}, initially 1 bcz each node is a subtree
            res[0] += res[3], bcz 3 is added as new edge 0-3
            res[1] -= res[3]

        parent = [-1,0,0,1,1,1,3,3], s = "abaabcxy"

                    0 'a'
                  /   \
                1 'b'  2 'a'
            /   |   \
         3 'a' 4 'b' 5 'c' 
        / \
    'x' 6  7 'y'

      At node 3:
        lastSeen = {0, _, _, _}
        nearestAncestor = last['a']; // 0 node

        - Now we need to check below nodes 6 and 7 and what if 6th node was 'a' then its nearest ancestor would have been node 3, so we must update lastSeen array as soon we get same character
        lastSeen['a'] = 3 and it should be updated back as node 0 when dfs comes back from child node of 3 to back node 3


        Code:
            dfs(currNode, s, parent) {
                int c = s.charAt(currNode) - 'a';
                int nearestAncestor = last[c];
                last[c] = currNode;  // update last as currNode

                for(int child : adj.get(currNode)) {
                    dfs(child, s, parent);
                }

                // Now back at currNode and assume that recursion has filled res array of child nodes
                last[c] = nearestAncestor;  // update nearestAncestor for currNode and for child currNode is nearestAncestor for its child

                // Now when children have been explored and for currNode we need create new edge
                parent[currNode] = nearestAncestor;
                res[nearestAncestor] += res[currNode];  // size of node 3 is subtree of its nearestAncestor so add to res[nearestAncestor]
            }

            return res;

        Story Points:
         1. Create adjList
         2. res[n] = {1, 1, ..}
         3. last[26] = {-1, -1,..} // tell last time when this character was seen
         4. dfs(0, s, parent)
*/