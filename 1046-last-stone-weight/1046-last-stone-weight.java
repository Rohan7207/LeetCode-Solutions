class Solution {
    public int lastStoneWeight(int[] stones) {
        //Create a max heap
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b) -> b - a);

        //Add all elements to a heap
        for (int stone : stones) {
            maxHeap.add(stone);
        }

        //Remove two heavy stones from heap and smash them
        while (maxHeap.size() > 1) {
            int y = maxHeap.poll(); //heavy stone
            int x = maxHeap.poll(); //second heavy stone

            if (x != y) {
                maxHeap.add(y - x);
            }
        }

        //return the remaining element in heap or else 0
        return maxHeap.isEmpty() ? 0 : maxHeap.peek();
    }
}