class Solution {
    public int maximumProduct(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;

        int opt1 = nums[n - 1] * nums[n - 2] * nums[n - 3];

        int opt2 = nums[0] * nums[1] * nums[n - 1];

        return Math.max(opt1, opt2);
    }
}

/*
    PriorityQueue<Integer> minHeap = new PriorityQueue<>((a, b) -> Integer.compare(a, b));

        for (int num : nums) {
            minHeap.add(num);

            if (minHeap.size() > 3) {
                minHeap.poll();
            }
        }

        int ans = 1;
        while (!minHeap.isEmpty()) {
            int curr = minHeap.poll();

            ans *= curr;
        }

        return ans;
*/