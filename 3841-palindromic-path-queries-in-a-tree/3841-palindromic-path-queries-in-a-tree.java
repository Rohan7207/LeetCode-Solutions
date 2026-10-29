class Solution {
    private int n;

    // Graph: adjacency list for the undirected tree
    private List<List<Integer>> adj;

    // Euler Tour: entry time, exit time, and XOR mask at each position
    private int[] tin, tout, eulerTour;
    private int time;

    // Segment Tree: supports range XOR queries and point updates
    private int[] segmentTree;

    // Binary Lifting: ancestorTable[node][j] = 2^j-th ancestor
    private int[][] ancestorTable;
    private int[] depth;
    private int cols;

    // Mutable character labels: Java String itself cannot be modified
    private char[] labels;

    public List<Boolean> palindromePath(
            int n, int[][] edges, String s, String[] queries) {

        this.n = n;
        this.labels = s.toCharArray();

        // 1. Initialize the adjacency list
        adj = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }

        // 2. Populate the undirected tree
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];

            adj.get(u).add(v);
            adj.get(v).add(u);
        }

        // 3. Initialize Euler Tour arrays
        tin = new int[n];
        tout = new int[n];
        depth = new int[n];

        // Every node occurs twice: entry and exit
        eulerTour = new int[2 * n];

        // 4. Initialize Binary Lifting table
        cols = 32 - Integer.numberOfLeadingZeros(n);
        ancestorTable = new int[n][cols];

        for (int[] row : ancestorTable) {
            Arrays.fill(row, -1);
        }

        // 5. DFS from root 0 to construct the Euler Tour
        // and initialize each node's parent and depth
        time = 0;
        dfs(0, -1, 0);

        // Build the ancestor table after DFS initializes parents
        buildAncestorTable();

        // 6. Build the Segment Tree over the Euler Tour
        int N = 2 * n;
        segmentTree = new int[4 * N];

        buildSegmentTree(0, 0, N - 1, eulerTour);

        List<Boolean> result = new ArrayList<>();

        // 7. Process queries
        for (String q : queries) {
            String[] parts = q.trim().split("\\s+");

            // Update query: "update node character"
            if (parts[0].equalsIgnoreCase("update")) {
                int node = Integer.parseInt(parts[1]);
                char newChar = parts[2].charAt(0);

                int newMask = charMask(newChar);

                // Update the character label
                labels[node] = newChar;

                // Both Euler positions represent this node.
                // Replace their old masks with the new mask.
                updateSegmentTree(
                        tin[node], newMask, 0, 0, 2 * n - 1);

                updateSegmentTree(
                        tout[node], newMask, 0, 0, 2 * n - 1);

            } else {
                // Path query: "query u v"
                int u = Integer.parseInt(parts[1]);
                int v = Integer.parseInt(parts[2]);

                // Find Lowest Common Ancestor
                int lcaNode = getLCA(u, v);

                // XOR masks of the root-to-u and root-to-v paths
                int prefixU = querySegmentTree(
                        0, tin[u], 0, 0, 2 * n - 1);

                int prefixV = querySegmentTree(
                        0, tin[v], 0, 0, 2 * n - 1);

                // The LCA cancels out of the two prefixes,
                // so XOR its mask once to include it in the path.
                int mask = prefixU ^ prefixV
                        ^ charMask(labels[lcaNode]);

                // A palindrome permutation is possible if
                // at most one character has an odd frequency.
                result.add((mask & (mask - 1)) == 0);
            }
        }

        return result;
    }

    // Convert a lowercase character into a 26-bit mask
    private int charMask(char c) {
        return 1 << (c - 'a');
    }

    // DFS: record entry/exit times, character masks, parents and depths
    private void dfs(int node, int parent, int d) {
        depth[node] = d;
        ancestorTable[node][0] = parent;

        // Entry event
        tin[node] = time;
        eulerTour[time] = charMask(labels[node]);
        time++;

        for (int child : adj.get(node)) {
            if (child == parent) {
                continue;
            }

            dfs(child, node, d + 1);
        }

        // Exit event
        tout[node] = time;
        eulerTour[time] = charMask(labels[node]);
        time++;
    }

    // Segment Tree: build for XOR
    private void buildSegmentTree(
            int i, int l, int r, int[] eulerTour) {

        // Leaf represents one Euler Tour position
        if (l == r) {
            segmentTree[i] = eulerTour[l];
            return;
        }

        int mid = l + (r - l) / 2;

        buildSegmentTree(2 * i + 1, l, mid, eulerTour);
        buildSegmentTree(2 * i + 2, mid + 1, r, eulerTour);

        // Combine the two children using XOR
        segmentTree[i] = segmentTree[2 * i + 1]
                ^ segmentTree[2 * i + 2];
    }

    // Segment Tree: point assignment update
    private void updateSegmentTree(
            int index, int val, int i, int l, int r) {

        if (l == r) {
            segmentTree[i] = val;
            return;
        }

        int mid = l + (r - l) / 2;

        if (index <= mid) {
            updateSegmentTree(index, val, 2 * i + 1, l, mid);
        } else {
            updateSegmentTree(index, val, 2 * i + 2, mid + 1, r);
        }

        // Recalculate the current node after updating its child
        segmentTree[i] = segmentTree[2 * i + 1]
                ^ segmentTree[2 * i + 2];
    }

    // Segment Tree: range XOR query over [start, end]
    private int querySegmentTree(
            int start, int end, int i, int l, int r) {

        // No overlap
        if (l > end || r < start) {
            return 0;
        }

        // Complete overlap
        if (start <= l && r <= end) {
            return segmentTree[i];
        }

        // Partial overlap: combine both children
        int mid = l + (r - l) / 2;

        int leftXor = querySegmentTree(
                start, end, 2 * i + 1, l, mid);

        int rightXor = querySegmentTree(
                start, end, 2 * i + 2, mid + 1, r);

        return leftXor ^ rightXor;
    }

    // Binary Lifting: build the ancestor table
    private void buildAncestorTable() {
        for (int j = 1; j < cols; j++) {
            for (int node = 0; node < n; node++) {
                int middle = ancestorTable[node][j - 1];

                if (middle != -1) {
                    ancestorTable[node][j] = ancestorTable[middle][j - 1];
                }
            }
        }
    }

    // Find LCA(u, v) using Binary Lifting
    private int getLCA(int u, int v) {

        // Make u the deeper node
        if (depth[u] < depth[v]) {
            int temp = u;
            u = v;
            v = temp;
        }

        // Bring u to the same depth as v
        int diff = depth[u] - depth[v];

        for (int j = 0; j < cols; j++) {
            if ((diff & (1 << j)) != 0) {
                u = ancestorTable[u][j];
            }
        }

        if (u == v) {
            return u;
        }

        // Lift both nodes until their parents are the same
        for (int j = cols - 1; j >= 0; j--) {
            if (ancestorTable[u][j] != ancestorTable[v][j]) {
                u = ancestorTable[u][j];
                v = ancestorTable[v][j];
            }
        }

        return ancestorTable[u][0];
    }
}

/*
    We are given undirected tree of nodes represented by edges array and its values are given according to string values. We have been given queries, update s[u] = c,  another is to say whether we can form palindromic string from u node to v node.

    Ex 1: Initial tree:     after assigning string values
               0                 0 = 'a'
              /                 /    
             1                 1 = 'a' 
               \                \
                2                2 = 'c'
    
    Now query is
    1. "query 0 2", means we need find whether we can form palindromic string from 0 to 2,
        the string from 0 to 2 is "aac" which can arranged into palindrome so we can perform this query so assign true.
    2. "update 1 b", means s[1] = 'b', then 
                0 = 'a'
              /                 
             1 = 'b' 
               \                
                2 = 'c'  , we should assign true for this bcz question is asked for only query palindrome only not update
    3. "query 0 2", the string along path 0 to 2 is "abc" which we cannot convert to palindrome so assign false.

    Result = [true, false].


    How will you check a path can be a palindrome or not?
    - We can check it very easily
      ** If string is of even length then,
            "abba" - If string is of even length then to check whether it can palindrome is to check in even length every character occurences must be even if anyone is wrong means its matching pair is not avialable.
     
      ** If string is of odd length then,
            "acbca" - In odd length anyone charcter frequency would be one in this it is 'b' and every other character frequency should be even then it only it can be rearranged to palindrome.

        Palindrome -> Atmost 1 character had odd frequency and other frequency must be even.
      * "aabc"   this cannot be rearranged into palindrome bcz there are 2 character of frequency 1.

    ** Now we know that how we can find a path can be rearranged into palindrome then we can use Brute force very easily,

    Ex = n = 3                                      0 = 'a'
        edges = {{0, 1}, {1, 2}}                   /
        s = "aac"                                 1 = 'a'
                                                   \
                                                    2 = 'c'        
                           u v
                    "query 0 2"
        queries =   "update 1 b"
                    "query 0 2"

    - Now when we get query to find palindrome or not we simply write dfs in graph from node u to node v and store string and then check whether we can arrange in palindrome or not. and on go we will also store frequency when we visit 0th node its value is acessed by string index at 0.
    - Update can be done in constant time by making s[u] = ch.

    ** We can see issue that though update query can be done at O(1) time, to do query operation we would need to traverse the tree and also check palindromic rearrangement, since we need to traverse a tree which takes O(n) time and there Q queries to be done then,
     O(Q * n) => 5 * 10 ^ 4  * 5 * 10 ^ 4
            =>  25 * 10 ^ 8
            => 2.5 * 10 ^ 9
        which off course gives T.L.E

    Improved Approach:
    - In every query we would need to find a string over path and also need to check whether palindrome or not, but we can skip the palindrome checking logic, like:

        0 = 'a'
      /
     1 = 'a' 
     \
       2 = 'c'

    Now when we get query like 0 to 2, we start the dfs at node u that is 0.
    In DFS:
    - We add this character to string 'a' charcter count is 1
    - We go down and add next character 'a' which results 'aa' charcter count is 2 which is allowed
    - We go down and add 'c' then, "aac" now there is one odd frequency character and we can observer that we don't have any issue with even length character our issue is only to keep check of that there is atmost 1 character as with odd count. And we are doing this in dfs only not separate.
    - Notice that since we don't have any issue with even lenght then it is no point of keeping it so when we get even length we will only skip bcz there could be chance to get odd and store next character like, "aa" skip and store only 'c'. Now let's suppose there was another node with 'd' character then res would be "cd", Since we don't have want to store even length characters, 
    - like if the end path string is "aacd", we don't need to 'aa' we only need "cd" to do this, we have trick of applying XOR operation on the fly which doing this operation like,

        a ^ a ^ c ^ d => since a ^ a is 0 then remaining is only "cd", since there are two odd values, we can immediately return false.
        - There is issue when we apply xor operation on the fly bcz when we do c ^ d it also returns some value based on that we cannot decide whether it is palindrome arrangement or not.
          c ^ d = val ? can't decide rearrangement palindrome.
        
    - We can avoid above xor problem by assinging a bits value to each 26 characters like,
        a = 0 0 0 1   -> 0th bit is set
        b = 0 0 1 0   -> 1st bit is set
        c = 0 1 0 0   -> 2nd bit is set
        d = 1 0 0 0   -> 3nrd bit is set
        e = 1 0 0 0 0   -> 4th bit is set
        ...

    - It's usage is we want to take xor of these elements that are present in path from u to v:
         a -> 0 0 0 1
  XOR=>  a -> 0 0 0 1
         c -> 0 1 0 0
         d -> 1 0 0 0
    XOR=>     1 1 0 0 => if only 1 bit or less than we can return true.

    - Notice that xor values of both 'a''s have become 0 means it is pair, and if it is odd then its corresponding position bit is 1 in ans also, like for c 2nd bit is set and in ans also 2nd bit is set and for d 3rd bit is set same in ans. Now we can seeing the final xor operation of path charcaters we can say that whether we can arrage a path in palindromic string or not by 
    *** just checking no.of set bits in ans, if it has only 1 set bit then it can be arranged in palindromic way so we can return true, if it has more than one set bit then it cannot be arranged   
    in palindromic way so we can return false.
    - Notice that we don't need character also to find the arrangement palindrome or not by just checking xor operation ans which tells that, if particular position is set then definetly it has occured odd no.of times and if no.of set bits is more than 1 we can immediately return false.

            
**** On the fly while doing dfs from u to v, calculate the xor of char values which we set above, if it results as some bit x. like 
        Final XOR value = x.
    - Now we need to check no.of set bits, we can do this easily with &  operator like, 
       if (x & (x - 1) == 0) { // There is atmost 1 set bit in ans
            // can be a palindrome.
       }

       x = 1 1 0 0
(x - 1)  = 1 0 1 1
 &       = 1 0 0 0  this is != 0 bcz in x there we two set bits

    Another ex: "aac"
        a ->  0 0 0 1
        a ->  0 0 0 1
        c ->  0 1 0 0
        x ->  0 1 0 0

        x -> 0 1 0 0
    x - 1 -> 0 0 1 1
    &     -> 0 0 0 0  == 0, it says that there is only one bit is set means that there was only one charcter with odd count which proves that it can be made in a palindrome.

    ** This can be seperate question like to find whether we can arrange given string s in palindrome or not. and there are more ways to do this but above is also one of then which I have learnt.


    **-- Now we only optimized checking of palindromic nature by taking xor on fly with dfs. but still we are doing dfs on the tree right when we are asked to perform query operation, till now we just skipped the process of making strings, counting frequency, checking can from palindrome or not in each dfs call, we can do this while traversing only, but still we need to traverse tree. Lets see how we can overcome this, but first see how can we assign bits to a to z

    How can we assign bits to charcters from a to z
    a - 0 0 0 1
    b - 0 0 1 0
    c - 0 1 0 0 

    - We can do this like if ch = 'b' then its position is 1, and bit is set also at 1st pos we can do by left shiffting the 1 by position times.
        (001 => 1) << ('b' - 'a') => represents 1 which gives 0 1 0
        -> xor = 1 << (ch - 'a')
        ch => charValue = 1 << (ch - 'a')

    Now, how to get rid of DFS traversal Everytime ?

                0 - 'a'
              /
             1  - 'b' 
            /  \
     'c' - 2    4 - 'e'
          /
         3 - 'd'           

    - Now suppose we have been query from node 3 to 4, and now we want xor value from node 3 to node 4
        3 -> 4 XOR => d ^ c ^ b ^ e
    - We would not prefer like to go down tree to find u and go up till v and find xor, we can avoid this by these,
        -> Take a fixed point as root.
        -> Take xor values of characters from root -> u =>  a ^ b ^ c ^ d
        -> Take xor values of characters from root -> v =>  a ^ b ^ e
    
      => Obviously the duplicates will be removed which has results only xor of c ^ d ^ e, now if notice the path we have missed one node in path that is 'b', now we need to add this charcater to xor values to get correct results so c ^ d ^ e ^ b.

      ***- Now notice that 'b' node is which node, for node u and v the 'b' node is LCA(Lowest Common Ancestor).

      - Now by seeing this we can say that to find xor values from u to v
      (u, v) = (rootToU) ^ (rootToV) ^ (LCA(u, v))

      - Now one thing we would try to find root to u and root v xor values at each iteration of dfs we can store this information and get this in O(1) time.

  ***How to store xor values from u to v:
        - We can store this information in array of rootToNodeXOR
                idx =   0  1  2  3  4  5  6
        rootToNodeXOR = 
                values= a  b  c  d  e  f  g

        - We can fill the above array by applying dfs on tree one time.
        - How to apply dfs and fill,
            -> Root to root xor is root value only.
            -> In next node it would be a ^ b



                idx =   0  1    2       3       4  
        rootToNodeXOR = a  a^b a^b^c a^b^c^d a^b^c^d^e
                values= a  b    c        d       e  

            - One thing rememeber for our understanding we are storing but eventually we would store 001 to a, 010 to b and so on.

    - Now since we are stored the information now we can efficently apply the query on tree and get xor value from above array in constant value. We can get rootToNodeXOR[3] efficiently in O(1) time by looking up array.
    Ex: query(3, 4) = rootToNodeXOR[3] ^ rootToNodeXOR[4] ^ LCA(3, 4)
                    =    a^b^c^d       ^    a^b^c^d^e      ^ b
            - For our simplicity we are writing a b but we would eventualyy write its bit representation.

      - Now suppose the result is x of above query, to check whether it can be palindrome or not, we can use above things,

        if((x & (x - 1) == 0 ) {
            True
        } else {
            False
        }

    **-- We can get rootToNodeXOR[3] efficiently in O(1) time by looking up array, but we also need to get LCA(u, v)  efficiently in O(1) time. Which can be done using Binary Lifting Refer MIK channel Binary Liffting video. Now we are getting xor values in O(1) time and LCA value in logn time.
    Now we are happy that we can access all things in O(1) and log time, but still there is problem.

   ** -- The problem is when we find rootToNodeXOR array and precompute and store it, but we have also given that we need to do update query which changes a character value then it would definetly change xor of that pos and also other positions of array.
    Ex: update 1 , 'x'  

            0 - 'a'
              /
             1  - 'x' 
            /  \
     'c' - 2    4 - 'e'
          /
         3 - 'd'  

              idx =     0  1    2       3       4  
        rootToNodeXOR = a  a^b a^b^c a^b^c^d a^b^c^d^e
                values= a  b    c        d       e  

        - Then definetly this would affect directly to array we precomputed. then array would be,
              idx =     0  1    2       3       4  
        rootToNodeXOR = a  a^x a^x^c a^x^c^d a^x^c^d^e
                values= a  b    c        d       e

    Now the update(1, 'x') is becomes slow, bcz everytime we have to keep updating rootToNodeXOR array,
     where we need to iterate over rootToNodeXOR array and update character, so iteration would take O(n) but should do U times so O(U * n) where U = 5 * 10 ^ 4.

    ***** To avoid this we can do this using **Euler Tour topic, take pdf notes from MIK leetcode 3841 channel.

    - How to know that we need to apply Euler Tour in particular problem
        * It helps in problem such as Path Sum in tree or graph from root to a node.
        * It can be path sum or xor and also many operations.

    ** In this type of problems often Euler Tour is used and it is also combined with LCA.

        Applications of Euler Tour:
        1. Path Sum (root to node)  and combined with LCA concept  
           Path xor (root to node)

        2. O(1) finding ancestor(u) is v    ??
        3. Suppose there is large tree, and we have to know that from given node we want to know things of subtree of given node like its sum/max element/min element / count of nodes in effiecient way.


    Let's see what Euler Tour states:
                0 - 'a'
              /
             1  - 'b' 
            /  \
     'c' - 2    4 - 'e'
          /
         3 - 'd' 

        1. * It says that first do dfs on the tree. 
           * When doing dfs keep timer = 0 and at timer = 0, we were at node 'a' now store this information in array, lets take array tin = {} which stores the information that at what time did we entire that node, and in same way when we get out of that node in future we would store that time in tout = {}.
           * It has another core idea that when we update a particular timer value of node in tin we have store this info in another array named EulerTour = {}, in which we have tell at timer value at which node were we present.
           * Now we have do this two operations when we enter into a node and then increase timer.

           At node 'a': timer = 0
             node = 0  1  2  3  4
            tin =   0

                    0  1  2  3  4
            tout = 

            timer =     0  1  2  3  4
            EulerTour = a
                        |- 001

        At node 'b': timer++ = 1
             node = 0  1  2  3  4
            tin =   0  1

                    0  1  2  3  4
            tout = 

            timer =     0  1  2  3  4
            EulerTour = a  b
        
        At node 'c': timer++ = 2
             node = 0  1  2  3  4
            tin =   0  1  2

                    0  1  2  3  4
            tout = 

            timer =     0  1  2  3  4
            EulerTour = a  b  c

        At node 'd': timer++ = 3
             node = 0  1  2  3  4
            tin =   0  1  2  3

                    0  1  2  3  4
            tout = 

            timer =     0  1  2  3  4
            EulerTour = a  b  c  d
        Now at 'd' node since there are node below it we would backtrack and at this point timer value was 4 now we need add this timer in dout array.

        At node 'd' backtrack: timer++ = 4
             node = 0  1  2  3  4
            tin =   0  1  2  3

                    0  1  2  3  4
            tout =           4

            timer =     0  1  2  3  4
            EulerTour = a  b  c  d  d
        * In euler array we are actualy printing tour of dfs path it follows.

        At node 'c' backtrack: timer++ = 5
             node = 0  1  2  3  4
            tin =   0  1  2  3

                    0  1  2  3  4
            tout =        5  4

            timer =     0  1  2  3  4  5 
            EulerTour = a  b  c  d  d  c

        At node 'e' normal right of 'b': timer++ = 6
             node = 0  1  2  3  4
            tin =   0  1  2  3  6

                    0  1  2  3  4
            tout =        5  4

            timer =     0  1  2  3  4  5  6
            EulerTour = a  b  c  d  d  c  e

        At node 'e' backtrack: timer++ = 7
             node = 0  1  2  3  4
            tin =   0  1  2  3  6

                    0  1  2  3  4
            tout =        5  4  7

            timer =     0  1  2  3  4  5  6  7
            EulerTour = a  b  c  d  d  c  e  e

        At node 'b' backtrack: timer++ = 8
             node = 0  1  2  3  4
            tin =   0  1  2  3  6

                    0  1  2  3  4
            tout =     8  5  4  7

            timer =     0  1  2  3  4  5  6  7  8 
            EulerTour = a  b  c  d  d  c  e  e  b

        At node 'a' backtrack: timer++ = 9
             node = 0  1  2  3  4
            tin =   0  1  2  3  6

              node= 0  1  2  3  4
            tout =  9  8  5  4  7

            timer =     0  1  2  3  4  5  6  7  8  9 
            EulerTour = a  b  c  d  d  c  e  e  b  a   => It tell dfs journey with time.
        
        - The above array is known as Euler Tour. 
      -- In Euler Tour it says that to make this three arrays and see the magic that it does.

    - How to use Euler Tour in this problem:

    rootToNodeXOR[3] = a ^ b ^ c ^ d
    - To get this info using Euler Tour concept we should do this like,
      * Enter time of 3 using tin[3] = 3
      * Now we need to find xor value of root to 3 to do this, in EulerTour array take xor of values from 0 to that tin value i.e.  0 to 3 => a ^ b ^ c ^ d

      * Took the XOR of EulerTour[0...tin[3]]

    rootToNodeXOR[4] = a ^ b ^ e
     - To get this using Euler Tour is, 
            tin[4] = 6
    - Take xor from EulerTour 0 to 6 that is,
        rootToNodeXOR[4] = EulerTour[0...tin[4]]
                    = a ^ b ^ c ^ d ^ d ^ c ^ e
                   => a ^ b ^ e

    Now lets see whether this works:
     - First we had learnt that to find this
        query(3, 4) = rootToNodeXOR[3] ^ rootToNodeXOR[4] ^ LCA(3, 4)
                    = EulerTour[0...tin[3]] ^  EulerTour[0...tin[4]] ^ LCA(3, 4)

        - But we could see that to find the xor we are iterating over Euler Tour array everytime but we are only iterating over a specified range of query not entire.
        - We can get this effieiently in O(logn) time using ***segment tree

     Segment Tree -> Range Sum / XOR 
        - So we build the segement tree of Euler Tour array and we would get the value of range xor of u to v from segment tree of euler tour array.

    But our problem actually was how this helps with update query right, lets see how it does:

        - update(1, 'x')
                0 - 'a'
              /
             1  - 'x' 
            /  \
     'c' - 2    4 - 'e'
          /
         3 - 'd' 

        * The changes we need to do to make a Euler Tour array work with updated value are:
            -> We would check at what node we want to update which is given query.
            -> We will go tin of that idx and update EulerTour[tin[idx=update]] = 'ch'
            -> We will go tout of that idx and update EulerTour[tout[idx=update]] = 'ch'
        then,

            At node 'b' updated to 'x' : timer = 1
             node = 0  1  2  3  4
            tin =   0  1  2  3  6

              node= 0  1  2  3  4
            tout =  9  8  5  4  7

            timer =     0  1  2  3  4  5  6  7  8  9 
            EulerTour = a  x  c  d  d  c  e  e  x  a   => It tell dfs journey with time.

    - Since we have built a segment tree of Euler Tree array, we need to update it in segement tree,

        Segment Tree -> Range Sum / XOR  -> logn
                    -> update   -> logn


    Story Points:
     1. Query(u, v) :  
                Get the xor values stored from segement tree.
                 
                 log -> (0...tin[u]) ^
                 log -> (0...tout[v]) ^
    Binary Lifting log -> LCA(u, v)    => gives mask
                (mask & (mask - 1) == 0) true

     2. Update(node, ch) :
                s[node] = ch;
                tin[node]
                tout[node]

        Update in segement tree with new character:
            update(tin[node], ch)  -> logn
            update(tout[node], ch)  -> logn

    Code:
     1. DFS
     2. LCA -> Binary Lifting
     3. Segment Tree with Xor
        * Build
        * Query
        * Update Point
*/