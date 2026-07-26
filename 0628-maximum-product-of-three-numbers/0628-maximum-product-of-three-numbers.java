class Solution {
    public int maximumProduct(int[] nums) {
        int max1 = Integer.MIN_VALUE;
        int max2 = Integer.MIN_VALUE;
        int max3 = Integer.MIN_VALUE;

        int min1 = Integer.MAX_VALUE;
        int min2 = Integer.MAX_VALUE;

        for (int num : nums) {
            if (num >= max1) {
                max3 = max2;
                max2 = max1;
                max1 = num;
            } else if (num >= max2) {
                max3 = max2;
                max2 = num;
            } else if (num >= max3) {
                max3 = num;
            }

            if (num <= min1) {
                min2 = min1;
                min1 = num;
            } else if (num <= min2) {
                min2 = num;
            }
        }

        return Math.max(max1 * max2 * max3, min1 * min2 * max1);
    }
}

/*
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
*/

// Time Complexity: O(n log 3)
// Space Complexity: O(1)

// Maintain the 3 largest and 2 smallest numbers using heaps, because the maximum product of three numbers can only come from these five candidates.

// We can improve by calculating all 5 values in single pass

/*
    Key Observation

Maximum product does not require checking all combinations.

Example:

nums = [-10,-10,5,2]

Largest three:

-10 * 5 * 2 = -100

But:

-10 * -10 * 5 = 5000 ✅

So we need:

3 largest
+
2 smallest
Magic Line
if (minHeap.size() > 3)
    minHeap.poll();

This keeps only the top 3 largest values.

Because in a min heap:

smallest element stays at top

Removing it removes unnecessary values.

How we thought to come to this solution

Brute force:

Choose every 3 numbers:

O(n³)

Too slow.

Think:

What numbers can create maximum product?

Only:

large positive numbers

and:

large negative × large negative × large positive

So only 5 numbers matter:

[-2 smallest, -1 smallest, largest, 2nd largest, 3rd largest]

Use heaps to track them.

Why It Works

Any other number outside these five:

cannot beat the 3 largest values
cannot create a better negative pair

Therefore the answer must be one of:

largest × largest × largest

or

smallest × smallest × largest
Pattern Recognition

Whenever you see:

Maximum/minimum product
Choose k numbers
Large constraints

Think:

Track extreme values
        ↓
Heap / Sorting
Interview Importance

⭐⭐⭐⭐

Tests:

Heap usage
Mathematical observation
Avoiding brute force

Similar Problems
Kth Largest Element in an Array
Kth Largest Element in a Stream
K Closest Points to Origin
Maximum Product of Three Numbers

Common Pattern
Find important extremes
        ↓
Maintain using heap
        ↓
Check possible combinations
        ↓
Return best answer
*/

/*
    Using sorting O(nlogn) above O(n) which uses minheap to store 3 max values and maxheap to store
    2 min values and multiply 2 min with 3 max value
        Arrays.sort(nums);
        int n = nums.length;

        int opt1 = nums[n - 1] * nums[n - 2] * nums[n - 3];

        int opt2 = nums[0] * nums[1] * nums[n - 1];

        return Math.max(opt1, opt2);
*/