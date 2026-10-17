// Problem: Minimum Swaps to Sort by Digit Sum
// Link: https://leetcode.com/problems/minimum-swaps-to-sort-by-digit-sum/?envType=daily-question&envId=2026-10-16
// Difficulty: Medium

// Approach:
// 1. Create an Integer array sortedNums to preserve the original array
//    while allowing custom sorting.
// 2. Store each number's original index in a HashMap valToIdx.
// 3. Sort sortedNums using two criteria:
//    a. First, compare numbers by their digit sums in ascending order.
//    b. If digit sums are equal, compare the numbers themselves in ascending order.
// 4. Traverse the original nums array and compare nums[i] with sortedNums[i].
// 5. If both values match, no swap is needed.
// 6. Otherwise, find the current index of the expected value using valToIdx.
// 7. Swap the expected value into position i and update the original indices
//    of both swapped values in the HashMap.
// 8. Increment swapCount for every swap.
// 9. Return swapCount.

// Time Complexity: O(n log n * log M), where M is the maximum element.
// Space Complexity: O(n), for sortedNums and the HashMap.


class Solution {
    public int minSwaps(int[] nums) {
        int n = nums.length;
        Map<Integer, Integer> valToIdx = new HashMap<>();

        Integer[] sortedNums = new Integer[n];
        for (int i = 0; i < n; i++) {
            sortedNums[i] = nums[i];
            valToIdx.put(nums[i], i);
        }

        Arrays.sort(sortedNums, (num1, num2) -> { 
            int num1DigitSum = getDigitSum(num1); 
            int num2DigitSum = getDigitSum(num2);

            if (num1DigitSum != num2DigitSum) {
                return Integer.compare(num1DigitSum, num2DigitSum);
            }

            return Integer.compare(num1, num2);
        });

        int swapCount = 0;
        for (int i = 0; i < n; i++) {
            int expectedVal = sortedNums[i];
            int currVal = nums[i];

            // No swap required
            if (expectedVal == currVal) {
                continue;
            }

            int idx = valToIdx.get(expectedVal);

            int temp = currVal;
            nums[i] = expectedVal;
            nums[idx] = temp;

            valToIdx.put(expectedVal, i);
            valToIdx.put(currVal, idx);
            swapCount++;
        }

        return swapCount;
    }

    private int getDigitSum(int num) {
        int sum = 0;

        while (num > 0) {
            sum += num % 10;
            num /= 10;
        }

        return sum;
    }
}
