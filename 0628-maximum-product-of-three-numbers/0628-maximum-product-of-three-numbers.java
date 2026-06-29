// Problem: Maximum Product of Three Numbers
// Link: https://leetcode.com/problems/maximum-product-of-three-numbers/
// Difficulty: Easy

// Approach:
// Find the maximum product of any three numbers.
// The product can be maximum in only two cases:
// Case 1:
//     Three largest positive numbers.
//     max1 * max2 * max3
// Case 2:
//     Two smallest negative numbers + largest number.
//     min1 * min2 * max3
// Why?
// Multiplying two negative numbers creates a positive value,
// which can increase the final product.
// Step 1:
// Use a min heap to store the 3 largest numbers.
//     - Add every number.
//     - If size becomes greater than 3,
//       remove the smallest.
//     At the end:
//          heap contains the 3 largest values.
// Step 2:
// Use a max heap to store the 2 smallest numbers.
//     - Add every number.
//     - If size becomes greater than 2,
//       remove the largest.
//     At the end:
//          heap contains the 2 smallest values.
// Step 3:
// Calculate both possible maximum products:
//     opt1 = three largest numbers
//     opt2 = two smallest numbers + largest number
// Return the maximum of both.

// Time Complexity: O(n log 3)
// Space Complexity: O(1)


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
