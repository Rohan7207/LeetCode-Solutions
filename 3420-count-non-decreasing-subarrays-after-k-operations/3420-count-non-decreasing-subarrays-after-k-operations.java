class Solution {
    public long countNonDecreasingSubarrays(int[] nums, int k) {
        int n = nums.length;
        long ans = 0;
        long operations = 0;

        // Maintain the window maximum with a max priority queue
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> b[0] - a[0]);

        // Record the set of elements covered by this index
        List<List<Integer>> cnt = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            cnt.add(new ArrayList<>());
        }

        // Index of the first greater-or-equal element to the right
        int[] pos = new int[n];
        Arrays.fill(pos, n);

        // Decreasing monotonic stack
        Deque<Integer> st = new ArrayDeque<>();

        int left = 0;
        for (int right = 0; right < n; right++) {
            // Maintain the Monotonic Stack
            while (!st.isEmpty() && nums[right] >= nums[st.peek()]) {
                pos[st.peek()] = right;
                st.pop();
            }

            // The stack top is the nearest greater element to the left of nums[right]
            if (!st.isEmpty()) {
                cnt.get(st.peek()).add(right);
            }

            st.push(right);

            // The priority queue front is the window maximum
            pq.offer(new int[] { nums[right], right });

            // Adding nums[right] increases the operation count
            operations += Math.max(0, pq.peek()[0] - nums[right]);

            // The minimum operation count exceeds k; shrink the window
            while (operations > k) {
                // Removing nums[left] from the window decreases the operation count
                for (int i : cnt.get(left)) {
                    // Subtract the contribution
                    operations -= (long) (nums[left] - nums[i]) * (Math.min(pos[i], right + 1) - i);
                }

                left++;
            }

            // Remove elements with indices less than left from the priority queue
            while (!pq.isEmpty() && pq.peek()[1] < left) {
                pq.poll();
            }

            // There are right - left + 1 valid contiguous subarrays ending at right
            ans += right - left + 1;
        }
        return ans;
    }
}

/*
    Brute Force: The most direct approach is to enumerate all contiguous subarrays and calculate their costs individually, but this is clearly too slow.

    Approach: Sliding Window
        Each operation increases any element of a subarray by 1. We need to return the number of subarrays that can become non-decreasing in at most k operations

    Step 1: Solve a subproblem — the minimum number of operations for a single array,
        Given an array arr, what is the minimum number of operations needed to make the entire array non-decreasing, 3402 problem.

    Step 2: Enumerate all subarrays with a sliding window
        Observe that if an array arr can become non-decreasing in at most k operations, then so can any of its subarrays: a subarray of a non-decreasing array is still non-decreasing, and we simply apply the same adjustments over a shorter interval. In other words, with the right endpoint fixed, the cost never increases as the left endpoint moves right. We can therefore count using two pointers.
        Enumerate the right endpoint right in increasing order while maintaining the leftmost possible position of the left endpoint left, that is, the smallest left for which the minimum cost of making [left,right] non-decreasing does not exceed k. There are exactly right−left+1 valid subarrays ending at right. Summing these counts gives the answer.

    Step 3: Maintain the minimum number of operations as the window moves
        The window changes in only two ways: adding an element on the right or removing an element on the left. We analyze how each change affects the minimum number of operations.

            * Add an element x at the right end: Let maxVal be the maximum element in the window before the addition. Since x is the last element, it must be increased to at least maxVal to keep the window non-decreasing. The minimum number of operations therefore increases by max(0,maxVal−x). We can maintain the window maximum with a priority queue or a monotonic queue; see 239. Sliding Window Maximum.

            * Remove an element x from the left end: This is more involved because removing x also removes the portion of the cost that it previously imposed. Elements greater than or equal to x are clearly unaffected; only smaller elements can be affected. Let i be the index of x and j the index of the first element to its right that is at least x. We only need to consider elements in [i+1,j−1].
                Ex: First, consider the window [6,3,1,2,4,5,7]. The initial 6 covers all subsequent elements smaller than it. Removing it uncovers some of those elements. Which ones? The elements whose nearest greater element to the left is exactly 6: the three elements with values (3,4,5). The elements (1,2) have 3 as their nearest greater element to the left, so they remain directly covered by 3. While their required height drops from 6 to 3 (which is accounted for as part of the segment governed by 3), their remaining cost to reach 3 is unchanged.
                - Now consider how to subtract these contributions. Suppose the element at index p is covered by x, meaning its nearest greater element to the left is at the index of x. Raising numsp to x takes x−numsp operations, which must be subtracted when x is removed. Subsequent elements smaller than numsp also have their target height lowered from x to numsp, saving x−numsp operations at each position, until the first element at least numsp
​                  is reached. Thus, for the entire segment starting at p and ending just before that               element, subtract (j′−p)×(x−numsp) operations, where j′is the index of the first              element to the right of p that is at least numsp. If j′ goes beyond the window's right endpoint, truncate the segment at that endpoint.
                - Finding the first element to the right that is greater than or equal to a given element is the classic next greater element problem, which can be solved with a monotonic stack; see 496. Next Greater Element I. Summing the deductions for all indices covered by x gives the total decrease in the minimum number of operations after removing x.


        Summary: Enumerate right and maintain left with two pointers so that the minimum number of operations needed to make [left,right] non-decreasing does not exceed k. When adding an element, use a priority queue to maintain the window maximum and calculate the increase in cost. When removing an element, use the information maintained by the monotonic stack to subtract its contribution. There are right−left+1 valid subarrays ending at right; sum these counts to obtain the answer.

        Complexity Analysis
        Let n be the length of the given array nums.

        Time complexity: O(nlogn).

        Breaking down the costs:

        Both sliding-window pointers left and right only move to the right, at most n times each, so there are O(n) window expansions and contractions in total.
        During the scan, the decreasing monotonic stack maintains the nearest greater element to the left and finds the first greater-or-equal element to the right for each index. Each index is pushed and popped at most once, for a total cost of O(n).
        Each window expansion retrieves the maximum from the priority queue to calculate the cost increase. After the window shrinks, queue-front elements outside the window are removed. Each index enters and leaves the queue at most once, so priority queue operations take O(nlogn) in total, with O(logn) per operation.
        Shrinking the window requires traversing cnt[left], the set of indices covered by the removed element. Each index is covered by at most one element, so the sum of the sizes of all cnt sets is at most n. This part therefore takes O(n) in total.
        Thus, the overall time complexity is O(nlogn).
        Space complexity: O(n).

        The array pos records the first greater-or-equal element to the right of each index, and cnt records the indices covered by each element. Their combined size is O(n). The decreasing monotonic stack and priority queue are each no larger than the window length, also requiring O(n) space.

    nums = [6,3,1,2,4,4], k = 7
    // 6, 3, 1, 2, (6, 3), (3, 1), (3, 2), (3, 1, 2), (1, 4), (2, 4), (1, 2, 4), 4
    r = 5
    l = 1
    pos = {0, 4, 3, 4, 5, 0}
    st = 5, 0
    cnt = {{0 -> 1, 4, 5}, {1 -> 2, 3}}
    q = {{4, 4}, {4, 5} {3, 1}, {2, 3}, {1, 2}}
    oper = 6
    ans = 17
*/