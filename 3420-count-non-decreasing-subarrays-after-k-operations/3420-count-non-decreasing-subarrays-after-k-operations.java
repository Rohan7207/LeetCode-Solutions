// Problem: Count Non-Decreasing Subarrays After K Operations
// Link: https://leetcode.com/problems/count-non-decreasing-subarrays-after-k-operations/?envType=daily-question&envId=2026-11-13
// Difficulty: Hard

// Approach:
// 1. Use a sliding window [left, right] to maintain valid subarrays.
//
// 2. To make a subarray non-decreasing with minimum increments,
//    each element should be at least the maximum element before it.
//
//    Example:
//    [3, 1, 2, 1] -> [3, 3, 3, 3]
//
// 3. Use a max heap to maintain the maximum element in the window.
//
// 4. Use a monotonic decreasing stack to identify the nearest
//    greater element to the left of every index.
//
// 5. Use pos[] to store the next greater-or-equal element's index.
//    Use cnt[] to track which elements are affected when an element
//    is removed from the left.
//
// 6. When adding nums[right], calculate its additional operations.
//
// 7. If operations > k, remove elements from the left and subtract
//    their contributions using cnt[] and pos[].
//
// 8. Once the window becomes valid, every subarray ending at right
//    and starting between left and right is valid.
//
//    Add right - left + 1 to the answer.

// Time Complexity: O(n log n) expected
// Space Complexity: O(n)


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
