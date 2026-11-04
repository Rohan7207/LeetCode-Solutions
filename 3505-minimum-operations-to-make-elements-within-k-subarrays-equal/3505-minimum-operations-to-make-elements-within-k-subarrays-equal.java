class Solution {

    private static class DualHeap {
        PriorityQueue<Integer> left = new PriorityQueue<>(Comparator.reverseOrder());

        PriorityQueue<Integer> right = new PriorityQueue<>();
        
        // The HashMap tracks elements that should be removed from a heap but haven't physically been removed yet. This is called Lazy Deletion.
        Map<Integer, Integer> delayed = new HashMap<>();

        int leftSize = 0, rightSize = 0;
        long sumL = 0, sumR;

        // It physically removes elements that were previously marked for deletion, but only when they reach the heap's top.
        void prune(PriorityQueue<Integer> heap) {
            while(!heap.isEmpty() && delayed.getOrDefault(heap.peek(), 0) > 0) {
                int num = heap.poll();

                delayed.put(num, delayed.get(num) - 1);
            }
        }

        void balance() {
            if(leftSize > rightSize + 1) {
                int num = left.poll();
                right.offer(num);
                sumR += num;
                sumL -= num;
                rightSize++;
                leftSize--;
                prune(left);
            } else if(rightSize > leftSize) {
                int num = right.poll();
                left.offer(num);
                sumL += num;
                sumR -= num;
                leftSize++;
                rightSize--;
                prune(right);
            }
        }

        void add(int num) {
            if(left.isEmpty() || num <= left.peek()) {
                left.offer(num);
                leftSize++;
                sumL += num;
            } else {
                right.offer(num);
                rightSize++;
                sumR += num;
            }

            balance();
        }

        void remove(int num) {
            delayed.put(num, delayed.getOrDefault(num, 0) + 1);

            if(num <= left.peek()) {
                leftSize--;
                sumL -= num;

                if(num == left.peek()) {
                    prune(left);
                }
            } else {
                rightSize--;
                sumR -= num;

                if(num == right.peek()) {
                    prune(right);
                }
            }

            balance();
        }
    }

    public long minOperations(int[] nums, int x, int k) {
        int n = nums.length;
        DualHeap heaps = new DualHeap();

        long[] ops = new long[n];
        for(int i = 0; i < n; i++) {
            heaps.add(nums[i]);

            if(i >= x) {
                heaps.remove(nums[i - x]);
            }

            if(i >= x - 1) {
                long mid = heaps.left.peek();

                ops[i] = heaps.sumR - heaps.sumL + mid * (heaps.leftSize - heaps.rightSize);
            }
        }

        // Choose exactly k non-overlapping windows with minimum total cost.
        // dp[i][j] => Minimum operations required to select exactly j non-overlapping windows from the first i+1 elements (indices 0 through i).
        long[][] dp = new long[n][k + 1];
        for(int i = 0; i < n; i++) {
            Arrays.fill(dp[i], Long.MAX_VALUE); // Infinity because we haven't found a valid selection

            // Selecting zero windows costs zero operations
            dp[i][0] = 0;
        }

        for(int j = 1; j <= k; j++) {
            if(j == 1) {
                // The first possible window ends at x-1. Selecting it gives the cost of that window
                dp[x - 1][j] = ops[x - 1];
            } else {
                // This initializes the earliest possible position where j windows can fit: exactly j * x elements
                dp[j * x - 1][j] = dp[(j - 1) * x - 1][j - 1] + ops[j * x - 1];
            }
        }

        for(int j = 1; j <= k; j++) {
            /*
                Choice 1: Select the current window
                    The current window ends at i and starts at i-x+1.
                    Its cost is ops[i].

                Since windows cannot overlap, the previous selected windows must end at or before i-x.

                Therefore: dp[i-x][j-1] + ops[i]

                Choice 2: Don't select the current window

                Just carry forward the previous answer:
                dp[i-1][j]

                Combining both:
                
                dp[i][j]= min(dp[i-x][j-1] + ops[i], dp[i-1][j]
    
                This is the main DP transition.
            */
            for(int i = j * x; i < n; i++) {
                dp[i][j] = Math.min(dp[i - x][j - 1] + ops[i], dp[i - 1][j]);
            }
        }

        // We want exactly k windows using the entire array, so we return the state for the last index.
        return dp[n - 1][k];
    }
}

/*
    Finding Median of X sized subarrays:

    nums = {5, -2, 1, 3, 7, 3, 6, 4, -1} x = 5

    res[0] = {-2, 1, 3, 5, 7} = 3
    res[1] = {-2, 1, 3, 3, 7} = 3
    res[2] = {1, 3, 3, 6, 7} = 3
    res[3] = {3, 3, 4, 6, 7} = 4
    res[4] = {-1, 3, 4, 6, 7} = 4

    res = {3, 3, 3, 4, 4}

    Brute force for this would be take x-sized array, sort it and find median which O(n * nlogn).

    Lets take a part of subarray:
        {5, 7, -2, 3, 1, 2}   even and ensure median is at left side sorted ds only

    left = {-2, 1, 2}   right = {3, 5, 7}  (sorted)

        {5, 7, -2, 3, 1}

     {-2, 1, 3}   {5, 7}


    Ensure that:
        1. Take Datastructure that it keeps in sorted order (priority queue)
        2. Even size =>  Half + Half
        3. Odd size => left.size == right.size + 1  means left contains extra 1 element so that it contains median at last of left ds

        Ex: nums = {5, 7, -2, 3, 1}  => {-2, 1, 3, 5, 7}
        i = 0:
            left = {} right = {}
            if left.isempty() || left.size() <= right.size()   true then add in left else right
            left = {5}  right = {}

        i = 1:
            left = {5}   right = {}
            l <= r, false  =>  left = {5,}   right = {7}

        i = 2:
            left = {5}   right = {7}
            l <= r, true  =>  left = {-2, 5} (sorted ds)  right = {7}

        i = 3:
            left = {-2, 5}   right = {7}
            l <= r, false  =>  left = {-2, 5} (sorted ds)  right = {3, 7}

            - But now there is issue we wanted elements such that left values must be smaller than right values and if we concat -2, 5, 3, 7 which is now sorted so we need to swap the bigger element last element of left with smallest start element of right. This process of swapping is called
            Adjustment or Balancing.

            l <= r, false  =>  left = {-2, 3} (sorted ds)  right = {5, 7}

            i = 4:
            left = {-2, 3}   right = {5, 7}
            l <= r, true  =>  left = {-2, 1, 3} (sorted ds)  right = {5, 7}

            If we concat above left and right we get sorted order bcz left_max < right_min which proves we get sorted, if we get this sorted with left_max < right_min then it is 100% gurantee that left_max is median for this subarray.

        - But this for one subarray now we need to find median for i = 1 to 5 to do that we need increase i to 1 and j to 5 and also need to delete that i = 0 element from left or right ds, So we can't use heap bcz we need to find that element and delete and if we use set then we would not get sorted order and there could be possibility of duplicates also,

    - *** So the efficient ds for this is multiset, bcz it can store current window with duplicates and also we can do find operation, insert operation, since it is set the elemeents would be ordered.

    Ensure that:
        1. Take Datastructure that it keeps in sorted order (priority queue)
        2. Even size =>  Half + Half
        3. Odd size => left.size == right.size + 1  means left contains extra 1 element so that it contains median at last of left ds
        4. DS is Multiset

    - But we don't have Multiset ds in java so we will be doing these with heaps only refer Editorial and approach is Approach 1: Sliding Window + Two Heaps + Mathematics + Dynamic Programming
Intuition

    cost = mid×leftSize−sumL+sumR−mid×rightSize
         => sumR−sumL+mid×(leftSize−rightSize)
​
*/
