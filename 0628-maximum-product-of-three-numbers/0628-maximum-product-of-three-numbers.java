class Solution {
    public int maximumProduct(int[] nums) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>((a, b) -> Integer.compare(a, b));
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b) -> Integer.compare(b, a));

        for (int num : nums) {
            // Track 3 largest values
            minHeap.add(num);

            if (minHeap.size() > 3) {
                minHeap.poll();
            }

            // Track 2 smallest values
            maxHeap.add(num);

            if (maxHeap.size() > 2) {
                maxHeap.poll();
            }
        }

        int max1 = minHeap.poll();
        int max2 = minHeap.poll();
        int max3 = minHeap.poll();

        int min1 = maxHeap.poll();
        int min2 = maxHeap.poll();

        int opt1 = max1 * max2 * max3;
        int opt2 = min1 * min2 * max3;

        return Math.max(opt1, opt2);
    }
}

/*
    Using sorting O(nlogn) above O(n) which uses minheap to store 3 max values and maxheap to store
    2 min values and multiply 2 min with 3 max value
        Arrays.sort(nums);
        int n = nums.length;

        int opt1 = nums[n - 1] * nums[n - 2] * nums[n - 3];

        int opt2 = nums[0] * nums[1] * nums[n - 1];

        return Math.max(opt1, opt2);
*/