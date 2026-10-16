class Solution {

    private int MOD = 1000000007;
    private int n;

    private long[] sqSumSegTree;
    private long[] sumSegTree;
    private long[] lazyTree;

    // O(nlogn)
    public int sumCounts(int[] nums) {
        n = nums.length;

        sqSumSegTree = new long[4 * n];
        sumSegTree = new long[4 * n];
        lazyTree = new long[4 * n];

        int[] lastSeenIdx = new int[100001];
        Arrays.fill(lastSeenIdx, -1);

        int res = 0;

        for (int i = 0; i < n; i++) {
            int start = lastSeenIdx[nums[i]] + 1;
            int end = i;

            updateRange(start, end, 0, 0, n - 1, 1);

            res = (int) ((res + (sqSumSegTree[0])) % MOD);

            lastSeenIdx[nums[i]] = i;
        }
        
        return res;
    }

    private void updateRange(int start, int end, int i, int l, int r, int val) {
        if (lazyTree[i] != 0) {
            lazyAddition(i, l, r, lazyTree[i]);

            if (l != r) {
                lazyTree[2 * i + 1] += lazyTree[i];
                lazyTree[2 * i + 2] += lazyTree[i];
            }

            lazyTree[i] = 0;
        }

        // Out of Range
        if (l > end || r < start || l > r) {
            return;
        }

        // Completely withing range
        if (l >= start && r <= end) {
            lazyAddition(i, l, r, val);

            if (l != r) {
                lazyTree[2 * i + 1] += val;
                lazyTree[2 * i + 2] += val;
            }

            return;
        }

        // Go to left and right
        int mid = l + (r - l) / 2;
        updateRange(start, end, 2 * i + 1, l, mid, val);
        updateRange(start, end, 2 * i + 2, mid + 1, r, val);

        // Update ith node with child nodes
        sumSegTree[i] = (sumSegTree[2 * i + 1] + sumSegTree[2 * i + 2]) % MOD;
        sqSumSegTree[i] = (sqSumSegTree[2 * i + 1] + sqSumSegTree[2 * i + 2]) % MOD;
    }

    // Updates both the trees with child nodes
    private void lazyAddition(int i, int l, int r, long val) {
        int len = (r - l + 1);

        sqSumSegTree[i] = (sqSumSegTree[i]
                + 2L * val * sumSegTree[i]
                + (long) len * val * val) % MOD;
        sumSegTree[i] = (sumSegTree[i] + (long) len * val) % MOD;
    }
}

/*
    It is same as previous I problem 2913 but in this contraints are high, So we should go to O(n ^ 2),
    - Brute force is easy like traverse with two loops and keep set to handle duplicates like same as previous easy version. Which leads to O(n ^ 2) and would eventually give TLE, but this brute force only lead to optimal solution.

    Optimal Solution using Brute approach and solving its problem:
    - The problem in brute force is we would try every possible subarrays like when 1st element we go to every element and calculate subarrays, and in 2nd element again we will check every subarray.

        Ex = {2, 4, 6, 3, 5, 6, 10}

    i    Subarrays      Unique Elements
    0      {2}          1 
    1     {2, 4}        2, 1 (in this subarr), 1(4 is also a subarray eventually we would explore in BF)
    2     {2, 4, 6}     3, 2, 1 (In (2, 4, 6) = 3 UE, (4, 6) = 2,  (6) = 1 Unique Element)    
    3    {2, 4, 6, 3}   4, 3, 2, 1  (there is pattern to be followed prev elements + curr.length)
    4   {2, 4, 6, 3, 5}     5, 4,3, 2, 1  (same pattern)
    5  {2, 4, 6, 3, 5, 6}   5, 4, 3, 3, 2, 1 (6 is repeated in past, bcz of this at point of repeation
                            i = 2 the subarrays {2, 3, 6, 3, 5, 6}, {4, 6, 3, 5, 6} and {6, 3, 5, 6} are same as {5, 4, 3}, but from next point of repeation index subarrays {3, 5, 6}, {5, 6} and {6} count would change like previously, {3, 5} = 2 => {3, 5, 6} = 2 + 1 = 3,  {5} = 1 => {5, 6} = 1 + 1 = 2,  and new element 6 gives 1 so we get 3 2 1 atlast.
                            So pattern is like when new elements comes just increment old values and add 1 bcz new element as entered, but in case of duplicate, we should add 1 only after index of duplicate element seen and add 1 and before that index should be same bcz they does'nt change. So we should keep track of duplicate element index so that we can add +1 after that index only)

    - We can see that everytime whenever new element is coming we are adding +1 at given range starting idx 0 to i of count array. In case of duplicate we will only update or add +1 to the index present before in range of idx + 1 to i and in each step we add extra 1. So we everytime we are just adding +1 at given range which leads to approach named Segment Tree (or Fenwick Tree also) Data Structure.

    lets take another ex = nums = {1, 2, 1}

                {1}            {1, 2}           {1, 2, 1} (duplicate previous seen idx+1 element to i)
count= 0 to 0 {1, 0, 0}  0-1 {2, 1, 0}     1-2 {2, 2, 1} 
Final ans=> 1^2+0^2+0^2  + 2^2+1^2+0^2  +  2^2+2^2+1^2    => 1 + 5 + 9   => 15(correct ans)       

    Story Points:
    1. squareSumSegmentTree , Lazy Tree (Lazy Propogation), we keep two segment trees, lastSeenIdx array(10^5 + 1) or map, initial value is -1.
    2. Iterate in nums
        for(i = 0; i < n; i++) {
            int start = lastSeenIdx[nums[i]] + 1;   // Update from idx + 1 to i for duplicate
            int end = i;

            updateRange(start, end, 0(rootNode), 0(startIdx), n - 1(endIdx), +1 (update query));

            result = (result + squareSumSegmentTree[0]) % MOD;  // squareSumSegmentTree[0] will  store sum of squares of all subarrays distinct count 

            lastSeenIdx[nums[i]] = i;
        }

        return res;


    - We will keep 4 * n size of segment to be safer side
    Ex = {1, 2, 1}
Where 0 represents the sum of squares of distinct count at each root node which will update as traverse
                             0
                            0-2
                        /           \
                     0                   0   
                    0-1                 2-2

               /          \  
              0               0  
             0-0             1-1


        nums = {1, 2, 1}    
        i = 0, {1}, start = -1 + 1 = 0, end = i = 0, then it will search this start and end in tree, and when it reaches it will add +1 and return and the parent nodes updates itself as left+right till rootNode.
        count = {1, 0, 0} which represents segment tree structure only like from subarray there is 1 UE in tree and same in array, from 0-1 there is 1 and 0-0 there is 1 which as same as in count. Note we are only taking sum not sqaure of sums.

                             1  
                            0-2
                        /           \
                     1                   0   
                    0-1                 2-2

               /          \  
              1               0  
             0-0             1-1

    i = 1, {1, 2}  start = 0 end = i = 1 {0, 1}, then count = {1, 2, 0}
    - there are two ways to update like when we find that range first update their childs +1 and the update parent, range node as left + right, and other way is to use Lazy propogation which tells that don't update child nodes directly update root node and tell the child nodes that I will come and update in future but for this we need know that whether we should update child or not so for that we will keep another Lazy Tree similar to segment tree, we will represent this another tree but for easy i will just mention beside that nodes as +1 or not, and +2 at 0-1 bcz we will be adding +1 at both its childs so +2

                          1+3 = 4 
                            0-2
                        /           \
                    1+2=3                0   
                    0-1                 2-2

               /          \  
        +1      1             0  +1
             0-0             1-1

    segmentTree[node(0-1)] = 1 + ((r - l + 1) * 1) where l and r are range of node range we want to update
                           = 1 + ((1 - 0 + 1) * 1)  l = 0, r = 1
                           = 1 + 2 => 3

    Logic of updateRange: But this is for sum of subarrays not square
        void updateRange(int s(startRange), int e(endRange), int i(segmentTreeNodeIdx), int l(range of that node), int r(range of that node), int val(update val)) {
            // We need update values of child right so check lazyTree[i] != 0 then update it as many nodes below it * value stored in lazyTree[i], and we will inform the child nodes that in future I will update and pass it to child nodes. That is I have added value if lazyTree at ith node and passing to child nodes to update in future and since we have update the ith node of lazyTree in segTree we will assign lazyTree[i] = 0.
            if(lazyTree[i] != 0) {
                segTree[i] += (lazyTree[i] * (r - l + 1));

                if(l != r) {
                    lazyTree[2*i + 1] += lazyTree[i];
                    lazyTree[2*i + 2] += lazyTree[i];
                }

                lazyTree[i] = 0;
            }

            // We are at node which we want to update, where we will take that full subtree to add +1 and inform lazy tree to store info to add +1 in future, 
            like for ex: s = 0, e = 6 and l = 3, r = 5

            // Range is within s and e completely
            if(l >= start && r <= end) {
                segTree[i] += (r - l + 1) * val;

                // It should be leaf
                if(l != r) {
                    lazyTree[2*i + 1] += val;  //left child 
                    lazyTree[2*i + 2] += val;  // right child
                }

                return;   
            }

            // Else we should split it to search for that range
            int mid = l + (r - l) / 2;
            updateRange(s, e, 2*i + 1, l, mid, val);
            updateRange(s, e, 2*i + 2, mid + 1, r, val);


            // Add updated values of childs in parent node
            segTree = segTree[2 * i + 1] + segTree[2 * i + 2];
        }

    Sum of Square:
        In lazy propogation since it was only summation we would easily do node + no.of child nodes * val
    but we can't do same for squares, we should do this differently,

            x^2 + y^2
          /         \
        x             y
    Lets suppose in future we need to update values and add +1 to each then

        (x+1)^2 + (y+1)^2         => Lets solve this  (x+1)^2 + (y+1)^2 
         /           \                                   = (x^2+1+2x) + (y^2+1+2y)  
     x+1            y+1                               = (x^2+y^2) + 2(x+y) + 1^2+1^2 =>(val^2+val^2)
                                                where (x^2+y^2) is older value + next term
                                                    and where (x + y) is sum of previous old ith node child sum, so we will be keeping two segTree for sumSegTree and for squareSumTree

    Generic:
        (x+val)^2 + (y+val)^2 
      = (x^2+val^2+2xval) + (y^2+val^2+2yval)
      = (x^2+y^2) + 2*val(x+y) + (val^2 *2)

    To find updated value of ith node
    sqSumSegTree[i] = (olderValue) + next term
                    = (sqSumSegTree[i]) + 2*val*(sumSegTree[i]) + (r - l + 1) * val^2;

    Segment Trees:      
    sqSumSegTree
    sumSegTree
    lazyTree

    To update both sqSumSegTree and sumSegTree
    void updateRange(int s(startRange), int e(endRange), int i(segmentTreeNodeIdx), int l(range of that node), int r(range of that node), int val(update val)) {
            if(lazyTree[i] != 0) {
                lazyAddition(i(ith node), l(range), r(range), lazyTree[i](val to add));

                if(l != r) {
                    lazyTree[2*i + 1] += lazyTree[i];
                    lazyTree[2*i + 2] += lazyTree[i];
                }

                lazyTree[i] = 0;
            }

        

            // Range is within s and e completely
            if(l >= start && r <= end) {
                lazyAddition(i, l, r, val);

                // It should be leaf
                if(l != r) {
                    lazyTree[2*i + 1] += val;  //left child 
                    lazyTree[2*i + 2] += val;  // right child
                }

                return;   
            }

            // Else we should split it to search for that range
            int mid = l + (r - l) / 2;
            updateRange(s, e, 2*i + 1, l, mid, val);
            updateRange(s, e, 2*i + 2, mid + 1, r, val);


            // Add updated values of childs in parent node
            segTree = segTree[2 * i + 1] + segTree[2 * i + 2];
        }

        // l and r used to find how many child nodes down to be added
        void lazyAddition(int i, int l, int r, int val) {
            // Length of child nodes down
            int len = (r - l + 1);

            sqSumSegTree[i] += 2*val*(sumSegTree[i]) + len*(val*val);
            sumSegTree[i] += (len*val);
        }
*/