class Solution {
    public int[] sortArray(int[] nums) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>((a, b) -> Integer.compare(a, b));

        for(int num : nums) {
            minHeap.add(num);
        }

        int[] ans = new int[minHeap.size()];
        int index = 0;

        while(!minHeap.isEmpty()) {
            ans[index++] = minHeap.poll();
        }

        return ans;
    }
}