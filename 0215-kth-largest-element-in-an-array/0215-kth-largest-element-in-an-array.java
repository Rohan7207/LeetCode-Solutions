// Problem : Kth Largest Element in an Array
// Link : https://leetcode.com/problems/kth-largest-element-in-an-array/
// Difficulty : Medium

// Approach:
// Use a Min Heap of size k to keep track
// of the k largest elements seen so far.
//
// Traverse every element in the array:
//     - Add current element into the heap.
//     - If heap size becomes greater than k,
//       remove the smallest element.
//
// After processing all elements,
// the heap contains exactly the k largest numbers.
// The root of the min heap represents
// the kth largest element.
// Return the top element of the heap.

// Time Complexity: O(n log k)
// Space Complexity: O(k)


class Solution {
    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>((a, b) -> a - b);

        for(int num : nums){
            minHeap.add(num);
            if(minHeap.size() > k){
                minHeap.poll();
            }
        }

        return minHeap.peek();
    }
}
