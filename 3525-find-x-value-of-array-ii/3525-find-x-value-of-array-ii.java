class Solution {

    class Node {
        int[] count = { 0, 0, 0, 0, 0 };
        int prod = 0;
    }

    // {Build = O(n * k), each update/query = O(k * logn)}
    class SegmentTree {
        int n;
        int k;
        Node[] segTree;

        SegmentTree(int[] nums, int k) {
            this.k = k;
            this.n = nums.length;
            // Allocate space for the array of Nodes
            this.segTree = new Node[4 * this.n];

            // Fill every index with a new, empty Node instance
            for (int i = 0; i < segTree.length; i++) {
                segTree[i] = new Node();
            }

            build(0, 0, n - 1, nums);
        }

        public void build(int i, int l, int r, int[] nums) {
            // Leaf Node
            if (l == r) {
                leafNode(i, nums[l]);
                return;
            }

            int mid = l + (r - l) / 2;

            // left build
            build(2 * i + 1, l, mid, nums);

            // right build
            build(2 * i + 2, mid + 1, r, nums);

            // build root
            segTree[i] = mergeNodes(segTree[2 * i + 1], segTree[2 * i + 2]);
        }

        public void leafNode(int i, int val) {
            // segTree[i]
            for (int x = 0; x < k; x++) {
                segTree[i].count[x] = 0;
            }

            int r = val % k;
            segTree[i].count[r] = 1;
            segTree[i].prod = r;
        }

        public Node mergeNodes(Node left, Node right) {
            Node result = new Node();

            result.prod = (left.prod * right.prod) % k;

            for (int x = 0; x < k; x++) {
                result.count[x] = left.count[x];
            }

            for (int x = 0; x < k; x++) {
                int newRem = (left.prod * x) % k;

                result.count[newRem] += right.count[x];
            }

            return result;
        }

        public void segTreeUpdate(int i, int l, int r, int idx, int val) {
            if (l == r) {
                leafNode(i, val);
                return;
            }

            int mid = l + (r - l) / 2;

            if (idx <= mid) {
                segTreeUpdate(2 * i + 1, l, mid, idx, val);
            } else {
                segTreeUpdate(2 * i + 2, mid + 1, r, idx, val);
            }

            segTree[i] = mergeNodes(segTree[2 * i + 1], segTree[2 * i + 2]);
        }

        public void update(int idx, int val) {
            segTreeUpdate(0, 0, n - 1, idx, val);
        }

        public Node segTreeQuery(int start, int end, int i, int l, int r) {
            // start and end exactly covers l and r so return that node
            if (l >= start && r <= end) {
                return segTree[i];
            }

            int mid = l + (r - l) / 2;

            // Completely in left side
            if (end <= mid) {
                return segTreeQuery(start, end, 2 * i + 1, l, mid);
            }

            // Completely in right side
            if (start > mid) {
                return segTreeQuery(start, end, 2 * i + 2, mid + 1, r);
            }

            // If not on both side check both
            Node left = segTreeQuery(start, end, 2 * i + 1, l, mid);
            Node right = segTreeQuery(start, end, 2 * i + 2, mid + 1, r);

            return mergeNodes(left, right);
        }

        public Node query(int start, int end) {
            // Range: start...end

            return segTreeQuery(start, end, 0, 0, n - 1);
        }
    }

    public int[] resultArray(int[] nums, int k, int[][] queries) {
        int n = nums.length;
        int len = queries.length;

        SegmentTree segTree = new SegmentTree(nums, k);
        int[] res = new int[len];

        for (int i = 0; i < len; i++) {
            int idx = queries[i][0];
            int val = queries[i][1];
            int start = queries[i][2];
            int x = queries[i][3];

            segTree.update(idx, val);

            Node resNode = segTree.query(start, n - 1);

            res[i] = resNode.count[x];
        }

        return res;
    }
}

/*
    The change is in previous part I and II is only suffix removal is removed not prefix, and also we have given queries to perform,
    - Update nums[indexi] to valuei. Only this step persists for the rest of the queries.
        Ex: nums = {1, 2, 3, 4} and queries says to change 3 to 5 then nums = {1, 2, 5, 4} remains as nums to future array.
    - Remove the prefix nums[0..(starti - 1)] (where nums[0..(-1)] will be used to represent the empty prefix). We should remove the prefix till start_i - 1 from each query operation and return array of size of queries length where result[i] is the answer for the ith query.

    Ex: nums = {1, 2, 3, 4, 5} k = 3,
    For query0 = (2(idx), 2(val), 0(start), 2(rem)) => nums = {1, 2, 2, 4, 5} with removing empty prefix since start is 0, now we should find every subarray from modifed nums such that product of subarray modulo k is remainder = 2. (where we should also remove suffix),
     ->  nums = {1, 2, 2, 4, 5} remove suffix {2, 4, 5} then product is 1 * 2 = 2 % 3 = count++ (same as rem)
     ->  nums = {1, 2, 2, 4, 5} remove suffix {} then product is 1 * 2 * 2 * 4 * 5 = 80 % 3 = count++ (same as rem)
    Now for query0 there 2 subarray product modulo k  whose rem is 2.
    Similarly we should for each query.

    query[i] = {idx, val, start, x}, nums[idx] = val
    if start = d then we should check for subarray from d whose modulo is x and we should also do suffix
    nums = {a, b, c, d, e, f}
        {d, e, f} % k = x  1
        {d, e} % k = y
        {d} % k = x 2
    ans = 2

    // In part-I the starting was vairable and we where asking for previous ans but in this the start is fixed and end whereas based on suffix choosing,
    Part-I = count_i[x] = count of sub[...i] % k == x
    Part-II = count_i(starting_point)[x] = count of sub[i...] % k == x
        We could apply same solution here but we should find for each query which is O(q*(n * k)) since n and q are large it is not feasible and leads memory limit.

    - Queries
    - Point Update (nums[idx] = val)   -> This leads to approach of segment tree

    Segment Tree:
        - We need to store product of each subarray from left to right.
        - We need to store count of subarray's product modulo k whose remainder is x from left l 
            count_l[x(rem)] = ? x can be from 0 to k - 1 so count can be of size k count = new int[5];

    Ex: {idx, val, start, x}
    * Now we can call only segment.query(start, n - 1)
    * Since we need to update nums we should also change in segement, segment.update(idx, val)  // logn

    class Node {
        int[] count;
        int prod;
    }

    int[] segmentTree = new int[4 * n];

    How segment tree looks like for ex : nums = {3, 3, 4, 4}, n = 4 k = 5
    Leaf nodes : 
                                   0...3 

                                {3, 3, 4, 4}          
                        /                          \
                   0..1                              2..3

                  {3, 3}                            {4, 4}
               /          \                    /             \
          0..0           1..1               2..2              3..3

           {3}            {3}                {4}               {4}   


    Leaf Node 2..2 {4}: L = 2 R = 2, where prod = 4 and count = {0, 0, 0, 0, 1}   
    possible subarray = 4 only  4 % 5 = 4

    Build Segment Tree: buildSegmentTree(0(root node start from 0), 0(l), n - 1(r for root), nums);
        void buildSegmentTree(int i, int l, int r, int[] nums) {
            // Check if leaf node
            if(l == r) {
                // single element, nums[i]. prod is nums[i] only and count is count[nums[i] % k]++
                makeLeafNode(i, nums[l]);
            }

            int mid = l + (r - l) / 2;

            // left child build
            buildSegmentTree(2 * i + 1, l, mid, nums);  // 2 * i + 1 is root of nums

            // right child build
            buildSegmentTree(2 * i + 2, mid + 1, r, nums);

            // build curr node with help of leftChild and rightChild, the task is to get combined info of left and right and build parent where prod can be built easily by leftProd * rightProd but building count from both child is important part
            segmentTree[i] = mergeNodes(segmentTree[2 * i + 1], segmentTree[2 * i + 2]);

            
        } 

        mergeNode: Go first down and understand then look this
        Node res(root Node);
        res.prod = (left.prod * right.prod) % k;
            
        for(int x = 0; x < k; x++) {
            res.count[x] = left.count[x];  // Paste same left part
        }     

        for(int x = 0; x < k; x++) {
            int newRem = (left.prod * x) % k;

            res.count[newRem] += right.count[x];
        }

        return res;  


    How to Merge left child and right child to fill parent node??
        segmentTree[i] = mergeNodes(segmentTree[2 * i + 1], segmentTree[2 * i + 2]);       

     Ex: nums = {3, 3, 2, 2} k = 5
                        root[0..3]   =>   sub = {3}, {3, 3}, {3, 3, 2}, {3, 3, 2, 2}
                        prod=(4*4)%5=1
                        count={0, 0 + 1, 0, 1 + 1, 1}
                    /                  \
            Left[0..1]                 right[2..3]
            prod=(3*3)%5=4             prod=(2*2)%5=4
            count={0, 0, 0, 1, 1}      count={0, 0, 1, 0, 1} 
        sub= {3} % k = 3               sub= {2} % k = 2
             {3, 3} % k = 4                 {2, 2} % k = 4

    - The starting point of left child is always starting point of root so there will be same subarrys as left child in root, so we can paste same left count values to count values to root count array

        Node res(root Node);
        res.prod = (left.prod * right.prod) % k;
        
        for(int x = 0; x < k; x++) {
            res.count[x] = left.count[x];  // Paste same left part
        }

    sub = {3}, {3, 3}, {3, 3, 2}, {3, 3, 2, 2} of root

    here {3, 3, 2} where {3, 3} comes from left and {2} from right similar for other 
        {3 * 3 * 2} % k
        (left.prod * 2) % k
        ((left.prod % k) * (2 % k)) % k
        newRem = (left.prod * r) % k
                 (4 * 2) % 5 = 3
                 /      \-  right= {2} % k = 2  
(3 * 3 % 5 = 4 (left.prod))  for ex: {2, 1} % k = 2 for this also we will get same rem as 3 which 2times
                               We can sya that how many times rem is 2 on right we get that many times 3 as rem. so we ask right side how many times rem is 2 and add it root. 

    For {3, 3, 2, 2} % k
        ((3 * 3) * (2 * 2)) % k
        ((3 * 3) % k * (2 * 2) % K) % k
        (left.prod * 4) % k = 1 and we ask right side that how many subarrays with rem 4.
        
        We did: (left.prod * 2) = 3    
                (left.prod * 4) = 1
        There could other remainder also, 0, 1, 2, 3, 4

    mergeNode:
        Node res(root Node);
        res.prod = (left.prod * right.prod) % k;
            
        for(int x = 0; x < k; x++) {
            res.count[x] = left.count[x];  // Paste same left part
        }     

        for(int x = 0; x < k; x++) {
            int newRem = (left.prod * x) % k;

            res.count[newRem] += right.count[x];
        }

        return res;

    
    Steps: 
        1. We should query[i] = {idx, val, start, x}
        2. segmentTree.update(idx, val)
        3. Node = segementTree.query(start, n - 1);
        4. res.add(Node.count[x])
*/
