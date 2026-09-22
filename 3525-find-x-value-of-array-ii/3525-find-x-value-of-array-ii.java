// Problem: Find X Value of Array II
// Link: https://leetcode.com/problems/find-x-value-of-array-ii/?envType=daily-question&envId=2026-09-20
// Difficulty: Hard

// Approach:
//
// 1. Each segment tree Node represents a contiguous range of the array.
//
// 2. For every Node, we store:
//    - `prod`  → product of the entire range modulo k.
//    - `count[r]` → number of subarrays inside this range whose
//      product % k == r.
//
// 3. For a single element `val`, the only subarray is `[val]`.
//    So:
//       prod = val % k
//       count[val % k] = 1
//
// 4. When merging two ranges:
//
//       LEFT | RIGHT
//
//    First, the total product is:
//
//       (left.prod * right.prod) % k
//
// 5. Every subarray completely inside the LEFT range keeps its
//    original remainder, so copy `left.count` directly.
//
// 6. Every subarray completely inside the RIGHT range gets extended
//    conceptually by the product of the LEFT range when considering
//    the combined segment.
//
//    Therefore, if a right subarray has remainder `x`:
//
//       newRem = (left.prod * x) % k
//
//    Add its frequency to `result.count[newRem]`.
//
// 7. Build the Segment Tree recursively.
//
// 8. For every query:
//    - Update the value at `idx`.
//    - Recalculate all affected nodes from bottom to top.
//    - Query the range `[start, n - 1]`.
//    - Return the frequency of remainder `x`.
//
// 9. Because every update/query touches O(log n) nodes and each
//    merge processes all k remainders, each operation costs O(k log n).

// Time Complexity: O(n × k + q × k × log n)
// Space Complexity: O(n × k)


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
