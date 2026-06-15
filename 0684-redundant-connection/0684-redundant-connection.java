class Solution {
    //Function to find root of node
    public int find(int[] parent, int node) {
        while (node != parent[node]) {
            parent[node] = parent[parent[node]]; //Path compression for optimization
            node = parent[node]; //second line traverse until we get parent node
        }

        return node;
    }

    public int[] findRedundantConnection(int[][] edges) {
        //To detect the cycle in undirected graph best is to
        //use disjoint union set,there is two methods find() and merge()

        //Initialise parent array where parent[i] represents parent of node
        int[] parent = new int[edges.length + 1]; //parent array is dsu struture
        for (int i = 1; i <= edges.length; i++) {
            parent[i] = i; //Initialise parent with its own value
        }

        //Iterate over edges to find redundant one
        for (int[] edge : edges) {
            int node1 = edge[0];
            int node2 = edge[1];

            //Find roots of nodes
            int r1 = find(parent, node1);
            int r2 = find(parent, node2);

            //If roots are same then a cycle exist,return current edge
            if (r1 == r2) {
                return edge;
            }

            //Merge roots by making r1 the parent of r2
            parent[r2] = r1;
        }

        //If no cycle detected(which is not possible in this problem),return empty array
        return new int[0];
    }
}