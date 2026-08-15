// Problem : Last Stone Weight
// Link : https://leetcode.com/problems/last-stone-weight/
// Difficulty : Easy

// Approach:
// Use a Max Heap so that we can always access the two heaviest
// stones in O(log n) time.
//
// 1. Insert all stones into a Max Heap.
//
// 2. While at least two stones remain:
//    - Remove the heaviest stone `y`.
//    - Remove the second heaviest stone `x`.
//    - Smash them together.
//
// 3. If x == y, both stones disappear.
//    Otherwise, the remaining stone has weight:
//
//        y - x
//
//    Insert that remaining weight back into the heap.
//
// 4. When only one stone remains, return its weight.

// Time Complexity: O(n log n)
// Space Complexity: O(n)


class Solution {
    public int lastStoneWeight(int[] stones) {
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b) -> b - a);

        for (int stone : stones) {
            maxHeap.offer(stone);
        }

        while (maxHeap.size() > 1) {
            int y = maxHeap.poll(); 
            int x = maxHeap.poll(); 

            maxHeap.offer(Math.abs(x - y));
        }

        return maxHeap.peek();
    }
}
