// Problem: Kth Largest Element in a Stream
// Link: https://leetcode.com/problems/kth-largest-element-in-a-stream/
// Difficulty: Easy

// Approach:
// We need to continuously return
// the Kth largest element in a stream.
// Instead of storing all elements,
// maintain a Min Heap of size k.
// Key Idea:
//     Heap always stores the
//     k largest elements seen so far.
// Therefore:
//     Smallest element in the heap
//     = Kth largest element overall.
// Constructor:
//     Store k
//     Create min heap
//     Insert all initial numbers
//     using add() method.
// add(val):
// Case 1:
//     Heap size < k
//     Heap is not full yet.
//     Simply add val.
// Case 2:
//     Heap already contains k elements.
//     Compare val with heap top.
//     If val <= heap top:
//         val cannot belong to
//         top k largest elements.
//         Ignore it.
//     Else:
//         Remove smallest element
//         from heap.
//         Add val.
// Finally:
//     Heap top represents
//     Kth largest element.
// Return heap.peek()

// Time Complexity:
//
// Constructor:
//     O(n log k)
//
// add():
//     O(log k)
//
// Space Complexity:
//     O(k)


class KthLargest {
    PriorityQueue<Integer> minHeap;
    int k;

    public KthLargest(int k, int[] nums) {
        this.k = k;
        this.minHeap = new PriorityQueue<>(k);

        for (int num : nums) {
            add(num);
        }
    }

    public int add(int val) {
        if (minHeap.size() < k) {
            minHeap.offer(val);
        } else if (minHeap.peek() < val) {
            minHeap.poll(); 
            minHeap.offer(val); 
        }
        
        return minHeap.peek();
    }
}

/**
 * Your KthLargest object will be instantiated and called as such:
 * KthLargest obj = new KthLargest(k, nums);
 * int param_1 = obj.add(val);
 */
