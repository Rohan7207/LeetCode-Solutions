// Problem: Minimum Operations to Make Elements Within K Subarrays Equal
// Link: https://leetcode.com/problems/minimum-operations-to-make-elements-within-k-subarrays-equal/?envType=daily-question&envId=2026-11-04
// Difficulty: Hard

// Approach:
//
// 1. Use Sliding Window of fixed size x to process every window.
//
// 2. Maintain two heaps:
//    - Max Heap (left): stores smaller half.
//    - Min Heap (right): stores larger half.
//    This allows us to find the median efficiently.
//
// 3. Use Lazy Deletion with a HashMap:
//    - Mark outgoing elements for deletion.
//    - Remove them physically when they reach the heap top.
//
// 4. Maintain heap sizes using balance() and remove
//    invalid top elements using prune().
//
// 5. The median minimizes the sum of absolute differences.
//    Use the heap sums to calculate the minimum cost
//    of making each window equal.
//
// 6. Store each window's minimum cost in ops[i],
//    where i is the ending index of that window.
//
// 7. Use DP to select exactly k non-overlapping windows
//    with minimum total cost.
//
// 8. At every index, choose:
//    - Select current window:
//      dp[i-x][j-1] + ops[i]
//    - Skip current window:
//      dp[i-1][j]
//
// 9. Take the minimum of both choices.

// Time Complexity: O(n log x + nk)
// Space Complexity: O(nk + x)


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
            while (!heap.isEmpty() && delayed.getOrDefault(heap.peek(), 0) > 0) {
                int num = heap.poll();

                delayed.put(num, delayed.get(num) - 1);
            }
        }

        void balance() {
            if (leftSize > rightSize + 1) {
                int num = left.poll();
                right.offer(num);
                sumR += num;
                sumL -= num;
                rightSize++;
                leftSize--;
                prune(left);
            } else if (rightSize > leftSize) {
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
            if (left.isEmpty() || num <= left.peek()) {
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

            if (num <= left.peek()) {
                leftSize--;
                sumL -= num;

                if (num == left.peek()) {
                    prune(left);
                }
            } else {
                rightSize--;
                sumR -= num;

                if (num == right.peek()) {
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
        for (int i = 0; i < n; i++) {
            heaps.add(nums[i]);

            if (i >= x) {
                heaps.remove(nums[i - x]);
            }

            if (i >= x - 1) {
                long mid = heaps.left.peek();

                ops[i] = heaps.sumR - heaps.sumL + mid * (heaps.leftSize - heaps.rightSize);
            }
        }

        // Choose exactly k non-overlapping windows with minimum total cost.
        // dp[i][j] => Minimum operations required to select exactly j non-overlapping windows from the first i+1 elements (indices 0 through i).
        long[][] dp = new long[n][k + 1];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dp[i], Long.MAX_VALUE); 

            // Selecting zero windows costs zero operations
            dp[i][0] = 0;
        }

        for (int j = 1; j <= k; j++) {
            if (j == 1) {
                // The first possible window ends at x-1. Selecting it gives the cost of that window
                dp[x - 1][j] = ops[x - 1];
            } else {
                // This initializes the earliest possible position where j windows can fit: exactly j * x elements
                dp[j * x - 1][j] = dp[(j - 1) * x - 1][j - 1] + ops[j * x - 1];
            }
        }

        for (int j = 1; j <= k; j++) {
            
            for (int i = j * x; i < n; i++) {
                dp[i][j] = Math.min(dp[i - x][j - 1] + ops[i], dp[i - 1][j]);
            }
        }

        return dp[n - 1][k];
    }
}
