class Solution {
    public int[] sortArray(int[] nums) {
        int n = nums.length;
        return quickSort(nums, 0, n - 1);
    }

    private int[] quickSort(int[] nums, int start, int end) {
        int pivot = nums[start + new Random().nextInt(end - start + 1)];

        int i = start, j = end;

        while (i <= j) {
            while (nums[i] < pivot)
                i++;
            while (nums[j] > pivot)
                j--;

            if (i <= j) {
                int temp = nums[i];
                nums[i] = nums[j];
                nums[j] = temp;
                i++;
                j--;
            }
        }

        if (i < end)
            quickSort(nums, i, end);
        if (j > start)
            quickSort(nums, start, j);
        return nums;
    }
}

/*
     PriorityQueue<Integer> minHeap = new PriorityQueue<>((a, b) -> Integer.compare(a, b));

        for (int num : nums) {
            minHeap.add(num);
        }

        int[] ans = new int[minHeap.size()];
        int index = 0;

        while (!minHeap.isEmpty()) {
            ans[index++] = minHeap.poll();
        }

        return ans;
*/