// Problem: Find Median From Data Stream
// Link: https://leetcode.com/problems/find-median-from-data-stream/
// Difficulty: Hard

// Approach:
// Use two heaps:
//     - Max Heap (left) stores the smaller half
//       of the numbers.
//
//     - Min Heap (right) stores the larger half
//       of the numbers.
// When inserting a number:
//     - Place it into the appropriate heap
//       based on comparison with left's top.
//     - Rebalance heaps if their size
//       difference exceeds 1.
// This ensures:
//     - All elements in left <= all elements in right.
//     - Heap sizes differ by at most 1.
// To find median:
//     - If one heap has more elements,
//       its top is the median.
//     - If both heaps have equal size,
//       median is the average of both tops.

// Time Complexity:
//     addNum()     : O(log n)
//     findMedian() : O(1)
//
// Space Complexity: O(n)


class MedianFinder {
    PriorityQueue<Integer> left;
    PriorityQueue<Integer> right;

    public MedianFinder() {
        left = new PriorityQueue<>((a, b) -> b - a);
        right = new PriorityQueue<>();
    }

    public void addNum(int num) {
        if (left.isEmpty() || num <= left.peek()) {
            left.add(num);
        } else
            right.add(num);

        if (left.size() > right.size() + 1) {
            right.add(left.poll());
        }
        if (right.size() > left.size() + 1) {
            left.add(right.poll());
        }
    }

    public double findMedian() {
        if (left.size() > right.size())
            return left.peek();
        if (right.size() > left.size())
            return right.peek();

        return (left.peek() + right.peek()) / 2.0;
    }
}

/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder();
 * obj.addNum(num);
 * double param_2 = obj.findMedian();
 */
