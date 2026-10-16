// Problem: Subarrays Distinct Element Sum of Squares II
// Link: https://leetcode.com/problems/subarrays-distinct-element-sum-of-squares-ii/?envType=daily-question&envId=2026-10-16
// Difficulty: Hard

// Approach:
// 1. Use a segment tree to maintain information about all subarrays ending
//    at the current index.
// 2. Track the last occurrence of each number using lastSeenIdx.
// 3. For each nums[i], calculate start = lastSeenIdx[nums[i]] + 1.
// 4. Update the range [start, i] by adding 1 to the distinct-element count
//    of each affected subarray.
// 5. Maintain two segment-tree values for each node:
//    - sumSegTree: sum of distinct counts across the range.
//    - sqSumSegTree: sum of squared distinct counts across the range.
// 6. When adding val to every count in a segment of length len, update:
//    sum of counts = sum of counts + len * val
//    sum of squares = sum of squares + 2 * val * oldSum + len * val²
// 7. Use lazy propagation to postpone range updates until necessary.
// 8. After each update, add sqSumSegTree[0] to the answer because the root
//    represents the sum of squared distinct counts for all subarrays ending
//    at the current index.
// 9. Apply modulo 10⁹ + 7 to keep values within bounds.
// 10. Return the accumulated answer.

// Time Complexity: O(n log n)
// Space Complexity: O(n + U), where U is the value range used by lastSeenIdx.


class Solution {

    private int MOD = 1000000007;
    private int n;

    private long[] sqSumSegTree;
    private long[] sumSegTree;
    private long[] lazyTree;

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
